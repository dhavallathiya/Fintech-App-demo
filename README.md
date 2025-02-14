# Account Management App

## Overview
This is an Android application for managing user accounts, displaying account balances, and transferring funds. The app follows the MVVM architecture and is built using Kotlin.

## Features
- User authentication using Firebase Authentication
- Display a list of user accounts with RecyclerView
- Local database storage using Room
- Real-time data observation with LiveData
- UI designed with Material Design principles
- Status bar color customization and consistent font family

## Technologies Used
- **Architecture:** MVVM (Model-View-ViewModel)
- **Programming Language:** Kotlin
- **Libraries Used:**
  - Firebase Authentication for user login
  - RecyclerView for displaying lists
  - Room for local database management
  - LiveData for data observation
  - ViewModel for managing UI-related data

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/your-repo.git
   ```
2. Open the project in Android Studio.
3. Sync Gradle dependencies.
4. Run the app on an emulator or a physical device.

## Dummy Data
```kotlin
val dummyAccounts = listOf(
    Account(1, "Emma Wilson", 2500.0, "112233445"),
    Account(2, "Michael Brown", 8000.0, "998877665"),
    Account(3, "Sophia Martinez", 4500.0, "556677889"),
    Account(4, "James Anderson", 6000.0, "443322110"),
    Account(5, "Olivia Taylor", 3500.0, "221100334"),
    Account(6, "William Lee", 9000.0, "667788990"),
    Account(7, "Emily Davis", 1200.0, "889900112"),
    Account(8, "Alice Smith", 3000.0, "987654321"),
    Account(9, "Bob Johnson", 7000.0, "567890123")
)
```

---
![1](https://github.com/user-attachments/assets/8dfb3764-5365-43b2-86fe-5aa8777bf808)
![22](https://github.com/user-attachments/assets/d16f5bfa-0192-4f19-8465-10ded87a3aba)
![33](https://github.com/user-attachments/assets/1e3be3f3-445a-4017-a5ec-49471f26ff52)
![44](https://github.com/user-attachments/assets/1ae4375d-5de3-4941-968c-f9a7c9d9a2a3)
![55](https://github.com/user-attachments/assets/c0ef0b62-fdf7-4e6b-a124-6a17f9c538a1)
![66](https://github.com/user-attachments/assets/fe33fedb-eec9-4deb-8dd9-91b8a84b07fb)
![77](https://github.com/user-attachments/assets/e72820fd-0221-4aec-8c40-e0c3a100f364)



## License
This project is licensed under the **MIT License**.

