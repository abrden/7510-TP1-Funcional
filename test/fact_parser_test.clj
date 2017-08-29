(ns fact-parser-test
  (:require [clojure.test :refer :all]
            [parsers.fact-parser :refer :all]))

(deftest predicate-parse
  (testing "Tests a simple predicate parse"
    (is (= (:predicate (parse-fact "man(John)")) "man"))))

(deftest single-arg-parse
  (testing "Tests a single arg parse"
           (is (= (:args (parse-fact "man(John)")) '("John")))))

(deftest two-arg-parse
  (testing "Tests a two arg parse"
           (is (= (:args (parse-fact "father(John, Rosamund)")) '("John", "Rosamund")))))
