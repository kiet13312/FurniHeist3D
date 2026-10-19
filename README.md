# FurniHeist3D — 3D version

The game bundles Three.js r170 and GLTFLoader locally in `vendor/`; it does not need to fetch the 3D engine from a CDN. The Android app serves packaged files from a local HTTPS origin so module imports and GLB requests work in Android WebView without internet access.

## Gameplay
- Explore five guarded furniture zones in order: Mouse, Cat, Baby, Mom and Dad.
- Return furniture to the safe base to sell it for coins or keep it to decorate the base.
- Place, move, rotate and store furniture inside the base; placed furniture earns passive income.
- Train on the treadmill, upgrade it, buy speed shoes and expand the carry bag.
- Zone Speed gates and monster Speed values are randomized from 10 to 1,000,000 and saved.
- Mobile joystick, interaction button, jump, camera orbit and pinch zoom.

## Controls
- Desktop: WASD / arrow keys, Space to jump, Shift to sprint, E to interact.
- Desktop camera: drag to orbit and mouse wheel to zoom.
- Mobile: joystick to move, INTERACT to pick up/sell/train; drag to orbit and pinch to zoom.

## Build
Push to `main` starts both the web and Android APK workflows. The latest successful APK artifact is named `FurniHeist3D-3D-APK`; the web artifact is named `FurniHeist3D-web-build`. Three.js is MIT-licensed; see `vendor/THREE-LICENSE.txt`.
