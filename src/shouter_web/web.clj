(ns shouter_web.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [shouter_web.controllers.shouts :as shouts]
            [shouter_web.views.layout :as layout]
            [shouter_web.models.migration :as schema]))

(defroutes routes
  shouts/routes
  (route/resources "/")
  (route/not-found (layout/four-oh-four)))

(def app
  (do
    (schema/migrate)
    (wrap-defaults routes site-defaults)))