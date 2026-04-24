# 🚀 Expense Tracker Backend API

A production-ready **Expense Tracker REST API** built using Spring Boot, featuring authentication, role-based access, and expense management.

---

## 📌 Features

- 🔐 JWT Authentication & Authorization  
- 👤 User & Admin Roles  
- 💰 Expense Management (CRUD)  
- 📁 Category Management  
- 📊 Admin Dashboard (Stats)  
- ⚙️ Global Exception Handling  
- 🌐 CORS Configuration  
- 🧾 DTO-based clean architecture  

---

## 🛠️ Tech Stack

- Java  
- Spring Boot  
- Spring Security  
- JWT (JSON Web Token)  
- Spring Data JPA  
- MySQL  
- Maven  

---

## 📁 Project Structure

```
com.expenseTracker
│
├── controller        # REST APIs (Auth, Expense, Category, Admin, User)
├── service           # Business Logic Layer
├── repository        # Database Access Layer
├── model             # Entity Classes (User, Expense, Category)
├── dto               # Request & Response Objects
├── security          # JWT + Security Config
├── configuration     # CORS & Security Configurations
├── exception         # Global Exception Handling
└── enums             # Role Enum
```

---

## 🔐 Authentication

Uses JWT Token-based Authentication.

After login, include token in headers: 
```
Authorization: Bearer <your_token>
```


---

## 📌 API Endpoints

### 🔑 Auth
- POST `/auth/register` → Register user  
- POST `/auth/login` → Login user  

### 💰 Expense
- POST `/expenses` → Add expense  
- GET `/expenses` → Get all expenses  
- PUT `/expenses/{id}` → Update expense  
- DELETE `/expenses/{id}` → Delete expense  

### 📁 Category
- Add / Get categories  

### 👑 Admin
- View system stats  
- Manage users  

---

## ⚙️ Configuration

Update your database config in:

```
src/main/resources/application.properties
```

Example:

```
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=your_password
```

---

## ▶️ Run the Project

### 1. Clone Repository
```
git clone https://github.com/Kishore4739/expenseTracker.git
```

### 2. Navigate
```
cd expenseTracker
```

### 3. Run
```
mvn spring-boot:run
```

## 🧪 API Testing

Import Postman collection: ExpenseTracker-API.postman_collection.json


---

## 📄 API Documentation (Swagger)

Interactive API documentation is available using Swagger UI.

### 🔗 Access Swagger UI:
http://localhost:8080/swagger-ui/index.html

### 🔗 OpenAPI JSON:
http://localhost:8080/v3/api-docs

### 📌 Features:
- Test APIs directly from browser  
- View request/response formats  
- Explore all endpoints (User, Expense, Category, Admin)  
- No need for Postman  

---

## 🧠 Key Concepts Used

- Layered Architecture  
- DTO Pattern  
- JWT Authentication  
- Role-Based Authorization  
- Exception Handling  

---

## 🚀 Future Improvements

- Dockerize application  
- Deploy on AWS / Render  
- Add frontend (React)  
- Add pagination & filtering  
- Add analytics dashboard  


---

## ⭐ Why This Project Stands Out

- Includes security + roles (not just CRUD)  
- Follows industry-standard architecture  
- Clean and scalable design  


