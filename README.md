# FurniHeist3D — 3D version

The game renders with Three.js/WebGL and loads the supplied 17 GLB files: the full map, player, treadmill, 9 furniture models and 5 monsters. The APK workflow reconstructs the packed model bundle into the Android asset directory before compiling, so the GLB files are included in the APK.

## Gameplay
- Explore five guarded furniture zones.
- Pick furniture up, carry it home and sell it for coins.
- Place furniture in the house to earn passive income.
- Upgrade the treadmill to level 10, buy speed shoes and expand the carry bag.
- Touch joystick, interact button and drag-to-rotate camera for mobile play.

## Build APK
Every push to `main` starts **Build FurniHeist3D 3D Android APK**. Open the repository's **Actions** tab and download the `FurniHeist3D-3D-APK` artifact from the latest successful run.

The renderer loads the Three.js ES modules from jsDelivr, so the device needs internet access to load the engine. The 17 GLB assets are packaged locally in the APK.

## Controls
- Desktop: WASD / arrow keys, Shift to sprint, E to interact, drag to rotate.
- Mobile: use the joystick and INTERACT button.
- Hold INTERACT while on the treadmill to train.
