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
                [:a {:href "#"} "Logo"]]]
              ]
             [:section
              [:ul {:class "left"}
               [:li
                [:a {:href "#"} "Second nav"]]]
              ]]]))

(defn- asset-js
  []
  (hc/html (hp/include-js "/js/vendor/jquery.js")
           (hp/include-js "/js/foundation.min.js")
           (hp/include-js "/js/app.js")))

(defn- footer
  []
  (hc/html [:footer {:class ".two.columns"}
            [:hr]
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

