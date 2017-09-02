(ns logical-interpreter-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def FACTS_FILE "test/files/facts.txt")

(deftest true-fact-query
  (testing "Tests a simple true fact query"
           (is (= (evaluate-query FACTS_FILE "man(John)") true))))
