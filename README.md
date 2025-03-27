# UpTill - Real-Time Monitoring System 🚀  
### Tech Stack: Java | Spring Boot | WebSockets | PostgreSQL/MySQL  

UpTill is a real-time monitoring tool that tracks website uptime, performance, and user activity.  
It leverages WebSockets for live visitor tracking and Spring Scheduler for automated health checks.  

## ✨ Features  
✅ **Live Monitoring:** Track server uptime and response time  
✅ **Real-time User Tracking:** WebSockets enable instant updates on active visitors  
✅ **Automated Health Checks:** Spring Scheduler ensures system reliability  
✅ **Secure & Scalable:** Implements authentication and efficient API handling  

## ⚙️ Setup & Installation  
### 1️⃣ Clone the Repository  
```bash
git clone https://github.com/Divyansh2911/UpTill.git
cd UpTill
```
### 2️⃣ Backend Setup
Install Java 17+ and Maven.

Run the backend:
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
### 3️⃣ Database Configuration
#Update application.properties in src/main/resources/:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/uptill_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```
### 📌 API Endpoints
* Endpoint	Method	Description
/api/monitor	GET	Fetch real-time monitoring data
/api/users/active	GET	Get active user details
/api/health-check	GET	Perform a periodic health check
### 💡 Why Use UpTill?
🔹 Fast & Reliable – Minimal response time with efficient handling
🔹 Scalable – Supports multiple users & concurrent requests
🔹 Open-Source – Modify and extend as needed
