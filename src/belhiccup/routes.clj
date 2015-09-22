(ns belhiccup.routes
  (:require
    [compojure.core :refer [routes GET POST context]]
    [compojure.route :refer [not-found resources]]
    [belhiccup.pages :as page]))

(defn all-routes
  []
  (routes
    (GET "/" req (page/home))
    (GET "/contact" req (page/contact))
    (GET "/articles" req (page/articles))
    (GET "/article/:id" req
      (page/article (get-in req [:params :id])))
    (resources "public/")
    (not-found "Not found")))