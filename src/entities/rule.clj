(ns entities.rule
  (:require [entities.fact])
  (:import [entities.fact Fact]))

(defprotocol Evaluable
  (evaluate [this query]))

(defn variables-map
  ""
  [rule query]
  (zipmap (:args (:signature rule)) (:args query))
  )

(defrecord Rule [signature facts]
  Evaluable
  (evaluate [this query]
              (map
               (fn [generic-fact]
                 (new Fact
                  (:predicate generic-fact)
                  (map (fn [var] (get (variables-map this query) var)) (:args generic-fact)))
                 )
               (:facts this))
            )
  )

