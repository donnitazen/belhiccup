(ns belhiccup.routes
  (:require
    [compojure.core :refer [routes GET POST context]]
    [compojure.route :refer [not-found resources]]
    [belhiccup.pages :as page]))

(defn all-routes
  []
  (routes
    (GET "/" req (page/home))
    (resources "public/")
    (not-found "Not found")))