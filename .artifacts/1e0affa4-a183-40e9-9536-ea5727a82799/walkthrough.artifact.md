# RRB JE CBT-1 AI Mock Test - Android App Walkthrough

I have initialized the Flutter project and implemented the core modules based on the unified Supabase backend architecture.

## 🚀 Accomplishments

### 1. Unified Backend & Authentication
- **Supabase Integration**: Set up `supabase_flutter` for shared authentication (Web & Mobile).
- **Profile Sync**: Implemented real-time listener for `profiles` table to sync Pro entitlements and progress.
- **Auth Wrapper**: Automatically toggles between `LoginScreen` and `HomeScreen` based on session state.

### 2. Authentic CBT-1 Exam Engine
- **Exam UI**: 90-minute timer, question navigation, and subject categories.
- **Bilingual Toggle**: 1-Tap switch between English and Hindi content.
- **Question Palette**: Color-coded grid for Answered, Marked, and Not Answered questions.
- **Virtual Calculator**: Scientific calculator with \u221A, x\u00b2, and % functions.

### 3. AI Pedagogical Tutor
- **Gemini 1.5 Flash**: Integrated Google\u0027s latest AI for sub-45s doubt solving.
- **Interactive Chat**: Dedicated tutor screen with context-aware explanation capabilities.

### 4. Razorpay Payments
- **Pro Pass Integration**: Razorpay SDK service for ₹99/₹299 plans.
- **Entitlement Sync**: Automatic profile update (`is_pro = true`) and subscription logging in Supabase upon successful payment.

## 🛠 Project Structure
- `lib/core`: Constants and Global configurations.
- `lib/features`: Feature-based modules (Auth, Exam, Profile, Tutor).
- `lib/services`: External SDK integrations (Supabase, Gemini, Razorpay).
- `lib/models`: Data structures for Questions, Attempts, and Profiles.

## 📋 Next Steps for Developer

### A. Environment Setup
Update the [.env](file:///G:/TechQ-Labs/android-app/rrb-self-prac-mob/.env) file with your production keys:
- `SUPABASE_URL` \u0026 `SUPABASE_ANON_KEY`
- `GEMINI_API_KEY`
- `RAZORPAY_KEY_ID`

### B. Android Configuration
1. **Razorpay**: Add the following to `android/app/build.gradle`:
   ```gradle
   dependencies {
       implementation \u0027com.razorpay:checkout:1.6.33\u0027
   }
   ```
2. **Supabase Auth**: Configure deep links in `AndroidManifest.xml` to handle password resets and email confirmations.

### C. Backend Requirements
Ensure the PostgreSQL schema in Supabase matches the defined structure:
- `public.profiles`
- `public.subscriptions`
- `public.questions`
- `public.attempts`
