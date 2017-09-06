(ns entities.fact)

(defprotocol Equivalent
  (equivalent-to-query [this query]))

(defrecord Fact [sentence predicate args]
  Equivalent
  (equivalent-to-query [this query]
    (= (:sentence this) (:sentence query))
    )
  )
