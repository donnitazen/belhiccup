(ns belhiccup.pages
  (:require
    [hiccup.core :as hc]
    [hiccup.page :as hp]))

(defn home
  []
  (hp/html5
    [:head
     [:meta {:charset "utf-8"}]
     (hp/include-css "/css/normalize.css")
     (hp/include-css "/css/foundation.min.css")]
    [:body
     [:center
      [:h1 "Helloooow world!"]]]))

(defn- head
  []
  (hc/html [:head
            [:meta {:charset "utf-8"}]
            (hp/include-css "/css/normalize.css")
            (hp/include-css "/css/foundation.min.css")
            ]))

(defn- header
  []
  (hc/html [:header {:class "large-12"}
            [:nav {:class "top-bar"}
             [:ul
              [:li {:class "name"}
               [:h2
                [:a {:href "/"} "Logo"]]]
              ]
             [:section
              [:ul {:class "left"}
               [:li
                [:a {:href "/article"} "Articles"]]]
              ]]]))

(defn- asset-js
  []
  (hc/html (hp/include-js "/js/vendor/jquery.js")
           (hp/include-js "/js/vendor/fastclick.js")
           (hp/include-js "/js/foundation.min.js")
           (hp/include-js "/js/app.js")))

(defn- footer
  []
  (hc/html [:footer {:class ".two.columns"}
            [:hr]
            [:p
             [:a {:href "/contact"} "Contact Us"]]
            [:p "Copyright 2015 Beleje can be Cute as well"]]))

(defn- body
  []
  (hc/html [:div {:class "large-12 columns"}
            [:h1 "Aloha"]
            [:div {:class "large-12 columns"}
             [:h1 "My Friends' Photographs"]
             (map cute-pic-space
                  ["pic-title-1" "pic-title-2" "pic-title-3"]
                  ["pic 1" "pic 2" "pic 3"])]
            [:div {:class "large-12 columns"}
             [:h1 "My Friends' Bank Accounts"]]]))

(defn- cute-pic-space
  [title image]
  (hc/html
    [:div.large-4.column
     [:h4 title]
     [:p image]]))



(defn home
  [] (hp/html5 (head)
               [:body {:class "row"}
                (header)
                (body)
                (footer)
                (asset-js)
                ]))

(defn contact
  [] (hp/html5 (head)
               [:body {:class "row"}
                (header)
                [:center
                 [:p "Contact me plzzzz"]]
                (footer)
                (asset-js)]))

(defn read-article-file
  [filename]
  (->> (read-string (slurp (str "resources/data/" filename)))))

(defn show-all-articles
  [id title]
  (hc/html [:li
            [:a {:href (str "/article/" id)}
             title]]))

(defn show-one-article
  [id]
  (-> (read-article-file "articles.edn")
       (get-in [(dec (read-string id))])))

(defn articles
  []
  (hp/html5 (head)
            [:body {:class "row"}
             (header)
             [:ul
              (->> (read-article-file "articles.edn")
                   (map #(show-all-articles (% :article-id) (% :title)) ))]
             (footer)
             (asset-js)]))

(defn article
  [id]
  (let [the-article (show-one-article id)]
    (hp/html5 (head)
              [:body {:class "row"}
               (header)
               [:div {:class "large-6 columns"}
                [:h3  (str "Title: " (the-article :title))]
                [:h4 (str "Number: " (the-article :article-id))]
                [:h5 (str "Content: \n" (the-article :text))]]
               (footer)
               (asset-js)])))



