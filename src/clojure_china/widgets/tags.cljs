(ns clojure-china.widgets.tags
  (:require [clojure-china.widgets.app :refer [render-route]]))

(defmethod render-route :tags-show [{{{id :id} :params} :route :as app}]
  [:h3 id])
