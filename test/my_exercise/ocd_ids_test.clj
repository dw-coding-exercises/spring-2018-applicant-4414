(ns my-exercise.ocd-ids-test
  (:require [clojure.test :refer :all]
            [my-exercise.ocd-ids :refer :all]))

(deftest by-address-test
  (let [ids (by-address {:state "SOMESTATE"
                         :city "SOMECITY"})]
    (testing "country-level"
      (is (contains? ids "ocd-division/country:us")))
    (testing "state-level"
      (is (contains? ids "ocd-division/country:us/state:somestate")))
    (testing "place-level"
      (is (contains? ids "ocd-division/country:us/state:somestate/place:somecity")))))
