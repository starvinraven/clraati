(ns clraati.user
  (:require
    [clraati.facebook :as fb]
    [taoensso.timbre :as timbre]
    [clojure.data.json :as json]
    [clraati.user-store :as store]
    [clj-time.core :as time]
    [cheshire.core :refer [parse-string]]
    [crypto.random :as random]))

(defn- get-or-create-user
  [fb-user]
  (let [fb-id         (str (:id fb-user))
        existing-user (store/get-user-by-fb-id fb-id)]
    (if existing-user
      (let [new-email (:email (fb/create-user-facebook user local-id))]
        (store/update-user (merge existing-user {:fb-user fb-user :email new-email :last-login (time/now)}))
        user-with-updated-data)
      (store/insert-user (fb/create-user-facebook fb-user local-id)))))

(defn create-key
  [user]
  (let [key     (random/base64 80)
        expires (time/plus (time/now) (time/weeks 1))]
    (store/create-key (:id user) key expires)))

(defn log-in
  [code]
  (let [user-map (parse-string (fb/get-info-facebook code))
        user     (get-or-create-user user-map)
        user-key (create-key user)]
    (timbre/info "got user" user-map "and key" user-key "local user" user)
    {:user user :key user-key}))