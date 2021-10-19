(ns cljfx-game.core
  (:require [cljfx.api :as fx]
            [cljfx-game.db.core :as db]
            [cljfx-game.events.core :as events]
            [cljfx-game.views.core :as views]
            cljfx-game.events.load))

(fx/create-app db/state*
               :event-handler events/event-handler
               :co-effects {:fx/context (fx/make-deref-co-effect db/state*)}
               :effects {:context (fx/make-reset-effect db/state*)}
               :desc-fn (fn [_] {:fx/type views/root}))