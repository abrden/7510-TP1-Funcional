(ns entities.database-test
  (:require [clojure.test :refer :all]
            [entities.database :refer :all] [entities.rule :refer :all] [entities.fact :refer :all])
  (:import [entities.database DataBase])
  (:import [entities.rule Rule])
  (:import [entities.fact Fact])
  )

(def consulting-detective-rule (new Rule (new Fact "consultingDetective" ["X"])
                                 [(new Fact "man" ["X"])]))

(def daugther-rule (new Rule (new Fact "daugther" ["Y", "X"])
                     [(new Fact "woman" ["Y"]) (new Fact "father" ["X", "Y"])]))

(def woman-fact (new Fact "woman" ["Rosamund"]))

(def father-fact (new Fact "father" ["John", "Rosamund"]))

(def man-fact (new Fact "man" ["Sherlock"]))

(def test-database (new DataBase [woman-fact father-fact man-fact] [consulting-detective-rule daugther-rule] []))

(deftest positive-find-rule-in-db
  (testing "Tests positive rule search"
           (is (:predicate (:signature (find-rule test-database (new Fact "daugther" ["Molly", "Mr. Hooper"])))) "daugther")))

(deftest negative-find-rule-in-db
  (testing "Tests negative rule search"
           (is (= (find-rule test-database (new Fact "son" ["Rosamund", "John"])) nil))))

(deftest positive-fact-query
  (testing "Tests positive fact query"
           (is (= (fact-query test-database woman-fact) true))))

(deftest negative-fact-query
  (testing "Tests negative fact query"
           (is (= (fact-query test-database (new Fact "woman" ["Sherlock"])) nil))))

(deftest positive-rule-query-single
  (testing "Tests positive rule query with a single fact"
    (is (= (rule-query test-database (new Fact "consultingDetective" ["Sherlock"])) true))))

(deftest positive-rule-query-multiple
  (testing "Tests positive rule query"
    (is (= (rule-query test-database (new Fact "daugther" ["Rosamund", "John"])) true))))

(deftest negative-rule-query
  (testing "Tests negative rule query"
           (is (= (rule-query test-database (new Fact "daugther" ["Molly", "John"])) false))))

(deftest negative-rule-query-nonexistent-rule
  (testing "Tests negative rule query for nonexistent rule"
           (is (= (rule-query test-database (new Fact "son" ["Sherlock", "Mr. Holmes"])) false))))