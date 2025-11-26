# 🚀 NASA APOD Explorer – Backend (Spring Boot REST API)

This is the backend service for **NASA APOD Explorer**, a full-stack application that fetches and serves NASA's Astronomy Picture of the Day (APOD) data through a simplified REST API for frontend consumption.

---

## 🌍 API Source
NASA Public API: https://api.nasa.gov/  
Endpoint: `/planetary/apod`

---

## 🧠 Features
- REST API built using Spring Boot
- Fetches APOD data from NASA API
- Supports:
  - Today’s APOD
  - APOD by date
  - APOD range (last 7 days gallery)
- Caching with TTL & max size to improve performance
- Secure API key handling using environment variable
- CORS enabled for React frontend
- Error handling & validations

---

## 🛠 Technology Stack
| Component | Tech |
|----------|------|
Language | Java 17
Framework | Spring Boot
HTTP Client | RestTemplate
Build Tool | Maven
Cache | Custom TTL Cache
Validation | jakarta.validation
API Key | Environment variable

---

## 📌 REST Endpoints

| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/apod/today` | Get Today’s APOD |
| GET | `/api/apod/date?date=YYYY-MM-DD` | Get APOD by date |
| GET | `/api/apod/range?start_date=YYYY-MM-DD&end_date=YYYY-MM-DD` | Get APOD list for date range |
| GET | `/api/apod/health` | Health Check |

---

## 🧪 Run Instructions

### Prerequisites
- Java 17+
- Maven installed

### Start Backend
```bash
mvn spring-boot:run

## 👨‍💻 Author
**Ahmad Ali**  
Email: ahmadalidudekula35@gmail.com  
Phone: 6304417514
