(ns cljfx-game.db.assoc
  (:require [cljfx.api :as fx]
            [cljfx-game.db.paths :as paths]))

(defn assoc-in-context [ctx path val]
  (fx/swap-context ctx assoc-in path val))

(defn stage [ctx val]
  (assoc-in-context ctx paths/stage val))

(defn canvas [ctx val]
  (assoc-in-context ctx paths/canvas val))

(defn triangle [ctx val]
  (assoc-in-context ctx paths/triangle val))