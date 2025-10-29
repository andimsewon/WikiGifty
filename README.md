# WikiGifty

An Android app that demonstrates a clean Firebase Authentication flow with basic profile storage using Firebase Realtime Database. The project focuses on a simple, reliable login/registration experience, English-only UI strings, and small UX touches like loading indicators and input validation.

---

## Features (Implemented)

- Authentication
  - Email/Password Registration and Login (Firebase Auth)
  - Session persistence (auto-route on app start)
  - Logout from Main screen
- Profile
  - Store basic profile data on sign-up (name, birth date, phone, email) in Realtime Database
  - View profile data after sign-up or from Main screen
- UX/Quality
  - English-only strings via resources
  - Loading indicators and disabled buttons during async operations
  - Input validation: email format, password length, birth date (YYYY-MM-DD), phone digits length
  - Hide keyboard on submit
  - Splash routes to Login or Main based on auth state
- App setup
  - Firebase App initialization via `Application` class (`WikiGiftyApp`)
  - Realtime Database offline persistence enabled
  - INTERNET permission added

---

## Not in scope (yet)

- Friends system, tags, public/private wish lists
- Profile images / Glide / Firebase Storage
- MVVM restructuring

These can be added later as incremental features.

---

## Tech Stack

- Kotlin, XML layouts
- Firebase Authentication, Firebase Realtime Database
- Gradle Kotlin DSL

---

## Project Structure (high level)

- `app/src/main/java/com/example/wikigifty/`
  - `WikiGiftyApp.kt` – Firebase init + DB persistence
  - `SplashActivity.kt` – Route based on current user
  - `LoginActivity.kt` – Sign-in with loading state and validation
  - `RegisterActivity.kt` – Sign-up, profile save to DB, validation
  - `MainActivity.kt` – Logged-in landing, profile navigation, logout
  - `ProfileActivity.kt` – Read and render profile from DB
- `app/src/main/res/layout/` – Activity XML layouts
- `app/src/main/res/values/strings.xml` – English-only strings

---

## Requirements

- Android Studio latest (Giraffe/Koala+)
- Android SDK: minSdk 24, targetSdk 34
- Java/Kotlin: JVM target 11

---

## Setup & Run

1) Clone
```bash
git clone https://github.com/andimsewon/WikiGifty.git
cd WikiGifty
```

2) Firebase configuration
- Create a Firebase project and enable Authentication (Email/Password) and Realtime Database.
- Download `google-services.json` and place it in `app/`.
- Ensure Realtime Database rules allow authenticated read/write for testing (adjust as needed).

3) Build & run
- Open the project in Android Studio and run on a device/emulator.

---

## Gradle/Plugins

- Firebase Bill of Materials (BOM) manages Firebase versions
- Applied `com.google.gms.google-services` plugin

Key dependencies are declared in `app/build.gradle.kts`:
- `platform("com.google.firebase:firebase-bom:...")`
- `com.google.firebase:firebase-auth`
- `com.google.firebase:firebase-database`

---

## Screens (current)

- Splash: decides Login vs Main
- Login: email/password sign-in, link to Register
- Register: collect basic profile data and save to DB
- Main: show simple welcome, navigate to Profile, logout
- Profile: read and display user info

---

## Notes

- `google-services.json` is intentionally git-ignored and must be provided locally.
- DB persistence is enabled; first load requires network, subsequent reads may use cache.

---

## Roadmap (suggested)

- ViewBinding adoption and MVVM structure
- Profile photo (Firebase Storage) and Glide
- Friends/tags and wish list features
- Better error handling and retry patterns
- Android 12+ SplashScreen API

---

## License

MIT
