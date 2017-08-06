(ns clraati.migration
  (:require
    [migratus.core :as migratus]
    [clraati.db :as db]))

; create role clraati with login password 'clraati';
; create database clraati;
; grant all on database clraati to clraati;

(defn config
  []
  {:store                :database
   :migration-dir        "migrations/"
   :migration-table-name "migrations"
   :db                   (db/db-spec)})

(defn migrate
  []
  (migratus/migrate (config)))