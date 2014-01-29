(ns clojure-china.widgets.app
  (:require [reagent.core :as reagent :refer [atom]]))

;;; routing api

(def route (atom nil))

(defmulti render-route
  (fn [{name :name}] name)
  :default :404)

(defn set-route!
  ([name] (set-route! name {}))
  ([name params]
     (reset! route {:name name :params params})))

(defmethod render-route :404 [route]
  [:em "not found"])

;;; app layout

(defn header []
  [:header
   [:h1 "Clojure China"]
   [:h4 (clj->js (str @route))]])

(defn content []
  [:div#content
   (render-route @route)])

(defn footer []
  [:footer
   "footer"])

(defn app-widget []
  [:div
   [header]
   [content]
   [footer]])
