(ns logical-interpreter
  (:gen-class)
  (:require [entities.database])
  (:require [parsers.file-parser])
  (:require [parsers.fact-parser])
  )

(defn evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  query is invalid, returns nil"
  [database query]
  (if-not (re-matches #".+\(.+\)" query)
    nil
    (let [parsed-query (parsers.fact-parser/parse-fact query)]
      (cond
        (entities.database/fact-query database parsed-query) true
        (entities.database/rule-query database parsed-query) true
        :else false))
    )
  )

(defn build-db-and-evaluate-query
  "Returns true if the rules and facts in database imply query, false if not. If
  either input can't be parsed, returns nil"
  [databaseFileName query]
  (let [database (parsers.file-parser/parse-file databaseFileName)]
    (if (:malformations database)
      nil
      (evaluate-query database query)))
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
      (println (str "-> " (evaluate-query database input)))
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