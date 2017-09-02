(ns logical-interpreter
  (:require [entities.database])
  (:require [parsers.file-parser])
  )

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [databaseFileName query]
  (let [database (parsers.file-parser/parse-file databaseFileName)]
    (cond
      (entities.database/complies-with-fact database query) true
      (= query "") false
      :else false))
  )