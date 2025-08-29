Perfect 👍 — I’ll merge everything into a **single, clean, easy-to-read `README.md` file**.
This will include:

* A short **project description** (under 350 chars).
* The detailed documentation (features, tech stack, setup, usage, API, etc.).
* Proper formatting for GitHub readability.

Here’s your final **all-in-one README.md** 👇

---

```markdown
# TMMS – Travel Management Service (Spring Boot 3)

**TMMS (Travel Management Service)** is a Spring Boot 3 application that enables employees to submit travel requisitions and reimbursements, while managers and admins can review, approve, or decline requests. It uses JWT-based authentication, Spring Security 6, JPA/Hibernate, and provides Swagger/OpenAPI 3.1 documentation.

---

## ✨ Features
- **User Management**: Register, login (JWT), fetch profile  
- **Travel Requisitions**: Submit, view mine, list pending, approve, decline  
- **Travel Reimbursements**: Submit, view mine, list pending, approve, decline  
- **Role-based Access Control**: `EMPLOYEE`, `MANAGER`, `ADMIN`  
- **Consistent Responses**: Global exception handling + `ApiResponse` structure  
- **API Docs**: Swagger/OpenAPI 3.1 UI for interactive exploration  
- **Cross-cutting Concerns**: AOP service logging hooks (example aspect included)  
- **Email Notifications**: Placeholders for approvals/declines  

---

## 🛠 Tech Stack
- Java 21, Spring Boot 3.x  
- Spring Web, Spring Security 6, Spring Data JPA, Hibernate 6.6  
- MySQL (or any JDBC-compatible DB)  
- JWT (jjwt)  
- springdoc-openapi (Swagger UI)  
- Lombok, ModelMapper  
- Spring Boot DevTools (optional for development)  

---

## 📂 Project Structure (Key Packages)
```

com.travelmanagementservice.tmss
├── controller      → REST controllers
├── security        → Security config, JWT filter/util, UserDetailsService
├── service         → Services and implementations
├── repository      → Spring Data JPA repositories
├── entity          → JPA entities
├── dto             → Request/response DTOs
├── mapper          → ModelMapper mappers
├── exceptions      → Custom exceptions and advice
└── aop             → Example service AOP hooks

````

---

## ⚙️ Prerequisites
- Java 21+  
- Maven 3.9+  
- MySQL 8+ (or adjust JDBC URL for your DB)  
- IDE with Lombok plugin enabled  

---

## 🔧 Configuration

Recommended: `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/tmms?useSSL=false&serverTimezone=UTC
    username: tmms_user
    password: tmms_pass
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true

jwt:
  secret: change-this-to-a-very-strong-secret
  expiration: 36000000

springdoc:
  swagger-ui:
    path: /swagger-ui.html
````

---

## 🚀 Build & Run

* **Build:** `mvn clean package`
* **Run (Dev):** `mvn spring-boot:run`
* **Run (Jar):** `java -jar target/app.jar`

Default server: [http://localhost:8080](http://localhost:8080)

* Swagger UI:

  * [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
  * [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
* OpenAPI JSON:

  * [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 🔐 Security Overview

* **JWT-based Stateless Auth**

  * `/user/login` → returns JWT on successful authentication
  * Clients send `Authorization: Bearer <token>` for protected endpoints

* **Public Endpoints**

  * `/user/register`, `/user/login`, Swagger/OpenAPI

* **Role-based Access**

  * `EMPLOYEE`: Submit/view own requisitions & reimbursements
  * `MANAGER`: Approve/decline travel & reimbursement requests
  * `ADMIN`: Reserved admin routes

---

## 🗄 Database Model (Simplified)

* **User**: `(id, username, password, role)`
* **TravelRequisition**: `(id, purpose, startDate, endDate, estimatedCost, status, requestedBy)`
* **TravelReimbursement**: `(id, amount, description, dateOfExpense, status, requestedBy)`

Notes:

* `requestedBy` → `@ManyToOne` to `User` (`user_id` as FK)
* `status` → `PENDING`, `APPROVED`, `DECLINED`
* Passwords stored as **BCrypt hashes**

---

## 📡 API Endpoints (Postman-Friendly)

### Authentication

* **POST** `/user/register`

  ```json
  { "username": "babu", "password": "Babu345", "role": "EMPLOYEE" }
  ```
* **POST** `/user/login`

  ```json
  { "username": "babu", "password": "Babu345" }
  ```

### User

* **GET** `/user/me?username={username}`

### Travel Requisition (Bearer Token Required)

* **POST** `/travel/submit`
* **GET** `/travel/myrequests`
* **GET** `/travel/pending` (MANAGER)
* **GET** `/travel/approve/{id}` (MANAGER)
* **GET** `/travel/decline/{id}` (MANAGER)

### Travel Reimbursement (Bearer Token Required)

* **POST** `/reimbursement/submit`
* **GET** `/reimbursement/myRequest`
* **GET** `/reimbursement/pending` (MANAGER/ADMIN)
* **GET** `/reimbursement/approve/{id}` (MANAGER/ADMIN)
* **GET** `/reimbursement/decline/{id}` (MANAGER/ADMIN)

---

## ⚠️ Common Gotchas

* **Entity IDs:** Do not send `id=0` for new inserts
* **Password Encoding:** Must be BCrypt (`$2a/$2b...`)
* **AuthenticationManager:** Correctly wire `CustomUserDetailsService` + `PasswordEncoder`
* **Swagger Issues (403/500):**

  * Permit `/swagger-ui/**`, `/v3/api-docs/**` in security
  * Use springdoc compatible with Spring Boot 3.5+

---

## 💡 Development Tips

* Separate **DTOs** for requests/responses (avoid exposing passwords/IDs)
* Add a custom `AuthenticationEntryPoint` → return `401 JSON` consistently
* Enable SQL & security logs in dev; minimize in prod
* **Testing:**

  * Unit test services with mocks
  * Use Testcontainers for DB integration

---

## 🐳 Running with Docker (Optional)

```dockerfile
FROM eclipse-temurin:21-jre
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
```

* Build: `mvn clean package -DskipTests`
* Docker build: `docker build -t tmms:latest .`
* Docker run: `docker run -p 8080:8080 --env-file .env tmms:latest`

---

## 🤝 Contribution Guidelines

* Use feature branches + PRs
* Update Swagger annotations when adding endpoints
* Write tests for new business logic
* Keep README + API docs up to date

---

## 📜 License

Licensed under the **MIT License**. See `LICENSE` for details.

---

## 📬 Contact

* Maintainer: **TMMS Team**
* Issues/Requests: Use the repository’s issue tracker

---

🚀 Enjoy building with **TMMS**!

```

---

✅ Save this file as **`README.md`** in your project root, then push it to GitHub.  
Do you also want me to generate a **MIT LICENSE file** (standard template) so your repo is complete?
```
