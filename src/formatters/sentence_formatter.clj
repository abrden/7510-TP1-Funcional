(ns formatters.sentence-formatter
  (:require [clojure.string :as str]))

(defn format-sentence
  "Receives a string predicate and a sequence of args.
  Returns a string sentence."
  [predicate args]
  (str predicate "(" (str/join ", " args) ").")
  )