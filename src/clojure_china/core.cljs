(ns clojure-china.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [clojure-china.widgets.app :refer [app-widget]]
            [clojure-china.widgets.tags]))

(enable-console-print!)

(def app-state (atom {}))

(om/root app-state
         app-widget
         (.getElementById js/document "app"))

(defn set-route!
  ([name] (set-route! name {}))
  ([name params]
     (swap! app-state assoc :route {:name name :params params})))

(def routes
  #js {"/" #(set-route! :root)
       "/tags/:id" #(set-route! :tags-show {:id %})})

(.. (js/Router routes)
    (configure #js {:notfound #(set-route! :404)})
    (init "/"))
