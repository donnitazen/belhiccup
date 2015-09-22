(ns belhiccup.routes
  (:require
    [compojure.core :refer [routes GET POST context]]
    [compojure.route :refer [not-found resources]]
    [belhiccup.pages :as page]
    [belhiccup.articles :as art]
    [noir.response :as resp]))

(defn all-routes
  []
  (routes
    (GET "/" req (page/home))
    (GET "/contact" req (page/contact))
    (GET "/articles" req (page/articles))
    (GET "/article/:id" req
      (page/article (get-in req [:params :id])))
    (POST "/add-article" req
      (let [new-id (art/add-article (req :params))]
        (resp/redirect (str "/article/" new-id))))
    (resources "public/")
    (not-found "Not found")))
