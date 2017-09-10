(ns entities.rule
  (:require [entities.fact])
  (:import [entities.fact Fact]))

(defprotocol Evaluable
  (evaluate [this query]))

(defn variables-map
  "Receives a Rule record and a Fact record named query.
  Returns a map with the rule variables as keys and the correspondent constants as values.
  For example; The rule daugther(X, Y) and query daugther(Rosamund, John) returns
  the map {X Rosamund, Y John}"
  [rule query]
  (zipmap (:args (:signature rule)) (:args query))
  )

(defrecord Rule [signature facts]
  Evaluable
  (evaluate [this query]
    "Receives a Rule record and a Fact record named query.
    Returns a sequence of new Fact records (the ones on the rule's body)
    with their variable args replaces by the ones on the query.
    For example; The rule daugther(X, Y) :- father(X,Y) and query daugther(Rosamund, John)
    returns [ father(John, Rosamund) ]"
    (map
      (fn [generic-fact]
        (new Fact
             (:predicate generic-fact)
             (map (fn [var] (get (variables-map this query) var)) (:args generic-fact))))
      (:facts this))
    )
  )

