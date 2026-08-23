# Migration Plan: Flutter to Native Android (Kotlin + Jetpack Compose)

This plan outlines the steps to migrate the "RRB JE CBT-1 AI Mock Test" application from Flutter to a native Android application using modern Jetpack Compose, Supabase, and Gemini AI.

## User Review Required

> [!IMPORTANT]
> This will involve deleting all Flutter-related files (`lib/`, `pubspec.yaml`, `ios/`, etc.) and restructuring the project into a standard Android Gradle project.

## Proposed Changes

### [Cleanup]
- Delete Flutter directories: `lib/`, `ios/`, `linux/`, `macos/`, `windows/`, `web/`, `test/`.
- Remove Flutter config files: `pubspec.yaml`, `analysis_options.yaml`, `.metadata`.

### [Project Setup]
- **Root Build Configuration**: Initialize `build.gradle.kts` (or `build.gradle`) and `settings.gradle.kts`.
- **App Module Configuration**: Set up `app/build.gradle.kts` with dependencies for:
    - Jetpack Compose (UI)
    - Hilt (Dependency Injection)
    - Supabase (Backend/Auth)
    - Google Generative AI (Gemini)
    - Razorpay (Payments)
    - Room (Offline Practice)
    - Retrofit/Ktor (Networking)

### [Architecture & Modules]
- **Core Module**: Theme, Constants, Common Composables.
- **Data Module**: Supabase Client, Room Database, Repositories.
- **Auth Module**: Login/Signup screens using Compose.
- **Exam Module**: CBT-1 Exam Engine (ViewModel, Timer, Bilingual state, Calculator).
- **Tutor Module**: Gemini AI integration with Chat UI.
- **Subscription Module**: Razorpay implementation.

## Verification Plan

### Automated Tests
- Unit tests for Exam scoring logic.
- Repository tests for Supabase data fetching.

### Manual Verification
- Verify Login flow with Supabase.
- Verify Exam Engine bilingual toggle and calculator.
- Verify Razorpay payment callback.
