# Faculty Attendance — Face Recognition System

An Android application for marking faculty attendance using facial recognition, built with Android Studio (Java), Firebase, and ML Kit Face Detection.

## Features

- **Kiosk Mode** — touchscreen-friendly face scan for faculty to mark their own attendance without logging in
- **Admin Mode** — secure login for administrators to register faculty, view today's attendance, and access attendance history
- **Face Recognition** — uses ML Kit on-device face detection with custom feature extraction stored in Firebase
- **Real-time Database** — attendance records synced live via Firebase Realtime Database

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Build System | Gradle (Groovy DSL) |
| Backend | Firebase Auth, Firebase Realtime Database |
| Face Detection | Google ML Kit |
| Min SDK | Android 7.0 (API 24) |

## Project Structure

```
app/src/main/java/com/kiet/facultyattendance/
├── MainActivity.java              # Kiosk + Admin login screen
├── activities/
│   ├── SplashActivity.java
│   ├── AdminDashboardActivity.java
│   ├── FaceScanActivity.java      # Kiosk mode face scanning
│   ├── FaceCaptureActivity.java   # Register faculty face
│   ├── RegisterFacultyActivity.java
│   ├── FacultyListActivity.java
│   ├── TodayAttendanceActivity.java
│   └── AttendanceHistoryActivity.java
├── adapters/
│   ├── FacultyAdapter.java
│   └── AttendanceAdapter.java
├── models/
│   ├── Faculty.java
│   └── AttendanceRecord.java
└── utils/
    ├── FaceFeatureExtractor.java  # Core face recognition logic
    ├── ActivityUtils.java
    └── LoadingDialog.java
```

## Setup Instructions

### Prerequisites
- Android Studio Hedgehog or later
- A Firebase project with **Authentication** and **Realtime Database** enabled
- Java 11+

### 1. Clone the repository
```bash
git clone https://github.com/YOUR_USERNAME/FacultyAttendance.git
cd FacultyAttendance
```

### 2. Configure Firebase
1. Go to [Firebase Console](https://console.firebase.google.com/) and create or open your project
2. Register the Android app with package name `com.kiet.facultyattendance`
3. Download `google-services.json` and place it at `app/google-services.json`
   - A template is provided at `google-services.json.template` for reference

### 3. Set up Firebase Authentication
- Enable **Email/Password** sign-in in Firebase Console → Authentication → Sign-in method
- Create an admin account manually: `admin@kiet.edu.pk` (or update `ADMIN_EMAIL` in `MainActivity.java`)

### 4. Set up Firebase Realtime Database
- Enable Realtime Database in Firebase Console
- Set rules to allow authenticated reads/writes (adjust for production):
```json
{
  "rules": {
    ".read": "auth != null",
    ".write": "auth != null"
  }
}
```

### 5. Build & Run
- Open the project in Android Studio
- Sync Gradle
- Run on a physical device (camera required for face recognition)

## Usage

### Kiosk Mode
Tap **"Scan Attendance"** on the main screen — no login required. The app opens the camera and scans for a registered face.

### Admin Mode
Enter the admin email and password to access the dashboard, where you can:
- Register new faculty (capture face)
- View today's attendance
- View attendance history
- Manage the faculty list

## Security Notes

- `google-services.json` is excluded from version control — never commit it to a public repo
- The admin email is hardcoded in `MainActivity.java` — update it for your institution
- Restrict your Firebase API key in [Google Cloud Console](https://console.cloud.google.com/) to your app's SHA certificate for production use

## Contributors

| Name | GitHub |
|---|---|
| Affan Mirza | [@AffanMirza00](https://github.com/AffanMirza00) |
| Nihal Raza | [@nihalraza03](https://github.com/nihalraza03) |
| Zaid Maju | *(GitHub coming soon)* |

---
