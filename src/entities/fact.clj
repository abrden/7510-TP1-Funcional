(ns entities.fact)

(defprotocol Equivalent
  (equivalent-to-query [this query]))

(defrecord Fact [sentence predicate args]
  Equivalent
  (equivalent-to-query [this query]
    (and (= (:predicate this) (:predicate query)) (= (:args this) (:args query)))
    )
  )
