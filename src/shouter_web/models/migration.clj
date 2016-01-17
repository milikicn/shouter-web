(ns shouter_web.models.migration
  (:require [clojure.java.jdbc :as sql]
            [shouter_web.models.shout :as shout]))

(defn migrated? []
  (-> (sql/query shout/spec
                 [(str "SELECT count(*) AS count " 
                       "FROM information_schema.tables "
                       "WHERE table_name = 'shouts'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands shout/spec
                        (sql/create-table-ddl
                          :shouts
                          [:id "integer NOT NULL AUTO_INCREMENT primary key"]
                          [:body "varchar(100)"]
                          [:created_at "timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP"]))
    (println " done")))