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
    (re-matches #".+\(.+\)" sentence) (parsers.fact-parser/parse-fact sentence)
    (re-matches #".+\(.+\)\ *:-\ *.*" sentence) (parsers.rule-parser/parse-rule sentence)
    :else (new Malformation sentence))
  )

(defn parse-file
  ""
  [fileName]
  (group-by type
          (map dispatch-parser
               (with-open [rdr (io/reader fileName)]
                   (doall (line-seq rdr)))
               )
          )
  )