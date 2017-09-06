(ns formatters.sentence-formatter
  (:require [clojure.string :as str]))

(defn format-sentence
  ""
  [predicate args]
  (str predicate "(" (str/join ", " args) ").")
  )