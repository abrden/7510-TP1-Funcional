(ns parsers.file-parser
  (:require [clojure.string :as str] [clojure.java.io :as io]
            [entities.database] [entities.malformation] [parsers.fact-parser] [parsers.rule-parser])
  (:import [entities.database DataBase] [entities.malformation Malformation])
  )

(defn dispatch-parser
  "Receives a string sentence from the database file.
  Dispatches the fact or rule parser.
  Returns a Fact, Rule or Malformation record depending on the sentence format."
  [sentence]
  (cond
    (re-matches #".+\(.+\)\ *:-\ *.*\." sentence) (parsers.rule-parser/parse-rule sentence)
    (re-matches #".+\(.+\)\." sentence) (parsers.fact-parser/parse-fact sentence)
    :else (new Malformation sentence))
  )

(defn parse-file
  "Receibes a string representing the database file name.
  Returns a DataBase record containing the facts, rules and malformations of the file."
  [fileName]
  (let [ dbmap (group-by type
          (map dispatch-parser
               (with-open [rdr (io/reader (io/file fileName))]
                   (doall (line-seq rdr)))
               )
          )] (new DataBase
                      (get dbmap entities.fact.Fact)
                      (get dbmap entities.rule.Rule)
                      (get dbmap entities.malformation.Malformation))
    )
  )