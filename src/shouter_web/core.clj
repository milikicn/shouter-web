(ns shouter-web.core)
 
(use 'ring.adapter.jetty)

(defn app-handler [request]
  {:status 200
   :headers {"Content-Type" "text/plain;=us-ascii"}
   :body "Hello from Ring"})

(run-jetty app-handler {:port 3000})