(ns parsers.fact-parser
  (:require [entities.fact])
  (:import [entities.fact Fact])
  (:require [clojure.string :as str]))

(defn parse-fact
  "Returns a Fact record for the given a fact string"
  [fact]
  (let [[predicate & args] (str/split fact #"\(|,|\)")]
    (new Fact predicate args)
    )
  )
