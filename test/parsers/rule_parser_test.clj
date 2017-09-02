(ns rule-parser-test
  (:require [clojure.test :refer :all]
            [parsers.rule-parser :refer :all]))

(deftest predicate-parse-signature
  (testing "Tests a simple predicate parse"
    (is (= (:predicate (:signature (parse-rule "hija(X, Y) :- mujer(X), padre(Y, X)"))) "hija"))))

(deftest predicate-parse-signature-without-spaces
  (testing "Tests a simple predicate parse without spaces"
           (is (= (:predicate (:signature (parse-rule "hija(X, Y):-mujer(X),padre(Y, X)"))) "hija"))))