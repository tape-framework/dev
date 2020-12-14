(ns tape.dev.repl
  (:require [cider.piggieback :as piggieback]
            [cljs.repl.browser :as browser]))

(defn repl []
  (piggieback/cljs-repl (browser/repl-env)))
