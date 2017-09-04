(ns integration.number-database-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def number-database "test/files/number_database.txt")

(deftest number-database-fact-test
  (testing "add(one, one, two) should be true"
    (is (= (evaluate-query number-database "add(one, one, two)")
           true)))
  (testing "add(two, one, one) should be false"
    (is (= (evaluate-query number-database "add(two, one, one)")
           false))))
           
(deftest number-database-rule-test
  (testing "subtract(one, one, two) should be false"
    (is (= (evaluate-query number-database "subtract(one, one, two)")
           false)))
  (testing "subtract(two, one, one) should be true"
    (is (= (evaluate-query number-database "subtract(two, one, one)")
           true))))