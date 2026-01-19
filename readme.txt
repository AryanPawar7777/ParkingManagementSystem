Parking Management System

This project is a backend microservices–based Parking Management System built using Spring Boot and Spring Cloud.
It is designed to demonstrate real-world backend architecture, service separation, centralized configuration, and secure API communication.



🔹 Microservices Included

The system is divided into multiple independent microservices, each handling a specific responsibility.
API Gateway – Single entry point for all client requests
Config Server – Centralized configuration management
Service Discovery – Registration and discovery of services
Auth Service – Authentication and authorization using JWT
User Service – User management operations
Parking Slot Service – Parking slot availability and management
Reservation Service – Slot booking and reservation workflow
Payment Service – Payment handling and transaction management






🔹 Architecture Overview
The application follows a microservices architecture with centralized routing and configuration.
Requests are routed through API Gateway
Configuration is fetched from Spring Cloud Config Server
Services communicate via REST APIs
Each service is independently deployable
Loose coupling between services




🔹 Technology Stack
The project is developed using modern Java backend technologies.
Java 17
Spring Boot
Spring Cloud (Gateway, Config Server)
Spring Security
Spring Data JPA
MySQL
Maven
Git & GitHub




🔹 Key Features

This project focuses on backend-specific features and best practices.
Modular microservices design
Centralized API routing
Secure authentication with JWT
Service-to-service communication
Clean layered architecture
Production-style backend structure





🔹 Security & Configuration Management
Security best practices are followed throughout the project.
Sensitive files (application.properties) are excluded from Git
No database passwords or secrets are committed
Configuration is managed using Config Server
Environment-specific values are handled outside source code





🔹 How to Run (High-Level)

This project is intended mainly for architecture and backend understanding.
Typical startup order:
Start Config Server
Start Service Discovery
Start API Gateway
Start Auth Service

Start remaining services (User, Parking, Reservation, Payment)
