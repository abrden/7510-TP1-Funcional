(ns parsers.file-parser
  (:require [entities.database])
  (:import [entities.database DataBase])
  (:require [entities.malformation])
  (:import [entities.malformation Malformation])
  (:require [parsers.fact-parser])
  (:require [parsers.rule-parser])
  (:require [clojure.string :as str])
  (:require [clojure.java.io :as io])
  )

(defn dispatch-parser
  ""
  [sentence]
  (cond
    (re-matches #".+\(.+\)\ *:-\ *.*" sentence) (parsers.rule-parser/parse-rule sentence)
    (re-matches #".+\(.+\)" sentence) (parsers.fact-parser/parse-fact sentence)
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