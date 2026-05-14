# SGE - Student Management System

Spring Boot project for managing students, departments, modules, grades, and report cards.

## Features

- JWT-based authentication
- Student CRUD API
- Department (`Filiere`) CRUD API
- Module CRUD API
- Grade (`Note`) CRUD API
- Weighted average calculation
- Bulletin pages with Thymeleaf

## Tech Stack

- Java 17+
- Spring Boot 3.2
- Spring Web
- Spring Data JPA
- Spring Security
- Thymeleaf
- MySQL
- JWT (`jjwt`)

## Project Structure

```text
src/main/java/com/example/sge/
|-- config/        Security configuration
|-- controller/    REST controllers + bulletin web pages
|-- dto/           Auth and bulletin DTOs
|-- model/         JPA entities
|-- repository/    Spring Data repositories
|-- security/      JWT services and filter
`-- service/       Business logic
```

## Prerequisites

- Java 17 or newer
- Maven 3.6+ or the bundled Maven launcher
- MySQL

## Database Setup

Create the database:

```sql
CREATE DATABASE sge_db;
```

Update `src/main/resources/application.properties` if needed:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/sge_db
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Run the Project

If Maven is installed globally:

```bash
mvn spring-boot:run
```

If you want to use the bundled Maven on Windows:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-19'
$env:Path='C:\Program Files\Java\jdk-19\bin;' + $env:Path
.\maven-mvnd-2.0.0-rc-3-windows-amd64\mvn\bin\mvn.cmd spring-boot:run
```

Default URL:

```text
http://localhost:8080
```

If port `8080` is already used:

```powershell
.\maven-mvnd-2.0.0-rc-3-windows-amd64\mvn\bin\mvn.cmd spring-boot:run "-Dspring-boot.run.arguments=--server.port=8081"
```

## Authentication Flow

Protected API routes require a JWT token.

Public routes:

- `POST /auth/register`
- `POST /auth/login`
- `GET /bulletins`
- `GET /bulletins/{etudiantId}`

### 1. Register an Admin User

`POST /auth/register`

```json
{
  "username": "admin",
  "password": "admin123",
  "role": "ROLE_ADMIN"
}
```

### 2. Login

`POST /auth/login`

```json
{
  "username": "admin",
  "password": "admin123"
}
```

Example response:

```json
{
  "token": "your-jwt-token",
  "username": "admin",
  "role": "ROLE_ADMIN"
}
```

### 3. Use the Token

Send this header on protected requests:

```text
Authorization: Bearer <your-jwt-token>
```

## Guided Postman Test

Use the following order to test the project cleanly.

### 1. Create a Filiere

`POST /filieres`

```json
{
  "nom": "Informatique",
  "niveau": "L3",
  "capacite": 30
}
```

### 2. Create an Etudiant

`POST /etudiants`

```json
{
  "nom": "Ali",
  "prenom": "Ben Salem",
  "email": "ali@test.com",
  "groupe": "A1",
  "filiere": {
    "id": 1
  }
}
```

### 3. Create a Module

`POST /modules`

```json
{
  "nom": "Java",
  "code": "JAV101",
  "coefficient": 3,
  "filiere": {
    "id": 1
  }
}
```

### 4. Create a Note

Use the real `etudiant.id` and `module.id` returned by the API.

`POST /notes`

```json
{
  "valeur": 15.5,
  "etudiant": {
    "id": 1
  },
  "module": {
    "id": 1
  }
}
```

### 5. Verify Data

- `GET /etudiants`
- `GET /etudiants/1`
- `GET /modules`
- `GET /notes/etudiant/1`
- `GET /notes/moyenne/1`
- `GET /bulletins`

## Main Endpoints

### Auth

- `POST /auth/register`
- `POST /auth/login`

### Filieres

- `GET /filieres`
- `GET /filieres/{id}`
- `POST /filieres`
- `PUT /filieres/{id}`
- `DELETE /filieres/{id}`

### Etudiants

- `GET /etudiants`
- `GET /etudiants/{id}`
- `POST /etudiants`
- `PUT /etudiants/{id}`
- `DELETE /etudiants/{id}`

### Modules

- `GET /modules`
- `GET /modules/{id}`
- `POST /modules`
- `PUT /modules/{id}`
- `DELETE /modules/{id}`

### Notes

- `POST /notes`
- `PUT /notes/{id}`
- `DELETE /notes/{id}`
- `GET /notes/etudiant/{id}`
- `GET /notes/module/{id}`
- `GET /notes/moyenne/{etudiantId}`

### Bulletins

- `GET /bulletins`
- `GET /bulletins/{etudiantId}`

## Browser Pages

- `http://localhost:8080/bulletins`
- `http://localhost:8080/bulletins/1`

If you run the app on another port such as `8081`, update the URL accordingly.

## Known Notes

- `/etudiants`, `/filieres`, `/modules`, and `/notes` are API endpoints, not HTML pages.
- If you open those API URLs in a browser without a token, you should not expect a normal web page.
- If a referenced `module` or `filiere` does not exist, related create requests may fail with database foreign key errors.
- Some not-found cases currently return `500` instead of `404`.

## Suggested GitHub Upload Steps

```bash
git init
git add .
git commit -m "Initial commit"
git branch -M main
git remote add origin <your-github-repo-url>
git push -u origin main
```

## License

Use the license that matches your school or personal project needs.
