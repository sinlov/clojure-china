(ns clojure-china.widgets.tags
  (:require [clojure-china.widgets.app :as app]
            [secretary.core :as secretary])
  (:require-macros [secretary.macros :refer [defroute]]))

(defroute "/tags/:name" {:as params}
  (app/set-route! :tag params))

(defmethod app/render-route :tag [{{:keys [name]} :params}]
  [:h3 name])
