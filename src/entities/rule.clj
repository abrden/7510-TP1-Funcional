(ns entities.rule
  (:require [parsers.fact-parser])
  (:require [clojure.string :as str]))

(defprotocol Evaluable
  (evaluate [this query]))

(defn variables-map
  ""
  [rule query]
  (zipmap (:args (:signature rule)) (:args (parsers.fact-parser/parse-fact query)))
  )

(defn format-sentence
  ""
  [predicate args]
  (str predicate "(" (str/join ", " args) ")")
  )

(defrecord Rule [signature facts]
  Evaluable
  (evaluate [this query]
              (map
               (fn [fact]
                 (format-sentence
                  (:predicate fact)
                  (map (fn [var] (get (variables-map this query) var)) (:args fact)))
                 )
               (:facts this))
            )
  )

