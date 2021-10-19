(ns cljfx-game.events.canvas.keyboard
  (:require [cljfx-game.events.core :as events]
            [cljfx-game.db.update :as db-update]))

(def translate-by 10)

(defn maybe-pop
  "Popping an empty vector throws an error."
  [v]
  (if (seq v)
    (pop v)
    v))

(defmulti ^{:arglists '([context key-name ctrl?])}
          handle-key-pressed
          (fn [_ key-name _]
            key-name))

(defmethod handle-key-pressed :default
  [context _ _]
  context)

(defmethod handle-key-pressed "Left"
  [context _ _]
  (db-update/triangle context update :offset-x - translate-by))

(defmethod handle-key-pressed "Right"
  [context _ _]
  (db-update/triangle context update :offset-x + translate-by))

(defmethod handle-key-pressed "Up"
  [context _ _]
  (db-update/triangle context update :offset-y - translate-by))

(defmethod handle-key-pressed "Down"
  [context _ _]
  (db-update/triangle context update :offset-y + translate-by))

(defmethod handle-key-pressed "Backspace"
  [context _ ctrl?]
  (if ctrl?
    (db-update/canvas context assoc :objects [])
    (db-update/canvas context update :objects maybe-pop)))

(defmethod handle-key-pressed "Delete"
  [context _ ctrl?]
  (if ctrl?
    (db-update/canvas context assoc :objects [])
    (db-update/canvas context update :objects maybe-pop)))

;; Well, this method should not be there, but I really did not want to make a separate file for it.
(defmethod handle-key-pressed "Esc"
  [context _ _]
  (db-update/stage context assoc :showing false))

(defmethod events/event-handler :event-type/key-pressed
  [{:fx/keys [event context]}]
  (let [key-name (.getName (.getCode event))
        ctrl? (.isControlDown event)]
    {:context (handle-key-pressed context key-name ctrl?)}))