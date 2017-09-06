(ns parsers.file-parser-test
  (:require [clojure.test :refer :all]
            [parsers.file-parser :refer :all]))

(def FACTS_FILE "test/files/facts.txt")
(def MALFORMED_FILE "test/files/malformed.txt")
(def NUMBERS_FILE "test/files/number_database.txt")
(def WITHOUT_DOTS_FILE "test/files/without_dots.txt")

(deftest simple-file-parse
  (testing "Tests a simple file parse"
    (is (= (count (:facts (parse-file FACTS_FILE))) 3))))

(deftest malformed-file-parse
  (testing "Tests a malformed file parse"
           (is (= (count (:malformations (parse-file MALFORMED_FILE))) 3))))

(deftest number-file-parse
  (testing "Tests a number file parse"
    (let [db (parse-file NUMBERS_FILE)]
      (is (and (= (count (:rules db)) 1) (= (count (:facts db)) 9) (= (count (:malformations db)) 0))))))

(deftest db-without-dot-ending-is-malformed
  (testing "Tests a file without dots parse"
    (let [db (parse-file WITHOUT_DOTS_FILE)]
      (is (and (= (count (:rules db)) 0) (= (count (:facts db)) 0) (= (count (:malformations db)) 3))))))