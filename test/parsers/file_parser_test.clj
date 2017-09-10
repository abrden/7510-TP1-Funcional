(ns parsers.file-parser-test
  (:require [clojure.test :refer :all]
            [parsers.file-parser :refer :all]))

(def facts-file "test/files/facts.txt")
(def malformed-file "test/files/malformed.txt")
(def numbers-file "test/files/number_database.txt")
(def without-dots-file "test/files/without_dots.txt")

(deftest simple-file-parse
  (testing "Tests a simple file parse"
    (is (= (count (:facts (parse-file facts-file))) 3))))

(deftest malformed-file-parse
  (testing "Tests a malformed file parse"
    (is (= (count (:malformations (parse-file malformed-file))) 3))))

(deftest number-file-parse
  (testing "Tests a number file parse"
    (let [db (parse-file numbers-file)]
      (is (and (= (count (:rules db)) 1) (= (count (:facts db)) 9) (= (count (:malformations db)) 0))))))

(deftest db-without-dot-ending-is-malformed
  (testing "Tests a file without dots parse"
    (let [db (parse-file without-dots-file)]
      (is (and (= (count (:rules db)) 0) (= (count (:facts db)) 0) (= (count (:malformations db)) 3))))))