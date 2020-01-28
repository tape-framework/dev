(ns tape.dev.server
  "A Ring handler for Figwheel to serve WebJars assets."
  (:require [ring.middleware.resource :as resource]
            [ring.util.response :as response]))

(def handler
  (-> (response/not-found "Not found")
      (resource/wrap-resource "/META-INF/resources")))
