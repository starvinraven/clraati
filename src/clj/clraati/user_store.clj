(ns clraati.user-store
  (:require [yesql.core :refer [defqueries]]
            [clraati.db :as db]))

(defqueries "sql/user.sql" {:connection (db/db-spec)})

(defn get-user-by-fb-id
  [fb-id]
  (db/transform-result (sql-get-user-by-fb-id {:fb_id fb-id})))

(defn create-key
  [user-id key expires]
  (sql-create-key<! {:user_id user-id :login_key key :expires expires}))

(defn insert-user
  [{:keys [username name fb-id fb-user]}]
  (sql-insert-user<! {:username username
                      :name     name
                      :fb_id    fb-id
                      :fb_user  fb-user}))

(defn update-user
  [{:keys [id email last-login fb-user]}]
  (sql-update-user<! {:email email
                      :id id
                      :last_login last-login
                      :fb_user fb-user}))
