(ns parsers.rule-parser
  (:require [parsers.fact-parser])
  (:require [entities.rule])
  (:import [entities.rule Rule])
  (:require [clojure.string :as str]))

(defn parse-rule
  "Returns a Rule record for the given a rule string"
  [rule]
  (let [[predicate & facts] (map parsers.fact-parser/parse-fact (str/split rule #"\ *:-\ *|\),\ *"))]
    (new Rule predicate facts)
    )
  )
