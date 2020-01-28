## README

STATUS: Pre-alpha, in design and prototyping phase.

### About

`tape.dev`

A Swiss-Army Knife of deps and tools for development and testing Tape Framework
modules.

### Install

Add it to your `:dev` or `:test` alias deps.

```clojure
{:aliases {:test {:extra-paths ["test" "target"]
                  :extra-deps {tape/dev {:local/root "../dev"}}}}}
```

### Usage

#### Run Figwheel

In a a Clojure REPL, start Figwheel with:
- `(fig/dev)` a build based on project-level dev.cljs.edn file,
- `(fig/auto m)` a build based on configuration discovered automatically;
  the map `m` is a an optional map of overrides like `{:main 'my.core}`.

#### Serve WebJars assets

Serving WebJars assets is useful in DevCards. To do it add the following entry
in your config:

```clojure
{:ring-handler tape.dev.server/handler}
```

The `tape.tools.server/app` handler uses middleware that serves static
resources from `"/META-INF/resources/webjars"`, so assets can be linked as:

```clojure
[:link {:rel "stylesheet" :href "/webjars/bulma/0.8.0/css/bulma.min.css"}]
```

#### Deps pulled in

This pulls in [re-frame-10x](https://github.com/day8/re-frame-10x) and
[Bulma WebJar](https://github.com/jgthms/bulma) for use in development and
DevCards.

#### License

Copyright © 2019 clyfe

Distributed under the MIT license.
