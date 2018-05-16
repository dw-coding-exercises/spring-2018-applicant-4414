(ns my-exercise.search
  "Voter Search request and results"
  (:require [clojure.string :as str]
            [clojure.edn :as edn]
            [clj-http.client :as client]
            [ring.util.response :as resp]
            [hiccup.page :refer [html5]]
            [my-exercise.home :as home]
            [my-exercise.ocd-ids :as ocd-ids]
            [clojure.pprint]))

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
  ;; Note: we'll use synchronous HTTP here because its simpler. If we
  ;; needed to coordinate the results of muptiple backend services I
  ;; would do so concurrently using core.async (or some other
  ;; coordination tool (promises or whatever).
  (let [ocd-ids (ocd-ids/by-address (select-keys (:params request)
                                      [:city :state :zip]))
        api-response (client/get (:upcoming api-urls)
                       {:query-params {:district-divisions (str/join "," ocd-ids)}})
        results (when (= 200 (:status api-response))
                  ;; TODO: proper error handling, all the way up to a
                  ;; proper error message for the user if the backend
                  ;; service doesn't return successfully

                  ;; In the mean time, just returning nil will prevent
                  ;; any great ugliness and just give the user a "no
                  ;; results" message.
                  (edn/read-string (:body api-response)))]
    (clojure.pprint/pprint results)
    (search-results results)))

