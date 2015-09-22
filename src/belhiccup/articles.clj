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

(defn add-article
  [article]
  (let [all-articles (read-article-file "articles.edn")
        new-id (inc (read-string (:article-id (last all-articles))))
        new-article {:title (article :title) :text (article :content) :article-id (str new-id)}]
    (spit "resources/data/articles.edn" (conj all-articles new-article))
    new-id))


