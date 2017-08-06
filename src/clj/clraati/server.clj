(ns clraati.server
  (:require [clraati.handler :refer [handler]]
            [ring.adapter.jetty :refer [run-jetty]]
            [com.stuartsierra.component :as component]
            [taoensso.timbre :as timbre]))

(defrecord Server [port]
  component/Lifecycle

  (start [component]
    (do
      (timbre/info "Starting server on port" port)
      (let [server (run-jetty handler {:port port})]
        (assoc component :server server))))

  (stop [component]
    (.stop (:server component))))

(defn new-server [port]
  (map->Server {:port port}))


