(ns cljfx-game.views.core
  (:require [cljfx-game.views.canvas :as canvas]
            [cljfx-game.subs.core :as subs]))

(defn root [{:keys [fx/context]}]
  (let [{:keys [showing title opacity]} (subs/stage context)]
    {:fx/type :stage
     :showing showing
     :title   title
     :opacity opacity
     :width   1200
     :height  675
     :scene   {:fx/type           :scene
               :on-mouse-pressed  {:fx/event-type :event-type/mouse-pressed}
               :on-mouse-dragged  {:fx/event-type :event-type/mouse-dragged}
               :on-mouse-released {:fx/event-type :event-type/mouse-released}
               :on-key-pressed    {:fx/event-type :event-type/key-pressed}
               :root              {:fx/type  :v-box
                                   :children [{:fx/type canvas/canvas
                                               :width   1200
                                               :height  675}]}}}))