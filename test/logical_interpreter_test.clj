(ns logical-interpreter-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def FACTS_FILE "test/files/facts.txt")

(deftest true-fact-query
  (testing "Tests a simple true fact query"
    (is (= (build-db-and-evaluate-query FACTS_FILE "man(John)") true))))

(deftest false-fact-query
  (testing "Tests a simple false fact query"
    (is (= (build-db-and-evaluate-query FACTS_FILE "woman(John)") false))))

(deftest empty-query
  (testing "Tests an empty query"
    (is (= (build-db-and-evaluate-query FACTS_FILE "") nil))))
