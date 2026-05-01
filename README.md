# HireHub - Authentication Module

## 📌 Overview

This project implements a complete authentication system for the HireHub platform using Spring Boot.

It includes user registration, login, JWT-based authentication, role-based authorization, and secure password storage.

---

## 🚀 Features

* User Registration
* User Login
* JWT Token Generation
* Role-Based Access Control (ADMIN, CANDIDATE)
* Secure Password Hashing (BCrypt)
* Global Exception Handling
* Protected API Endpoints

---

## 🛠️ Tech Stack

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* PostgreSQL
* JWT (JSON Web Token)
* Maven

---

## ⚙️ Setup Instructions

### 1. Clone Repository

```bash
git clone https://github.com/YOUR_USERNAME/Hire-Hub.git
cd Hire-Hub
```

---

### 2. Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hirehub
spring.datasource.username=postgres
spring.datasource.password=your_password
```

---

### 3. Run Application

```bash
mvn spring-boot:run
```

---

## 📡 API Endpoints

### 🔹 Register User

**POST** `/api/auth/register`

```json
{
  "name": "Hanzlah",
  "email": "hanzlah@gmail.com",
  "password": "123456"
}
```

---

### 🔹 Login

**POST** `/api/auth/login`

```json
{
  "email": "hanzlah@gmail.com",
  "password": "123456"
}
```

Response:

```json
{
  "token": "JWT_TOKEN"
}
```

---

### 🔹 Protected Route Example

**GET** `/api/auth/admin`

Header:

```text
Authorization: Bearer JWT_TOKEN
```

---

## 🔐 Security

* Passwords are hashed using BCrypt
* JWT tokens are used for authentication
* Role-based authorization implemented
* Unauthorized requests are blocked

---

## 🧠 Architecture

```
Controller → Service → Repository → Database
        ↓
     Security Layer (JWT Filter)
```

---

## 📌 Notes

* Default role assigned: `CANDIDATE`
* Admin role can be assigned via database update
* Tokens expire after a fixed duration

---

## 👨‍💻 Author

Hanzlah
