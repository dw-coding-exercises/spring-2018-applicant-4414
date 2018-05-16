(ns my-exercise.search
  "Voter Search request and results"
  (:require [clojure.string :as str]
            [clj-http.client :as client]
            [ring.util.response :as resp]
            [hiccup.page :refer [html5]]
            [my-exercise.home :as home]
))

;; In a real app, would pull this out to a config file or settings DB
;; instead of a hardcoded map
(def api-urls {:upcoming "https://api.turbovote.org/elections/upcoming"})

(defn- search-results
  "Server-side rendering of the search results page"
  [results]
  (html5
    ;; reuse the home header. Should probably factor out to common lib
    ;; code (if always the same) or create a separate header (if
    ;; always different.)
    (home/header nil)
    [:div "Hello, world!"]))

(defn search
  "Ring handler for upcoming election POST request"
  [request]
  ;; Note: we'll use synchronous HTTP here because its
  ;; simpler. If we needed to coordinate the results of muptiple
  ;; backend services I would do so concurrently using
  ;; core.async (or threads, or promises, or something like that.)
  (let [ocd-ids ["ocd-division/country:us/state:nj"
                 "ocd-division/country:us/state:nj/place:newark"]
        results (client/get (:upcoming api-urls)
                  {:query-params {:district-divisions (str/join "," ocd-ids)}})]
    (search-results results)))

