(ns clraati.db
  (:require
    [camel-snake-kebab.core :refer (->kebab-case-keyword)]
    [camel-snake-kebab.extras :refer [transform-keys]]))

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

(defn transform-result
  [result]
  (transform-keys ->kebab-case-keyword result))