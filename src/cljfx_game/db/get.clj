(ns cljfx-game.db.get
  (:require [cljfx-game.db.core :as db]
            [cljfx-game.db.paths :as paths]))

(defn get-in-ctx [ctx path]
  (if (contains? ctx :cljfx.context/m)
    (get-in (db/ctx->state ctx) path)
    (get-in ctx path)))

(defn stage [ctx]
  (get-in-ctx ctx paths/stage))

(defn canvas [ctx]
  (get-in-ctx ctx paths/canvas))

(defn triangle [ctx]
  (get-in-ctx ctx paths/triangle))