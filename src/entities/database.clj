(ns entities.database
  (:require [entities.rule]))

(defprotocol Compliant
  (complies-with-fact [this query])
  (complies-with-rule [this query])
  )

(defn fact-query
  ""
  [database query]
  (some (fn [fact] (= (:sentence fact) query)) (:facts database))
  )

(defn rule-query
  ""
  [database rule query]
  (every? (fn [x] (fact-query database x)) (entities.rule/evaluate rule query))
  )

(defn find-rule
  ""
  [database query]
  (first (filter (fn [rule] (= (:predicate (:signature rule)) query)) (:rules database)))
  )

(defrecord DataBase [facts rules malformations]
  Compliant
  (complies-with-fact [this query]
                      (fact-query this query))
  (complies-with-rule [this query]
                      (if-let [rule (find-rule this query)]
                        (rule-query this rule query)
                        false)
                      )
  )