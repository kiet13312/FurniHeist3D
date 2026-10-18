# FurniHeist3D

A landscape Android game built around a lightweight 3D-style furniture-heist playground. The game logic and drawing are bundled locally in the APK, so gameplay does not need an internet connection.

## Gameplay
- Explore the plaza and collect furniture.
- Bring furniture to your home to earn coins.
- Place collected furniture in your house; each placed item earns passive coins.
- Upgrade the treadmill up to level 10 and train to run faster.
- Buy speed shoes and a carry-bag upgrade.
- Landscape layout with on-screen movement buttons for touchscreens.

## Build an APK on GitHub
Every push to `main` runs the Android build workflow.

1. Open the repository's **Actions** tab.
2. Open the latest **Build FurniHeist3D Android APK** run.
3. Under **Artifacts**, download `FurniHeist3D-debug-apk`.
4. Extract the ZIP and install `app-debug.apk` on Android. Allow installation from the file manager/browser if Android asks.

You can also trigger the workflow manually from Actions using **Run workflow**.

## Build locally
Use JDK 17, Android SDK Platform 35 and Gradle 8.9, then run:

```sh
gradle :app:assembleDebug
```

APK output: `app/build/outputs/apk/debug/app-debug.apk`.

## Controls
- Desktop: WASD / arrow keys to move; Shift to sprint; E to interact; drag mouse to rotate the view.
- Mobile: use the on-screen movement arrows and action button.
- On the treadmill: hold E or Space to train.

## Scope
This is an offline single-player prototype with a custom canvas renderer. It is not a Roblox project and does not include online multiplayer or cloud saves.
