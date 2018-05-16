(ns my-exercise.ocd-ids
  "Tools for creating and manipulating OCD-IDs"
  (:require [clojure.string :as str]))

(defn- for-country
  "Return the country-level OCD-ID. Currently a constant function since we only support USA elections."
  [_]
  "ocd-division/country:us")

;; If we supported multiple address formats (e.g for different
;; countries) we would probably at a minimum make this a multimethod
;; dispatching on country. Ditto for the other helper functions in this file.
(defn- for-state
  "Return the state-level OCD-ID."
  [{:keys [state] :as address}]
  (str (for-country address) "/state:" (str/lower-case state)))

(defn- for-county
  "Return the county-level OCD-ID."
  [address]
  ;; TODO: need to make another API call to figure out the county for this address
  nil)

(defn- for-district
  "Return the congressional district level OCD-ID."
  [address]
  ;; TODO: We need to make another API call to figure out the district for this address
  nil)

(defn- for-place
  "Return the place/city OCD-ID."
  [{:keys [city] :as address}]
  (str (for-state address) "/place:" (str/lower-case city)))

;; TODO: Specify the definition of an 'address-map' using spec, schema, etc.
(defn by-address
  "Given an address map, return a set of OCD-IDs corresponding to that address"
  [address]
  (->> [for-country for-state for-county for-district for-place]
    (keep #(% address))
    (set)))
