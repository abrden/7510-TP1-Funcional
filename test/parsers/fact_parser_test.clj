(ns fact-parser-test
  (:require [clojure.test :refer :all]
            [parsers.fact-parser :refer :all]))

(deftest predicate-parse
  (testing "Tests a simple predicate parse"
    (is (= (:predicate (parse-fact "man(John)")) "man"))))

(deftest single-arg-parse
  (testing "Tests a single arg parse"
           (is (= (:args (parse-fact "man(John)")) '("John")))))

(deftest two-args-parse
  (testing "Tests a two args parse"
           (is (= (:args (parse-fact "father(John, Rosamund)")) '("John", "Rosamund")))))

(deftest without-spaces-parse
  (testing "Tests args without spaces parse"
           (is (= (:args (parse-fact "friends(John,Sherlock,Molly)")) '("John", "Sherlock", "Molly")))))