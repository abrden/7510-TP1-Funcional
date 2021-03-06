(ns integration.parent-database-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def parent-database "test/files/parent_database.txt")

(deftest parent-database-fact-test
  (testing "varon(juan) should be true"
    (is (= (build-db-and-evaluate-query parent-database "varon(juan)")
           true)))
  (testing "varon(maria) should be false"
    (is (= (build-db-and-evaluate-query parent-database "varon(maria)")
           false)))
  (testing "mujer(cecilia) should be true"
    (is (= (build-db-and-evaluate-query parent-database "mujer(cecilia)")
           true)))
  (testing "padre(juan, pepe) should be true"
    (is (= (build-db-and-evaluate-query parent-database "padre(juan, pepe)")
           true)))
  (testing "padre(mario, pepe) should be false"
    (is (= (build-db-and-evaluate-query parent-database "padre(mario, pepe)")
           false))))

(deftest parent-database-rule-test
  (testing "hijo(pepe, juan) should be true"
    (is (= (build-db-and-evaluate-query parent-database "hijo(pepe, juan)")
           true)))
  (testing "hija(maria, roberto) should be false"
    (is (= (build-db-and-evaluate-query parent-database "hija(maria, roberto)")
           false))))

(deftest parent-database-empty-query-test
  (testing "varon should be nil"
    (is (= (build-db-and-evaluate-query parent-database "varon")
           nil)))
  (testing "maria should be nil"
    (is (= (build-db-and-evaluate-query parent-database "maria")
           nil)))
  (testing "empty should be nil"
    (is (= (build-db-and-evaluate-query parent-database "")
           nil))))
