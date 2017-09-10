(ns parsers.fact-parser
  (:require [clojure.string :as str]
            [entities.fact])
  (:import [entities.fact Fact]))

(defn parse-fact
  "Returns a Fact record for the given a fact string"
  [fact-str]
  (let [[predicate & args] (str/split fact-str #"\(|,\ *|\)\.|\)")]
    (new Fact predicate args))
  )
