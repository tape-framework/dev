(ns tape.dev.repl
  (:require [cljs.repl :as repl]
            [cljs.repl.browser :as browser]))

(defn repl []
  (repl/repl (browser/repl-env)))
