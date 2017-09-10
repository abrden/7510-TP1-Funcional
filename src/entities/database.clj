(ns entities.database
  (:require [entities.rule] [parsers.fact-parser]))

(defprotocol Queryable
  (fact-query [this query])
  (rule-query [this query])
  )

(defn find-rule
  "Receives a DataBase record and a Fact record named query.
  Returns a Rule record if the predicate of the query matches the predicate of one of the rules on the database.
  Otherwise, nil."
  [database query]
  (first
    (filter (fn [rule] (= (:predicate (:signature rule)) (:predicate query))) (:rules database))
    )
  )

(defrecord DataBase [facts rules malformations]
  Queryable
  (fact-query [this query]
    "Receives a DataBase record and a Fact record named query.
    Returns true if the query matches one of the facts on the database.
    Otherwise, nil."
    (some (fn [fact] (= fact query)) (:facts this))
    )
  (rule-query [this query]
    "Receives a DataBase record and a Fact record named query.
    Returns true if the query complies with one of the rules on the database. Otherwise, false.
    Returns nil if the query's predicate doesn't match any of the rules on the database."
    (if-let [rule (find-rule this query)]
      (every? (fn [evaluated-fact] (fact-query this evaluated-fact)) (entities.rule/evaluate rule query)))
    )
  )