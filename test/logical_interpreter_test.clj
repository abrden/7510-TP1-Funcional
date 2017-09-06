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

(deftest true-fact-query-written-without-spaces
  (testing "Tests a true fact query without spaces that was with spaces in de db file"
    (is (= (build-db-and-evaluate-query FACTS_FILE "friends(Sherlock,John)") true))))

(deftest false-fact-query-unordered
  (testing "Tests a false fact query due to the args order"
    (is (= (build-db-and-evaluate-query FACTS_FILE "friends(John, Sherlock)") false))))

(deftest empty-query
  (testing "Tests an empty query"
    (is (= (build-db-and-evaluate-query FACTS_FILE "") nil))))

(deftest query-with-dot
  (testing "Test query with dot should be nil"
    (is (= (build-db-and-evaluate-query FACTS_FILE "man(John).") nil))))