(ns clraati.db)

(defonce dbspec (atom nil))

(defn- cfg->dbspec
  [cfg]
  {:classname   "org.postgresql.Driver"
   :subprotocol "postgresql"
   :subname     (:db-url cfg)
   :user        (:db-username cfg)
   :password    (:db-password cfg)})

(defn init-db
  [cfg]
  (reset! dbspec (cfg->dbspec cfg)))

(defn db-spec
  []
  @dbspec)


