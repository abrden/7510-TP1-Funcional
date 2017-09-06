(ns entities.rule-test
  (:require [clojure.test :refer :all]
            [entities.rule :refer :all] [entities.fact :refer :all])
  (:import [entities.rule Rule])
  (:import [entities.fact Fact])
  )

(def consulting-detective-rule (new Rule (new Fact "consultingDetective" ["X"])
                                 [(new Fact "man" ["X"]) (new Fact "livesIn221BBakerStreet" ["X"])]))

(def daugther-rule (new Rule (new Fact "daugther" ["Y", "X"])
                     [(new Fact "woman" ["Y"]) (new Fact "father" ["X", "Y"])]))

(def consulting-detective-rule-query (new Fact "consultingDetective" ["Sherlock"]))

(def daugther-rule-query (new Fact "daugther" ["Rosamund", "John"]))

(deftest varmap-single-arg
  (testing "Tests a single argument varmap"
           (is (.equals (variables-map consulting-detective-rule consulting-detective-rule-query) {"X" "Sherlock"}))))

(deftest varmap-multiple-arg
  (testing "Tests a multiple argument varmap"
           (is (.equals (variables-map daugther-rule daugther-rule-query) {"X" "John", "Y" "Rosamund"}))))

(deftest evaluate-single-arg-rule
  (testing "Tests a single argument rule evaluation"
           (is (= (evaluate consulting-detective-rule consulting-detective-rule-query)
                  [(new Fact "man" ["Sherlock"]) (new Fact "livesIn221BBakerStreet" ["Sherlock"])]))))

(deftest evaluate-multiple-arg-rule
  (testing "Tests a multiple argument rule evaluation"
           (is (= (evaluate daugther-rule daugther-rule-query)
                  [(new Fact "woman" ["Rosamund"]) (new Fact "father" ["John", "Rosamund"])]))))
