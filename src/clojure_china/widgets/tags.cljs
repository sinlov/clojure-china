(ns clojure-china.widgets.tags
  (:require [clojure-china.widgets.app :as app])
  (:require-macros [secretary.macros :refer [defroute]]))

(defroute "/tags/:id" {:as params}
  (app/set-route! :tags-show params))

(defmethod app/render-route :tags-show [{{id :id} :params}]
  [:h3 id])
