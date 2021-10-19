(ns cljfx-game.db.update
  (:require [cljfx.api :as fx]
            [cljfx-game.db.paths :as paths]))

(defn update-in-context [ctx path f args]
  (apply (partial fx/swap-context ctx update-in path f) args))

(defn stage [ctx f & args]
  (update-in-context ctx paths/stage f args))

(defn canvas [ctx f & args]
  (update-in-context ctx paths/canvas f args))

(defn triangle [ctx f & args]
  (update-in-context ctx paths/triangle f args))