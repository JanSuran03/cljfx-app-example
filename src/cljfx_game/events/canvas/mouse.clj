(ns cljfx-game.events.canvas.mouse
  (:require [cljfx-game.events.core :as events]
            [cljfx-game.db.update :as db-update]
            [cljfx-game.db.get :as db-get])
  (:import (javafx.scene.paint Color)))

(defmethod events/event-handler :event-type/mouse-pressed
  [{:fx/keys [context event]}]
  (let [x (.getSceneX event)                                ; our starting point for a rectangle being drawn
        y (.getSceneY event)]
    {:context (db-update/canvas context assoc :rect-coords {:startX x
                                                            :startY y
                                                            :width  0
                                                            :height 0})}))

(defmethod events/event-handler :event-type/mouse-dragged
  [{:fx/keys [context event]}]
  (let [{:keys [startX startY]} (:rect-coords (db-get/canvas context))]
    (if startX
      (let [x (.getSceneX event)
            y (.getSceneY event)
            [x width] (if (> x startX)
                        [startX (- x startX)]
                        [x (- startX x)])
            [y height] (if (> y startY)
                         [startY (- y startY)]
                         [y (- startY y)])]
        {:context (db-update/canvas context update :rect-coords assoc
                                    :x x
                                    :y y
                                    :width width
                                    :height height)})
      {:context context})))

(defmethod events/event-handler :event-type/mouse-released
  [{:fx/keys [context event]}]
  (let [{:keys [startX startY]} (:rect-coords (db-get/canvas context))
        x (.getSceneX event)
        y (.getSceneY event)
        radius (+ 30 (rand 50))
        fill (rand-nth [Color/MAGENTA Color/CRIMSON Color/DARKVIOLET Color/DARKBLUE])
        stroke (rand-nth [Color/BLACK Color/SILVER])
        new-ctx (if (and (= x startX)                       ;ENSURE DRAWING RECT DIDN'T BEGIN
                         (= y startY))                      ;DRAWS A CIRCLE IF SO
                  (db-update/canvas context update :objects conj #:object{:type           :filled-circle
                                                                          :x              x
                                                                          :y              y
                                                                          :radius         radius
                                                                          :fill-color     fill
                                                                          :stroke-color   stroke
                                                                          :center-coords? true})
                  context)]
    {:context (db-update/canvas new-ctx dissoc :rect-coords)}))
