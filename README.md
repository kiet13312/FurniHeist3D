# FurniHeist3D

A lightweight browser 3D-style furniture heist game made with plain HTML, CSS, JavaScript and a custom canvas renderer. No Unity, Godot, Three.js, or build step required.

## Gameplay
- Move around the plaza and pick up furniture.
- Bring furniture to your house to earn coins.
- Buy treadmill upgrades from level 1 to level 10.
- Train on the treadmill to increase running speed.
- Buy speed shoes and a larger carry bag.
- Play on desktop with WASD / E or use the mobile controls.

## Run locally
Open `index.html` in a modern browser.

## Build and publish
This is a static browser game, so it does not need a compiler or APK build. The GitHub Actions workflow deploys `index.html` to GitHub Pages whenever `main` changes.

1. Open **Settings → Pages** in this repository.
2. Under **Build and deployment**, choose **GitHub Actions** if it is not already selected.
3. The published game URL is: https://kiet13312.github.io/FurniHeist3D/

## Controls
- **WASD / arrow keys:** move
- **Shift:** sprint
- **E:** pick up / interact
- **Mouse drag:** rotate the camera
- **Mobile:** use the on-screen movement buttons and action button

This is an early playable prototype. Online multiplayer and cross-device cloud saves are not included.
