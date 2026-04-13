# DotTime — Life in Dots

Minimal Android home screen widget that visualizes time progress using dot indicators.

```
 2026  ●●●○○○○○○○○○  28%
  APR  ●●●●●○○○○○○○  40%
  SUN  ●●●●●●●○○○○○  58%
  14h  ●●●●○○○○○○○○  33%
```

## What it does

Four rows show how much of your **year**, **month**, **day**, and **hour** has passed:

- **White dots** — time elapsed
- **Red dot** — where you are now
- **Gray dots** — time remaining

## Features

- Pure black AMOLED background
- Monospace typography
- No internet, no accounts, no tracking
- Auto-refresh every 15 minutes via WorkManager
- Jetpack Glance widget (modern RemoteViews)

## Build

```bash
# Requires Android SDK + JDK 17
./gradlew assembleDebug

# Signed release bundle
./gradlew bundleRelease
```

## Tech Stack

- Kotlin
- Jetpack Glance 1.1.1
- WorkManager 2.9.1
- Min SDK 26 (Android 8.0)
- Target SDK 34

## License

MIT
