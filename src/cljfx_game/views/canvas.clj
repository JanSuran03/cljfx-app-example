(ns cljfx-game.views.canvas
  (:require [cljfx-game.events.canvas.draw :as draw]
            [cljfx-game.subs.core :as subs])
  (:import (javafx.scene.canvas Canvas)
           (javafx.scene.paint Color)))

(defn canvas [{:keys [fx/context width height]}]
  (let [canvas-objects (subs/objects context)
        {x :x y :y r-width :width r-height :height} (subs/rect-being-drawn context)
        {:keys [offset-x offset-y] :or {offset-x 0 offset-y 0}} (subs/triangle context)]
    {:fx/type :canvas
     :width   width
     :height  height
     :draw    (fn [^Canvas canvas]
                (as-> (.getGraphicsContext2D canvas) g-ctx
                      (doto g-ctx
                        (.clearRect 0 0 1200 675)
                        (.setStroke Color/BLACK)
                        (.strokePolygon (double-array (map #(+ % offset-x) [100 150 200]))
                                        (double-array (map #(+ % offset-y) [150 50 150]))
                                        3                   ; A TRIANGLE
                                        ))
                      (reduce draw/draw
                              g-ctx
                              canvas-objects)
                      (if x
                        (doto g-ctx (.setFill Color/LAWNGREEN)
                                    (.fillRect x y r-width r-height))
                        g-ctx)))}))