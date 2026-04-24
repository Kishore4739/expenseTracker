🚀 Expense Tracker Backend API
A production-ready Expense Tracker REST API built using Spring Boot, featuring authentication, role-based access, and expense management.

📌 Features
🔐 JWT Authentication & Authorization
👤 User & Admin Roles
💰 Expense Management (CRUD)
🗂️ Category Management
📊 Admin Dashboard (Stats)
⚙️ Global Exception Handling
🌐 CORS Configuration
🧾 DTO-based clean architecture

🛠️ Tech Stack
Java
Spring Boot
Spring Security
JWT (JSON Web Token)
Spring Data JPA
MySQL
Maven

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


🔐 Authentication
Uses JWT Token-based Authentication
Login returns a token which must be used in:
Authorization: Bearer <your_token>

📌 API Endpoints (Overview)
🔑 Auth
POST /auth/register → Register user
POST /auth/login → Login user
👤 User
Manage user profile
💰 Expense
POST /expenses → Add expense
GET /expenses → Get all expenses
PUT /expenses/{id} → Update expense
DELETE /expenses/{id} → Delete expense
🗂️ Category
Add / Get categories
👑 Admin
View system stats
Manage users


⚙️ Configuration
Update your database in:
application.properties
Example:
spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
spring.datasource.username=root
spring.datasource.password=your_password

▶️ Run the Project
1. Clone Repository
git clone https://github.com/your-username/expense-tracker.git
2. Navigate
cd expense-tracker
3. Run
mvn spring-boot:run

🧪 API Testing
Postman collection included:
ExpenseTracker-API.postman_collection.json
Import into Postman to test APIs easily.
