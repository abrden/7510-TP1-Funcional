(ns parsers.file-parser
  (:require [clojure.string :as str] [clojure.java.io :as io]
            [entities.database] [entities.malformation] [parsers.fact-parser] [parsers.rule-parser])
  (:import [entities.database DataBase] [entities.malformation Malformation])
  )

(defn dispatch-parser
  ""
  [sentence]
  (cond
    (re-matches #".+\(.+\)\ *:-\ *.*\." sentence) (parsers.rule-parser/parse-rule sentence)
    (re-matches #".+\(.+\)\." sentence) (parsers.fact-parser/parse-fact sentence)
    :else (new Malformation sentence))
  )

(defn parse-file
  ""
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