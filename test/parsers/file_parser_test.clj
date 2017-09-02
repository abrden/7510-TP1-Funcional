(ns parsers.file-parser-test
  (:require [clojure.test :refer :all]
            [parsers.file-parser :refer :all]))

(def FACTS_FILE "test/files/facts.txt")
(def MALFORMED_FILE "test/files/malformed.txt")

(deftest simple-file-parse
  (testing "Tests a simple file parse"
    (is (= (count (:facts (parse-file FACTS_FILE))) 3))))

(deftest malformed-file-parse
  (testing "Tests a malformed file parse"
           (is (= (count (:malformations (parse-file MALFORMED_FILE))) 3))))