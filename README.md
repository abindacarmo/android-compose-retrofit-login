# Mobile Computing: Dynamic Auth System with Jetpack Compose

[![Kotlin](https://img.shields.io/badge/Kotlin-2.2.10-blue.svg)](https://kotlinlang.org/)
[![Compose](https://img.shields.io/badge/Jetpack%20Compose-2024.09.00-green.svg)](https://developer.android.com/jetpack/compose)
[![Retrofit](https://img.shields.io/badge/Networking-Retrofit%202.9.0-red.svg)](https://square.github.io/retrofit/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

This repository contains a modern Android application developed for the **Mobile Computing Practical Course**. The project focuses on building a robust authentication system using **Jetpack Compose** and **Retrofit**, with a specialized implementation to handle dynamic server environments common in laboratory settings.

---

## 🚀 Key Technical Features

### 1. Dynamic Base URL Management
Unlike standard applications with static endpoints, this project implements a dynamic networking layer in `RetrofitClient.kt`. This allows the app to adapt to shifting local server IPs without needing to recompile the APK.
*   **Persistent Configuration**: Server IP is retrieved from `SharedPreferences` (`api_prefs`).
*   **Auto-Sanitization**: The system automatically cleans input strings (removes `http://`, `https://`, and trailing slashes).
*   **Smart Port Handling**: Automatically appends port `:80` if no specific port is provided by the user.

### 2. Network Reliability & Connectivity
*   **WiFi Awareness**: Includes a specialized `isWifiConnected` utility using `ConnectivityManager` to ensure the device is on the correct transport layer before attempting API calls.
*   **Optimized OkHttpClient**: 
    *   30-second timeouts for Connect, Read, and Write operations.
    *   `retryOnConnectionFailure` enabled to handle transient network drops in mobile environments.

### 3. Modern Android Architecture
*   **UI Layer**: 100% Jetpack Compose with Material 3 Design.
*   **Pattern**: MVVM (Model-View-ViewModel) for clean separation of concerns.
*   **Concurrency**: Kotlin Coroutines for non-blocking network requests.

---

## 🛠️ Tech Stack

- **Language**: Kotlin 2.2.10
- **Networking**: Retrofit 2.9.0 & OkHttp 3
- **JSON Parsing**: GSON Converter
- **UI Framework**: Jetpack Compose (BOM 2024.09.00)
- **Dependency Management**: Gradle Kotlin DSL

---

## 📂 Project Structure
```
text com.example.api_design
├── data
│   ├── model            # Data classes for API Request/Response 
│   └── remote 
│       ├── ApiService.kt        # Retrofit endpoint definitions 
│       └── RetrofitClient.kt    # Dynamic URL & Singleton logic 
├── ui 
│   ├── login 
│   │   ├── LoginScreen.kt       # UI Components (Compose) 
│   │   └── LoginViewModel.kt    # Logic & Auth State management 
│   └── theme                    # Material 3 Design Tokens 
└── MainActivity.kt              # Entry point
```


---

## ⚙️ Setup & Laboratory Configuration

### Prerequisites
- Android Studio Giraffe or newer.
- Local Backend API running on the same WiFi network.

### Configuring the API Endpoint
The application defaults to `10.67.116.88`. To change the target server for your specific lab setup:

1. **Option A (Manual)**: Update the fallback IP in `RetrofitClient.kt`:

2. **Option B (Dynamic)**: Programmatically update the `SharedPreferences` named `"api_prefs"` with the key `"server_ip"`.

### Build and Run

#### clone the repository
```git clone https://github.com/abindacarmo/android-compose-retrofit-login.git```


#### Open in Android Studio and Sync Gradle


---

## 🤝 Contribution
This project is part of a practical session. Feel free to fork and experiment with the network implementation or UI components.

## 📄 License
This project is licensed under the **MIT License**.

---
**Developed by [Brigida Carmo - abinda carmo]**  
*Mobile Computing Student*
