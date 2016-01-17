(ns shouter_web.models.shout
  (:require [clojure.java.jdbc :as sql]))

(def spec 
  {:classname "com.mysql.jdbc.Driver"
   :subprotocol "mysql"
   :subname "//127.0.0.1:3306/shouter"
   :user "shouter"
   :password "shouter"})

(defn all []
  (into [] (sql/query spec ["select * from shouts order by id desc"])))

(defn create [shout]
  (sql/insert! spec :shouts [:body] [shout]))