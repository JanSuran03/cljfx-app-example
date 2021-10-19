(ns cljfx-game.events.core)

(defmulti ^{:arglists '([{:fx/keys [event-type]}])}
          event-handler
          (fn [{:fx/keys [event-type]}]
            event-type))

(defmethod event-handler :default
  [{:fx/keys [event-type event context]}]
  (println (str "No event handler registered for event: " event-type
                "\nin event: " event))
  {:context context})