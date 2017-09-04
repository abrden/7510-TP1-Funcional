(ns logical-interpreter
  (:gen-class)
  (:require [entities.database])
  (:require [parsers.file-parser])
  )

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [databaseFileName query]
  (let [database (parsers.file-parser/parse-file databaseFileName)]
    (cond
      (:malformations database) nil
      (not (re-matches #".+\(.+\)" query)) nil
      (entities.database/fact-query database query) true
      (entities.database/rule-query database query) true
      :else false))
  )

(defn evaluate-query-with-db
  "Returns true if the rules and facts in database imply query, false if not. If
  query is invalid, returns nil"
  [database query]
  (cond
    (not (re-matches #".+\(.+\)" query)) nil
    (entities.database/fact-query database query) true
    (entities.database/rule-query database query) true
    :else false)
  )

(defn create-database
  ""
  [databaseFileName]
  (let [database (parsers.file-parser/parse-file databaseFileName)]
    (if (:malformations database)
      nil
      database))
  )

(defn query-loop
  ""
  [database]
  (loop [input (read-line)]
    (when-not (= "q" input)
      (println (str "-> " (evaluate-query-with-db database input)))
      (recur (read-line))))
  )

(defn -main
  ""
  [databaseFileName]
  (if-let [database (create-database databaseFileName)]
    (do (println "-> Database loaded successfully. Enter any query. Exit with 'q'.")
        (query-loop database))
    (println "-> Error: Malformed database"))
  (println "-> Exit")
  )