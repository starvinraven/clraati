(ns clraati.core
  (:require
    [config.core :refer [env]]
    [clraati.server :as server]
    [clraati.db :as db]
    [clraati.migration :as migration]
    [taoensso.timbre :as timbre]
    [com.stuartsierra.component :as component])

  (:gen-class))

(defn config-map
  []
  {:http-port   (Integer/parseInt (or (env :port) "3000"))
   :db-url      (or (env :db-url) "//localhost:5432/clraati")
   :db-username (or (env :db-username) "clraati")
   :db-password (or (env :db-password) "clraati")})

(defn system [config-options]
  (let [{:keys [http-port]} config-options]
    (component/system-map
      :server (server/new-server http-port))))

(defn wait-forever
  []
  @(promise))

(defn -main [& args]
  (timbre/info "Initializing...")
  (timbre/set-level! :info)
  (let [config (config-map)]
    (timbre/info "Init DB config")
    (db/init-db config)
    (timbre/info "Migrating DB")
    (migration/migrate)
    (timbre/info "Starting system")
    (component/start (system config))
    (wait-forever)))

