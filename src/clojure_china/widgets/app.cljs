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

(defn header [sider]
  (let [route-name (:name @route)]
    [:div.large-3.columns
     [:div [:h3 "Clojure China"]]
     [:dl.sub-nav
      [:dd.active
       [:a.current
        {:href "#/topics"
         :class (if (#{:topics :topic} route-name) "active")}
        "社区"]]
      [:dd
       [:a.item
        {:href "#/categories"
         :class (if (#{:categories :category} route-name) "active")}
        "分类"]]
      [:dd
       [:a.item
        {:href "#/users"
         :class (if (#{:users :user} route-name) "active")}
        "会员"]]]
     sider]))

(defn footer []
  [:footer
   "footer"])

(defn app-widget []
  (let [content (render-route @route)
        sider (if (map? content) (:sider content))
        content (if (map? content) (:content content) content)]
    [:div.row
     (header sider)
     [:div#content.large-9.columns
      content]]))
