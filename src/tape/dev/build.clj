(ns tape.dev.build
  (:require [me.raynes.fs :as fs]
            [cljs.repl.browser :as browser]
            [figwheel.main.api :as api]
            [cider.piggieback :as piggieback]))

(defn pig
  "Run a plain cljs repl with piggieback."
  []
  (piggieback/cljs-repl (browser/repl-env)))

(defn fig
  "Run Figwheel with the project-level dev.cljs.edn file."
  []
  (api/start "dev"))

(defn big
  "Run Figwheel with the project-level dev.cljs.edn file & piggieback."
  []
  (api/start {:mode :serve} "dev")
  (piggieback/cljs-repl (api/repl-env "dev")))

(defn auto
  "Run Figwheel with discovered configuration and `m` overrides."
  ([] (auto {}))
  ([m]
   (let [watch?  #{"src" "resources" "dev/src" "dev/resources" "test" "target"}
         dirs    (->> (fs/list-dir fs/*cwd*)
                      (filter fs/directory?)
                      (map fs/name)
                      (filterv watch?))
         options {:main            (symbol (fs/name fs/*cwd*) "core")
                  :devcards        true
                  :closure-defines {"re_frame.trace.trace_enabled_QMARK_" true}
                  :preloads        '[devtools.preload
                                     day8.re-frame-10x.preload]}
         config  {:watch-dirs   dirs
                  :ring-handler 'tape.dev.server/handler}
         build   (merge {:id "dev", :options options, :config config} m)]
     (api/start build)
     (api/cljs-repl (:id build)))))
