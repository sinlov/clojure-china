(ns clojure-china.widgets.app
  (:require [reagent.core :as reagent :refer [atom]]))

(def route (atom nil))

(defmulti render-route
  (fn [{name :name}] name)
  :default :404)

(defmethod render-route :root [route]
  [:h1 "index"])

(defmethod render-route :404 [route]
  [:em "not found"])

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
