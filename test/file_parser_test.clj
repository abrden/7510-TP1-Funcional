(ns file-parser-test
  (:require [clojure.test :refer :all]
            [parsers.file-parser :refer :all]))


(def FACTS_FILE "/home/agustina/Documents/tdd-tp1/test/files/facts")
(def MALFORMED_FILE "/home/agustina/Documents/tdd-tp1/test/files/malformed")

(deftest simple-file-parse
  (testing "Tests a simple file parse"
    (is (= (parse-file FACTS_FILE) '("man(John)" "woman(Irene)" "friends(Sherlock, John)")))))

(deftest malformed-file-parse
  (testing "Tests a malformed file parse"
           (is (= (parse-file MALFORMED_FILE) '("father(John, Rosamund)" "wife(John, Mary)" "Malformed" "man(Sherlock)" "Malformed" "Malformed")))))