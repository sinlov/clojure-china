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
  (let [route-name (:name @route)]
    [:div.large-3.columns
     [:div [:h3 "Clojure China"]]
     [:div
      [:a.item
       {:href "#/topics"
        :class (if (#{:topics :topic} route-name) "active")}
       "社区"]
      [:a.item
       {:href "#/categories"
        :class (if (#{:categories :category} route-name) "active")}
       "分类"]
      [:a.item
       {:href "#/users"
        :class (if (#{:users :user} route-name) "active")}
       "会员"]]
     [:code (clj->js (str @route))]
     [:ul {:style {:list-style "none"}}
      [:li [:a "上一页"]]
      [:li [:a "1"]]
      [:li "..."]
      [:li [:a "下一页"]]]]))

(defn content []
  [:div#content.large-9.columns
   (render-route @route)])

(defn footer []
  [:footer
   "footer"])

(defn app-widget []
  [:div.row
   [header]
   [content]])
