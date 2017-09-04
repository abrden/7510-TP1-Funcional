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
      (not (re-matches #".+\(.+\)" query)) nil
      (entities.database/fact-query database query) true
      (entities.database/rule-query database query) true
      :else false))
  )