(ns clojure-china.widgets.home
  (:require [secretary.core :as secretary]
            [clojure-china.widgets.app :as app])
  (:require-macros [secretary.macros :refer [defroute]]))

(defroute "/" []
  (app/set-route! :root))

(defmethod app/render-route :root [route]
  [:h1 "index"])
