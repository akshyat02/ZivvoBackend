# Zivvo API

**Zivvo API - Zero Inventory Visible Operations POS**

Zivvo API is a robust backend service built with Spring Boot, designed to power a modern Point of Sale (POS) system. It focuses on inventory management, product variants, and store operations with high visibility and efficiency.

---

## 🚀 Tech Stack

- **Language:** Java 25
- **Framework:** Spring Boot 4.0.2
- **Database:** PostgreSQL 16
- **Build Tool:** Maven
- **ORM:** Spring Data JPA / Hibernate
- **Documentation:** SpringDoc OpenAPI (Swagger UI)
- **Utilities:** Lombok

---

## 📂 Project Structure

The project follows a standard Spring Boot layered architecture:

```text
src/main/java/com/zivvo
├── controller/         # REST API Endpoints (Brand, Product, Store, etc.)
├── dto/                # Data Transfer Objects
├── entity/             # JPA/Hibernate Entities (Database Schema)
├── repository/         # Spring Data JPA Repositories
├── service/            # Business Logic Layer
└── ZivvoApiApplication.java  # Main Application Class
```

---

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed:
- [Java 25 JDK](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.x](https://maven.apache.org/download.cgi)
- [Docker & Docker Compose](https://www.docker.com/products/docker-desktop)

---

## ⚙️ Getting Started

### 1. Clone the repository
```bash
git clone [<repository-url>](https://github.com/akshyat02/ZivvoBackend)
cd zivvo-api
```

### 2. Set up the Database (Docker)
The project includes a `docker-compose.yml` file to quickly spin up a PostgreSQL instance and pgAdmin.

```bash
docker-compose up -d
```

- **PostgreSQL Port:** `5432`
- **Database Name:** `zivvo`
- **User/Password:** `zivvo` / `password123`
- **pgAdmin Port:** `5050` (Login: `admin@zivvo.com` / `admin123`)

### 3. Build & Run the Application
You can use the Maven wrapper to build and run the project:

```bash
./mvnw clean install
./mvnw spring-boot:run
```

---

## 📖 API Documentation

Once the application is running, you can access the interactive API documentation:

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI Specs:** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

### Core Endpoints:
- `/api/brands` - Manage product brands
- `/api/products` - Product management
- `/api/inventory` - Inventory tracking
- `/api/stores` - Store management
- `/api/variants` - Product variant configurations

---

## 🐳 Docker Services

The `docker-compose.yml` provides the following services:

| Service | Image | External Port | Description |
| :--- | :--- | :--- | :--- |
| `postgres` | `postgres:16` | `5432` | Primary database |
| `pgadmin` | `dpage/pgadmin4` | `5050` | Database management UI |

---

## 📝 Configuration

Application configuration can be found in `src/main/resources/application.yaml`.
Key configurations including database connection and Swagger settings are managed here.
