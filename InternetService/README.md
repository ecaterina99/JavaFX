# 💻 Internet Package Manager — JavaFX Desktop Application


Internet Package Manager is a modern JavaFX and Spring Boot desktop application for managing internet subscription packages. It features CRUD operations for customer data, advanced filtering, and real-time statistics. Built with strong form validation and an efficient double-click editing workflow, it ensures accurate data handling and streamlined customer service management.


## The Application Concept

Internet Package Manager streamlines the administration of internet subscription packages through an intuitive desktop interface. Users can create, view, edit, and delete customer packages containing personal information alongside subscription details including internet speed (2-100 Mbps), bandwidth limits (1-100 GB or unlimited), and contract duration (1-2 years). The powerful filtering system allows instant searches by customer name, speed tier, or contract length, while real-time statistics provide operational insights. Form validation ensures data integrity before database commits, and the double-click editing workflow accelerates package updates for efficient customer service operations.

## Screenshots

![Image](https://github.com/user-attachments/assets/dddd118c-b86c-4354-80f3-80667e2d9a88)

![Image](https://github.com/user-attachments/assets/6be2de5f-1eb3-44a2-9641-f0d8c5c43b58)

![Image](https://github.com/user-attachments/assets/68c2ba53-2910-48a6-9077-cd0ff1816237)

![Image](https://github.com/user-attachments/assets/d04f2971-9ad2-4694-bb85-0d44a7a4a911)

## 💡 Overview

⚙️ Spring Boot + JavaFX integration using custom lifecycle management (StageReadyEvent)

🗄️ Spring Data JPA + MySQL for full CRUD database operations

✅ Jakarta Validation with custom validators for precise input checks

🚨 Custom Exception Handling (PackageServiceException) for robust error control

🧩 Mapper & Service Layers ensuring clean, modular architecture

🧠 Transactional logic maintaining data consistency

🪵 SLF4J Logging for monitoring and debugging


## 🎨 JavaFX Frontend

FXML-based UI with custom CSS styling and a modern, undecorated window

Smooth drag-to-move and custom window controls

Bidirectional data binding for reactive UI updates

FilteredList and ObservableList for real-time data filtering and table updates

Dynamic dashboard displaying total packages, active filters, and average speeds

Double-click editing for instant record loading

Instant validation feedback with field highlighting and success notifications

⚡ Features

Multi-criteria filtering by customer name, speed, and contract duration

Real-time statistics that update as filters change

Confirmation dialogs for critical operations

Comprehensive exception and validation handling

Auto-dismissing success messages for smoother UX

🛠️ Tech Highlights

Full-stack JavaFX + Spring Boot integration

Enterprise architecture (Service, Repository, Mapper layers)

Real-time data binding and UI responsiveness

Robust validation and exception handling

Professional UI/UX with modern styling and animations
