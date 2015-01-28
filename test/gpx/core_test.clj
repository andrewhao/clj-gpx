(ns gpx.core-test
  (require [clj-time.core :as tc]
           [clojure.test :refer :all]
           [gpx.core :refer :all]
           [clojure.algo.generic.math-functions :refer :all]))

(deftest utility-functions
  (testing "value of R"
    (is (= R 6367)))

  (testing "radian conversion"
    (is (= Math/PI (rad 180))))

  (testing "haversine distance"
    (let [start {:lat 10, :lon 10}
          end {:lat 20, :lon 20}]
      (is (= 1543.7876928387263
             (haversine start end))))))

(deftest gpx-parsing
  (testing "trackpoint generation from XML"
    (let [path "test/fixtures/simple.gpx"
          output [{:time (tc/date-time 2015 01 01 00 00)
                   :elevation "10.0"
                   :lat 10
                   :lon 10}
                  {:time (tc/date-time 2015 01 01 00 01)
                   :elevation "20.0"
                   :lat 20
                   :lon 20}]]
      (is (= output (get-points path))))))

(deftest time-calculations
  (testing "elapsed time in seconds"
    (let [coll [{:time (tc/date-time 2015 01 01 00 00)}
                {:time (tc/date-time 2015 01 01 00 01)}]]
      (is (= 60
             (calculate-time coll))))))
