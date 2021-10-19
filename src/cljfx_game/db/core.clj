(ns cljfx-game.db.core
  (:require [cljfx.api :as fx]
            [clojure.core.cache :as cache]))

(def initial-db {:stage  {:showing true
                          :title   "Cljfx example"
                          :opacity 1}
                 :canvas {:objects  []
                          :triangle {:offset-x 200
                                     :offset-y 180}}})

(def state*
  (atom
    (fx/create-context
      initial-db
      cache/lru-cache-factory)))

(defn ctx->state [ctx]
  (:cljfx.context/m ctx))