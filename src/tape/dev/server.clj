(ns tape.dev.server
  "A Ring handler for Figwheel to serve WebJars assets."
  (:require [ring.middleware.resource :as resource]
            [ring.util.response :as response]))

(defn not-found [_]
  (response/not-found "Not found"))

(def handler
  (-> not-found
      (resource/wrap-resource "/META-INF/resources")))
