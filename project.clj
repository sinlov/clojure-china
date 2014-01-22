(defproject clojure-china "0.1.0-SNAPSHOT"
  :description "Source of the forum: clojure-china.org"
  :url "http://clojure-china.org"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2138"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [om "0.2.3"]
                 [com.facebook/react "0.8.0.1"]]

  :plugins [[lein-cljsbuild "1.0.1"]]

  :source-paths ["src"]

  :cljsbuild {
    :builds [{:id "clojure-china"
              :source-paths ["src"]
              :compiler {
                :output-to "js/clojure_china.js"
                :output-dir "out"
                :optimizations :none
                :source-map true}}]})
