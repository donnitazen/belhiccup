(ns belhiccup.articles)

(defn read-article-file
  [filename]
  (->> (read-string (slurp (str "resources/data/" filename)))))


(defn show-one-article
  "The id should be string"
  [id]
  (->> (read-article-file "articles.edn")
      (filter #(= id (% :article-id)))
       first))


