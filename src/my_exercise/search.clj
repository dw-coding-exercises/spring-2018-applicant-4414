(ns my-exercise.search
  "Voter Search request and results"
  (:require  [ring.util.response :as resp]
             [hiccup.page :refer [html5]]
             [my-exercise.home :as home]))

(defn search-results
  "Server-side rendering of the search results page"
  [request]
  (html5
    ;; reuse the home header. Should probably factor out to common lib
    ;; code (if always the same) or create a separate header (if
    ;; always different.)
    (home/header request)
    [:div "Hello, world!"]))

(defn search
  "Ring handler for voter search POST"
  [request]
  (search-results request))

