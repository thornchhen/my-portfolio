# My Portfolio — Compose Multiplatform

Android developer portfolio built with Kotlin and Compose Multiplatform for WebAssembly.

## Run locally

```bash
gradle :composeApp:wasmJsBrowserDevelopmentRun
```

## Build

```bash
gradle :composeApp:wasmJsBrowserDistribution
```

Every push to `main` builds and deploys the website with GitHub Actions.
