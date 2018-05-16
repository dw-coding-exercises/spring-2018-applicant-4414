(ns my-exercise.core
  (:require [compojure.core :as compojure :refer [GET POST defroutes]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [my-exercise.home :as home]
            [my-exercise.search :as search]))

(defroutes app
  (GET "/" [] home/page)
  (POST "/search" [] search/search)
  (route/resources "/")
  (route/not-found "Not found"))

(def handler
  (-> app
      (wrap-defaults site-defaults)
      wrap-reload))

(comment

  ;;Start server in REPL
  (do
    (use 'ring.adapter.jetty)
    (def server (run-jetty handler {:port 3000 :join? false})))

  ;; Stop server
  (.stop server)

  )
