# MyFinanceApp

A simple finance management backend application built with **Spring Boot**, **Java**, **PostgreSQL**, and **JWT authentication**.

---

## Features

- User registration and login with JWT authentication
- CRUD operations for Users, Categories, and Transactions
- Transaction filtering by user, category, and date range
- Automatic calculation of total amount per user within a date range
- Validation and global exception handling

---

## Technology Stack

- Java 23
- Spring Boot 3.5
- PostgreSQL
- Spring Security with JWT
- Lombok
- Maven

---

## Getting Started

### 1. Clone the repository

```bash
git clone <your-repo-url>
cd MyFinanceApp

 Create PostgreSQL database
CREATE DATABASE my_finance_app

 Configure database credentials

Copy the example properties file:
cp src/main/resources/application.properties.example src/main/resources/application.properties

Open application.properties and set your database credentials:
spring.application.name=MyFinanceApp
spring.datasource.url=jdbc:postgresql://localhost:5432/my_finance_app
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

 Build and run the application
mvn clean install
mvn spring-boot:run

## API Endpoints

**Authentication**
- POST   /auth/register   - Register a new user (No authentication)
- POST   /auth/login      - Login and get JWT token (No authentication)

**Users**
- GET    /user/get        - Get all users (JWT required)
- POST   /user/save       - Create a new user (JWT required)
- PUT    /user/update/{id}- Update user by ID (JWT required)
- DELETE /user/delete/{id}- Delete user by ID (JWT required)

**Categories**
- GET    /category/get    - Get all categories (JWT required)
- POST   /category/save   - Create a category (JWT required)
- PUT    /category/update/{id} - Update category by ID (JWT required)
- DELETE /category/delete/{id} - Delete category by ID (JWT required)

**Transactions**
- GET    /transactions/get        - Get all transactions (JWT required)
- GET    /transactions/{id}      - Get transaction by ID (JWT required)
- POST   /transactions/save       - Create a transaction (JWT required)
- PUT    /transactions/update/{id} - Update transaction by ID (JWT required)
- DELETE /transactions/delete/{id} - Delete transaction by ID (JWT required)




Note: All endpoints except /auth/** require a valid JWT token in the Authorization header.


6.Security

JWT is used for authentication

Passwords are encrypted with BCrypt

application.properties is ignored in Git to protect credentials

Notes

Make sure your PostgreSQL service is running before starting the application

You can extend this project by adding unit tests, Docker support, or a frontend