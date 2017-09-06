(ns formatters.sentence-formatter
  (:require [clojure.test :refer :all]
            [formatters.sentence-formatter :refer :all]))

(deftest format-single-arg-sentence
  (testing "Tests a single argument sentence format"
           (is (= (format-sentence "man" ["Sherlock"]) "man(Sherlock)"))))

(deftest format-multiple-arg-sentence
  (testing "Tests a multiple argument sentence format"
           (is (= (format-sentence "friends" ["John" "Molly" "Lestrade"]) "friends(John, Molly, Lestrade)"))))