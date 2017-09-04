(ns entities.database-test
  (:require [clojure.test :refer :all]
            [entities.database :refer :all] [entities.rule :refer :all] [entities.fact :refer :all])
  (:import [entities.database DataBase])
  (:import [entities.rule Rule])
  (:import [entities.fact Fact])
  )

(def consulting-detective-rule (new Rule (new Fact "consultingDetective(X)" "consultingDetective" ["X"])
                                 [(new Fact "man(X)" "man" ["X"]) (new Fact "livesIn221BBakerStreet(X)" "livesIn221BBakerStreet" ["X"])]))

(def daugther-rule (new Rule (new Fact "daugther(Y, X)" "daugther" ["Y", "X"])
                     [(new Fact "woman(Y)" "woman" ["Y"]) (new Fact "father(X, Y)" "father" ["X", "Y"])]))

(def man-fact (new Fact "man(Sherlock)" "man" ["Sherlock"]))

(def wife-fact (new Fact "wife(Mary, John)" "wife" ["Mary", "John"]))

(def test-database (new DataBase [man-fact wife-fact] [consulting-detective-rule daugther-rule] []))

(deftest positive-find-rule-in-db
  (testing "Tests positive rule search"
           (is (:sentence (:signature (find-rule test-database "daugther(Rosamund, John)"))) "daugther(Y, X)")))

(deftest negative-find-rule-in-db
  (testing "Tests negative rule search"
           (is (= (find-rule test-database "son(Rosamund, John)") nil))))

(deftest positive-fact-query
  (testing "Tests positive fact query"
           (is (= (fact-query test-database "man(Sherlock)") true))))

(deftest negative-fact-query
  (testing "Tests negative fact query"
           (is (= (fact-query test-database "woman(Sherlock)") nil))))

