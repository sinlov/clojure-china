(ns clojure-china.widgets.categories
  (:require [clojure-china.widgets.app :as app]
            [secretary.core :as secretary])
  (:require-macros [secretary.macros :refer [defroute]]))

(defroute "/categories" []
  (app/set-route! :categories))

(defroute "/categories/:name" {:as params}
  (app/set-route! :category params))

(defmethod app/render-route :categories []
  [:h3 "categories"])

(defmethod app/render-route :category [{{:keys [name]} :params}]
  [:h3 name])
