(ns entities.database
  (:require [entities.rule])
  (:require [parsers.fact-parser]))

(defprotocol Queryable
  (fact-query [this query])
  (rule-query [this query])
  )

(defn find-rule
  ""
  [database query]
  (first (filter
          (fn [rule] (= (:predicate (:signature rule)) (:predicate (parsers.fact-parser/parse-fact query))))
          (:rules database)
          ))
  )

(defrecord DataBase [facts rules malformations]
  Queryable
  (fact-query [this query]
              (some (fn [fact] (= (:sentence fact) query)) (:facts this))
              )
  (rule-query [this query]
              (if-let [rule (find-rule this query)]
                        (every? (fn [x] (fact-query this x)) (entities.rule/evaluate rule query))
                        false)
              )
  )