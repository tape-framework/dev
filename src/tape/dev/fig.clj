(ns tape.dev.fig
  (:require [figwheel.main.api :as api]
            [me.raynes.fs :as fs]))

(defn dev
  "Run Figwheel with the project-level dev.cljs.edn file."
  []
  (api/start "dev")
  (api/cljs-repl "dev"))

(defn auto
  "Run Figwheel with discovered configuration and `m` overrides."
  ([] (auto {}))
  ([m]
   (let [watch?  #{"src" "resources" "dev" "dev-resources" "test"
                   "test-resources" "target"}
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
                  :ring-handler 'tape.dev.server/app}
         build   (merge {:id "auto", :options options, :config config} m)]
     (api/start build)
     (api/cljs-repl (:id build)))))
