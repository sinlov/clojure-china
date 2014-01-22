(ns clojure-china.widgets.app
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer [html] :include-macros true]))

(defmulti render-route
  (fn [{{name :name} :route}] name)
  :default :404)

(defmethod render-route :root [app]
  [:h1 "index"])

(defmethod render-route :404 [app]
  [:em "not found"])

(defn header [app]
  [:header
   [:h1 "Clojure China"]])

(defn content [app]
  [:div#content
   (render-route app)])

(defn footer [app]
  [:footer
   "footer"])

(defn app-widget [app]
  (om/component
   (html
    [:div
     (header app)
     (content app)
     (footer app)])))
