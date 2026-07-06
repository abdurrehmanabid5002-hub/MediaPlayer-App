# MediaPlayerApp

A lightweight, modern Android application written in Kotlin that demonstrates how to stream audio from a remote URL using Android's native `MediaPlayer` API.

---

## 📱 Features

- **Audio Streaming**: Streams MP3 files directly from a remote server (defaults to a demo track from SoundHelix).
- **Interactive Playback Controls**:
  - **Play/Autoplay**: Connects to the streaming API, buffers the track, and begins playback automatically when prepared.
  - **Pause**: Temporarily suspends audio playback, allowing resume from the same position.
  - **Stop**: Ends playback, releases `MediaPlayer` resources, resets the state, and clears the progress seek bar.
- **Dynamic SeekBar Sync**: Fully interactive SeekBar that updates in real-time as the audio plays, using a background handler. Users can also drag the slider to seek to specific track times.
- **Clean Architecture & UI**: Centered UI design utilizing standard Android resources, XML layouts, and custom vector icons.

---

## 🛠️ Technology Stack

- **Platform**: Android
- **Programming Language**: Kotlin
- **Build Tool**: Gradle with Kotlin DSL (`build.gradle.kts`)
- **Min SDK**: API Level 24 (Android 7.0)
- **Target SDK**: API Level 36
- **Architecture/Frameworks**:
  - Jetpack Compose (configured in Gradle dependencies)
  - AndroidX & AppCompat framework
  - Material Design components
  - Android Native `MediaPlayer` API for audio processing

---

## 📁 Key File Structure

- [MainActivity.kt](file:///d:/My%20All%20Projects/MediaPlayerApp/app/src/main/java/com/example/mediaplayer/MainActivity.kt) - The main controller responsible for initializing the `MediaPlayer`, binding UI elements, setting click listeners, updating progress bars, and handling lifecycle cleanup.
- [activity_main.xml](file:///d:/My%20All%20Projects/MediaPlayerApp/app/src/main/res/layout/activity_main.xml) - The XML-based layout that structures the application interface, detailing the text headers, album art image, seekBar, and control action buttons.
- [AndroidManifest.xml](file:///d:/My%20All%20Projects/MediaPlayerApp/app/src/main/AndroidManifest.xml) - Declares internet usage permissions (`android.permission.INTERNET`), application-wide metadata, themes, and configuration flags for cleartext network traffic.
- [build.gradle.kts (App-level)](file:///d:/My%20All%20Projects/MediaPlayerApp/app/build.gradle.kts) - App-level dependencies, compilation properties, package namespace, and build configurations.

---

## 🚀 Getting Started

### Prerequisites
* Android Studio (Koala or later recommended)
* Android SDK (API Level 24+)
* Active Internet connection on the testing device/emulator to load the remote audio track.

### Running the App
1. Clone or open the project in **Android Studio**.
2. Sync the project Gradle files.
3. Set up a physical Android device with USB debugging enabled or configure an Android Virtual Device (AVD) Emulator.
4. Click **Run** in Android Studio to build and launch the application.
