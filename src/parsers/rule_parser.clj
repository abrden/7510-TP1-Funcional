(ns parsers.rule-parser
  (:require [clojure.string :as str]
            [parsers.fact-parser] [entities.rule])
  (:import [entities.rule Rule]))

(defn parse-rule
  "Returns a Rule record for the given a rule string"
  [rule]
  (let [[signature & facts] (map parsers.fact-parser/parse-fact (str/split rule #"\ *:-\ *|\),\ *"))]
    (new Rule signature facts)
    )
  )
