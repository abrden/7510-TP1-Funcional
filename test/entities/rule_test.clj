(ns entities.rule-test
  (:require [clojure.test :refer :all]
            [entities.rule :refer :all] [entities.fact :refer :all])
  (:import [entities.rule Rule])
  (:import [entities.fact Fact])
  )

(def consulting-detective-rule (new Rule (new Fact "consultingDetective(X)" "consultingDetective" ["X"])
                                 [(new Fact "man(X)" "man" ["X"]) (new Fact "livesIn221BBakerStreet(X)" "livesIn221BBakerStreet" ["X"])]))

(def daugther-rule (new Rule (new Fact "daugther(Y, X)" "daugther" ["Y", "X"])
                     [(new Fact "woman(Y)" "woman" ["Y"]) (new Fact "father(X, Y)" "father" ["X", "Y"])]))

(deftest format-single-arg-sentence
  (testing "Tests a single argument sentence format"
           (is (= (format-sentence "man" ["Sherlock"]) "man(Sherlock)"))))

(deftest format-multiple-arg-sentence
  (testing "Tests a multiple argument sentence format"
           (is (= (format-sentence "friends" ["John" "Molly" "Lestrade"]) "friends(John, Molly, Lestrade)"))))

(deftest varmap-single-arg
  (testing "Tests a single argument varmap"
           (is (.equals (variables-map consulting-detective-rule "consultingDetective(Sherlock)") {"X" "Sherlock"}))))

(deftest varmap-multiple-arg
  (testing "Tests a multiple argument varmap"
           (is (.equals (variables-map daugther-rule "daugther(Rosamund, John)") {"X" "John", "Y" "Rosamund"}))))

(deftest evaluate-single-arg-rule
  (testing "Tests a single argument rule evaluation"
           (is (= (evaluate consulting-detective-rule "consultingDetective(Sherlock)") ["man(Sherlock)" "livesIn221BBakerStreet(Sherlock)"]))))

(deftest evaluate-multiple-arg-rule
  (testing "Tests a multiple argument rule evaluation"
           (is (= (evaluate daugther-rule "daughter(Rosamund, John)") ["woman(Rosamund)" "father(John, Rosamund)"]))))
