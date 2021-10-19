(ns cljfx-game.events.canvas.draw
  (:import (javafx.scene.canvas GraphicsContext)))

(defmulti draw (fn [_ {:object/keys [type]}]
                 type))

(defn center-coords->top-left-corner [x y width height]
  [(- x (/ width 2))
   (- y (/ height 2))])

(defmethod draw :filled-rectangle
  [^GraphicsContext g-ctx {:object/keys [x y width height center-coords? fill-color]}]
  (let [[x y] (if center-coords?
                (center-coords->top-left-corner x y width height)
                [x y])]
    (as-> g-ctx g-ctx
          (if fill-color
            (doto g-ctx (.setFill fill-color))
            g-ctx)
          (doto g-ctx (.fillRect x y width height)))))

(defmethod draw :filled-circle
  [^GraphicsContext g-ctx {:object/keys [x y radius fill-color center-coords?]}]
  (let [[x y] (if center-coords?
                (center-coords->top-left-corner x y radius radius)
                [x y])]
    (as-> g-ctx g-ctx
          (if fill-color
            (doto g-ctx (.setFill fill-color))
            g-ctx)
          (doto g-ctx (.fillOval x y radius radius)))))