(ns com.ebao.gs.integration.mapping
  "rule helper file"
  )

(import '(java.io ByteArrayInputStream))

(require '[clojure.data.json :as json]
          '[clojure.xml :as xml])

(defn get-struct-map [xml]
  (let [stream (ByteArrayInputStream. (.getBytes (.trim xml)))]
    (xml/parse stream)))

(defn test[value mapcontext]
  (let [ context  (json/read-json mapcontext)
         xmlValue (get-struct-map value)]
       (println context)
       (println xmlValue)
       (first (:content(second (:content xmlValue))))
    )
  )










