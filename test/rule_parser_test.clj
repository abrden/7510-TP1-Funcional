(ns rule-parser-test
  (:require [clojure.test :refer :all]
            [parsers.rule-parser :refer :all]))

(deftest predicate-parse
  (testing "Tests a simple predicate parse"
    (is (= (:predicate (:predicate (parse-rule "hija(X, Y) :- mujer(X), padre(Y, X)"))) "hija"))))