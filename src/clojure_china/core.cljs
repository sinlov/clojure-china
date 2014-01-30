(ns clojure-china.core
  (:require [goog.events :as events]
            [reagent.core :as reagent :refer [atom]]
            [secretary.core :as secretary]
            [clojure-china.widgets.app :as app]
            [clojure-china.widgets.home]
            [clojure-china.widgets.topics]
            [clojure-china.widgets.tags])
  (:import [goog History]
           [goog.history EventType]))

(defn ^:export run []
  (reagent/render-component
   [app/app-widget]
   (.getElementById js/document "app")))

(doto (History.)
  (events/listen EventType.NAVIGATE
                 (fn [e] (secretary/dispatch! (.-token e))))
  (.setEnabled true))

(run)
