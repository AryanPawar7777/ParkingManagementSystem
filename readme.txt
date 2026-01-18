PROJECT NAME: Parking Microservices System
SUBMISSION DATE: November 7th, 2025

1. TEAM MEMBERS & ROLL NUMBERS
------------------------------

Aryan - 22CSU030



2. WORKING SERVICES & FUNCTIONALITY (WITHOUT ERRORS)
--------------------------------------------------
* **Service Registry (Eureka) Server.port = 8761:** Successfully registers and deregisters all microservices.
* **Config Server: (Server.port =8888) ** Successfully centralizes configuration for all microservices.
* **API Gateway (Gateway| Server.Port =9010 ):** Successfully routes external requests to the correct internal microservices.
* **ParkingSlotService (9002):** Manages slot CRUD operations and availability checks.
* **PaymentService (9003):** Handles payment processing and transaction logging.
* **UserService (9005 - New):** Manages user registration, profile retrieval, and authentication (assumed functionality).
* **ReservationService (9004- New):** Handles the booking and scheduling of parking slots (assumed functionality).

localhost:8888




3. MEMBER CONTRIBUTIONS & ENDPOINTS
-----------------------------------
A. ARYAN - [22CSU030]
    * **Services Coded:** ALL microservices (ParkingSlotService, PaymentService, UserService, ReservationService, APIGateway, ConfigServer, EurekaServer).



4. COMMON CODE CONTRIBUTIONS
----------------------------
* **API Gateway (Routing & Filtering):** ARYAN
* **Config Server Setup:** ARYAN
* **Service Registry (Eureka) Setup:** ARYAN


