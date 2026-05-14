# Spring Boot Student CRUD API

A RESTful API built with Spring Boot 4 for managing student records, following a clean layered architecture with JPA persistence and MySQL database.

## Tech Stack

- **Java 17**
- **Spring Boot 4**
- **Spring Data JPA** + **Hibernate**
- **MySQL 8**
- **Lombok**
- **JUnit 5** + **Mockito**
- **Maven**

## Project Structure

```
src/
├── main/java/com/example/demo/
│   ├── controller/    # REST endpoints
│   ├── service/       # Business logic
│   ├── repository/    # Data access layer
│   └── model/         # JPA entities
└── test/java/com/example/demo/
    ├── controller/    # MockMvc tests
    └── service/       # Unit tests
```

## Getting Started

### Prerequisites

- Java 17+
- MySQL 8+
- Maven (or use the included `mvnw` wrapper)

### Database Setup

The application connects to MySQL with the following default configuration. Make sure MySQL is running before starting the app:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/cifp?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
```

Update `src/main/resources/application.properties` to match your local credentials.

### Run the Application

```bash
./mvnw spring-boot:run
```

The server starts on `http://localhost:8080`.

### Run Tests

```bash
./mvnw test
```

## API Endpoints

| Method | Endpoint              | Description          | Status |
|--------|-----------------------|----------------------|--------|
| GET    | `/api/estudiantes`    | Get all students     | 200 OK |
| POST   | `/api/estudiantes`    | Create a new student | 201 Created |

### Example Request — Create Student

```http
POST /api/estudiantes
Content-Type: application/json

{
  "nombre": "Juan García",
  "email": "juan@example.com",
  "edad": 20
}
```

### Example Response

```json
{
  "id": 1,
  "nombre": "Juan García",
  "email": "juan@example.com",
  "edad": 20
}
```

## Branches

| Branch                    | Description                        |
|---------------------------|------------------------------------|
| `main`                    | Stable production-ready code       |
| `feature/new-features`    | Active development branch          |

## License

This project is intended for educational purposes.
