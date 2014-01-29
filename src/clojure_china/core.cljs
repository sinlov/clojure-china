(ns clojure-china.core
  (:require [reagent.core :as reagent :refer [atom]]
            [clojure-china.widgets.app :as app :refer [route app-widget]]
            [clojure-china.widgets.tags]))

(defn ^:export run []
  (reagent/render-component
   [app-widget]
   (.getElementById js/document "app")))

(defn set-route!
  ([name] (set-route! name {}))
  ([name params]
     (reset! app/route {:name name :params params})))

(def routes
  #js {"/" #(set-route! :root)
       "/tags/:id" #(set-route! :tags-show {:id %})})

(.. (js/Router app/routes)
    (configure #js {:notfound #(set-route! :404)})
    (init "/"))

(run)
