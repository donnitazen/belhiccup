(ns belhiccup.pages
  (:require
    [hiccup.core :as hc]
    [hiccup.page :as hp]
    [belhiccup.articles :as art]))

(defn- head
  [title]
  (hc/html [:head
            [:title title]
            [:meta {:charset "utf-8"}]
            [:link {:rel "stylesheet" :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"}]
            [:link {:rel "stylesheet" :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css"}]
            [:link {:rel "stylesheet" :href "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"}]]))

(defn- header
  []
  (hc/html [:header
            [:ul {:class "nav nav-pills"}
             [:li {:role "presentation"}
              [:a {:href "/"} "Belhiccup"]]
             [:li {:role "presentation"}
              [:a {:href "/articles"} "Articles"]]]]))

(defn- body
  [anything]
  (hc/html [:body {:class "container"}
            [:div {:class "row"}
             (header)]
            anything
            [:div {:class "row"}
             (footer)]]))

(defn- footer
  []
  (hc/html [:div {:class "panel-footer"}
            [:div {:class "col-md-3"}
             [:a {:href "/contact"} "Contact Me"]]]))

(defn home
  []
  (hp/html5 (head "Belhiccup - Home")
            (body [:div {:class "row"}
                   [:div {:class "jumbotron"}
                    [:h1 "This should be a cool jumbo pictures"]]
                   [:p "body"]])))

(defn contact
  []
  (hp/html5 (head "Belhiccup - Contact")
            (body [:div {:class "row"}
                   [:p "just call"]])))

(defn map-all-articles
  []
  (hc/html
    (for [arts (art/read-article-file "articles.edn")
                 ]
             (#(vector :li {:role "presentation"}
                       (vector :a {:href (str "/article/" (% :article-id))} (% :title))) arts))))

(defn articles
  []
  (hp/html5 (head "Belhiccup All Articles")
           (body [:div {:class "row"}
                  [:div {:class "col-md-2"}
                   [:ul {:class "nav nav-pills nav-stacked"}
                    (map-all-articles)]]
                  [:div {:class "col-md-10"}
                   [:h3 "Some cool quotes to seize the day"]
                   [:button {:type "submit"
                             :class "btn btn-default"}
                    "Add an article"]]])))


(defn article
  [id]
  (let [the-article (art/show-one-article id)]
    (println the-article)
    (hp/html5 (head (str "article #" id))
              (body [:div {:class "row"}
                     [:div {:class "col-md-2"}
                      [:ul {:class "nav nav-pills nav-stacked"}
                       (map-all-articles)]]
                     [:div {:class "col-md-10"}
                      [:h2 (str "Title: " (the-article :title))]
                      [:br]
                      [:h4 (str "Article #" (the-article :article-id))]
                      [:br]
                      [:h5 (str "Content: " (the-article :text))]
                      [:br]]]))))





