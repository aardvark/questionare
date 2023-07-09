(ns questionare.core
  (:require
   [reagent.core :as r]
   [reagent.dom :as d]
   [clojure.string :as s]))


;; -------------------------
;; Static data

(def answers
  {:q1 "388289"
   :q2 "547312955603"
   :q4 "16995796"
   :q5 "6551716"
   :q6 "9253688"})

;; -------------------------
;; Components
(defn input-with-atom
  [value]
  [:input
   {:type "text" :value @value
    :on-change #(reset! value (-> % .-target .-value))}])

(defn question-with-check
  [q a]
  (let [answer (r/atom "")]
    [(fn []
        [:p q
        [input-with-atom answer]
        " Result: " (if (= (s/trim @answer) a)
                      "Correct"
                      "Incorrect")])]))

;; -------------------------
;; Views

(defn home-page []
  [:div
   (question-with-check "Answer 1:" (:q1 answers))
   (question-with-check "Answer 2:" (:q2 answers))
   (question-with-check "Answer 3:" (:q3 answers))
   (question-with-check "Answer 4:" (:q4 answers))
   (question-with-check "Answer 5:" (:q5 answers))
   (question-with-check "Answer 6:" (:q6 answers))])

;; -------------------------
;; Initialize app

(defn mount-root []
  (d/render [home-page] (.getElementById js/document "app")))

(defn ^:export init! []
  (mount-root))