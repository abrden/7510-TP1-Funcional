(ns entities.database)

(defprotocol Compliant
  (complies-with-fact [this query]))

(defrecord DataBase [facts rules malformations]
  Compliant
  (complies-with-fact [this query]
                      (some (fn [fact] (= (:sentence fact) query)) (:facts this)))
  )