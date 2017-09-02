(ns logical-interpreter-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def FACTS_FILE "/home/agustina/Documents/tdd-tp1/test/files/facts")

(deftest true-fact-query
  (testing "Tests a simple true fact query"
           (is (= (evaluate-query FACTS_FILE "man(John)") true))))
