(ns clojure-china.widgets.topics
  (:require [secretary.core :as secretary]
            [clojure-china.widgets.app :as app])
  (:require-macros [secretary.macros :refer [defroute]]))

(defroute "/topics" []
  (app/set-route! :topics))

(defroute "/topics/page/:page" {:as params}
  (app/set-route! :topics params))

(defn get-topics [page]
  (let [topics (case page
                 1
                 [{:id 1 :title "AAA" :author "Cooky" :category "ClojureScript" :created-at (js/Date.)}
                  {:id 2 :title "BBB" :author "Cooky"}]
                 [{:id 3 :title "CCC" :author "Cooky"}])]
    [topics 2]))

(defn from-now [date]
  "1天前")

(defn topic-item [{{:keys [title category author created-at]} :topic}]
  [:li.topic-item
   [:a.title {:href "#"} title]
   (if category [:span.categroy category])
   [:a {:href (str "#/~" author)} author] "发表于" [:span.date (from-now created-at)]])

(defn paginator [{:keys [page total-pages topics]}]
  (let [title (str "共" total-pages "页")
        prev? (> page 1)
        next? (< page total-pages)]
    [:div.paginator
     (if prev?
       [:a.tiny.radius.button
        {:href (str "#/topics/page/" (dec page))
         :title title
         :dangerouslySetInnerHTML {:__html "&laquo;"}}])
     (if (and prev? next?) [:br])
     (if next?
       [:a.tiny.radius.button
        {:href (str "#/topics/page/" (inc page))
         :title title
         :dangerouslySetInnerHTML {:__html "&raquo;"}}])]))

(defn parse-page [page]
  (let [i (js/parseInt page 10)]
    (if (js/isNaN i) 1 i)))

(defmethod app/render-route :topics [{{:keys [page] :or {page "1"}} :params}]
  (let [page (parse-page page)
        [topics total-pages] (get-topics page)]
    {:sider [paginator {:page page :total-pages total-pages :topics topics}]
     :content [:div.ui.stackable.grid
               [:div.fourteen.wide.column
                [:ul#topic-list
                 (for [topic topics]
                   [topic-item {:topic topic, :key (:id topic)}])]]]}))
