# Online Shop API

A RESTful online shop backend built with Spring Boot, Spring Security, Hibernate(JPA),
and MySQL. This project supports product management, user authentication,
and order processing with role-based access control.

## Features
* User registration & authentication
* Role-based access control (ADMIN, USER)
* Product CRUD operations
* Order creation (buy product)
* View personal orders
* Admin view of all orders
* Secure endpoints with Spring Security
* DTO-based architecture
* Layered structure (Controller → Service → Repository)

## Tech Stack
* Java 17+
* Spring Boot 3+
* Spring Security
* Spring Data JPA (Hibernate)
* MySQL
* Maven
* ModelMapper

## Setup & Installation non Docker Based

### 1. Clone Repository
git clone https://github.com/nawid-hzr/online-shop.git

### 2. Configure Database
CREATE DATABASE shop;

### 3. Configure Environment Variables
Update application.properties: This is for MySQL config.

spring.datasource.url=jdbc:mysql://localhost:3306/shop
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

### 4. Run Application
your application runs on: http://localhost:8080

## Setup & Installation Docker Based
still not added

## Roles

| Role  | Permissions                                  |
| ----- | -------------------------------------------- |
| ADMIN | Full access (products + all orders)          |
| USER  | View products, buy products, view own orders |

## Gateways

### 1. Products

| Method | Endpoint             | Access | Description       |
| ------ | -------------------- | ------ | ----------------- |
| GET    | `/api/products`      | Public | Get all products  |
| GET    | `/api/products/{id}` | Public | Get product by ID |
| POST   | `/api/products`      | ADMIN  | Create product    |
| PUT    | `/api/products/{id}` | ADMIN  | Update product    |
| DELETE | `/api/products/{id}` | ADMIN  | Delete product    |

### 2. Orders

| Method | Endpoint           | Access        | Description                   |
| ------ | ------------------ | ------------- | ----------------------------- |
| POST   | `/api/orders/buy`  | USER, ADMIN   | Buy product                   |
| GET    | `/api/orders`      | ADMIN         | Get all orders                |
| GET    | `/api/orders/my`   | USER, ADMIN   | Get logged-in user's orders   |
| GET    | `/api/orders/{id}` | Authenticated | Get order by ID               |
| DELETE | `/api/orders/{id}` | Authenticated | Delete order (owner or admin) |

## Database Tables

### users
* id
* username
* email
* password
* role

### products
* id
* name
* description
* price
* amount
* rate

### orders
* id
* user_id
* product_id
* quantity
* total_price
* created_at

## Future Improvements

* JWT Authentication
* Pagination for orders/products
* Global exception handler
* Unit & Integration tests
* API documentation (Swagger)~~``