(ns shouter-web.web
  (:use compojure.core)
  (:require [compojure.handler :as handler]
            [shouter-web.views.layout :as layout]))

(defn str-to [num]
  (apply str (interpose ", " (range 1 (inc num)))))

(defn str-from [num]
  (apply str (interpose ", " (reverse (range 1 (inc num))))))

(defroutes app
  (GET "/count-up/:to" [to] (layout/common "SHOUTER" (str-to (Integer. to))))
  (GET "/count-down/:from" [from] (layout/common "SHOUTER" (str-from (Integer. from)))))