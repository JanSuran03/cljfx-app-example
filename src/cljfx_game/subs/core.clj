(ns cljfx-game.subs.core
  (:require [cljfx.api :as fx]
            [cljfx-game.db.get :as db-get]
            [cljfx-game.db.paths :as paths]))

(defn stage [ctx]
  (fx/sub-val ctx db-get/stage))

(defn canvas [ctx]
  (fx/sub-val ctx db-get/canvas))

(defn objects [ctx]
  (-> ctx canvas :objects))

(defn rect-being-drawn [ctx]
  (-> ctx canvas :rect-coords))

(defn triangle [ctx]
  (-> ctx canvas :triangle))