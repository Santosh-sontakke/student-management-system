# Student Admission System API

Spring Boot REST API for course registration and admission processing. It uses Java 17, Spring MVC, Spring Data JPA, Bean Validation, and PostgreSQL.

## Run locally

1. Create a PostgreSQL database named `student_management`.
2. Set `DB_USERNAME` and `DB_PASSWORD` for your local PostgreSQL account. `DB_URL` is optional and defaults to `jdbc:postgresql://localhost:5432/student_management`.
3. Start the application with `./mvnw spring-boot:run`.

The API listens on `http://localhost:8080`. Hibernate creates or updates tables on startup (`spring.jpa.hibernate.ddl-auto=update`).

## Bruno workflow

Import the `bruno/Student Admission System` collection, then:

1. Create a course with `POST /api/courses`.
2. Register an applicant with `POST /api/registrations`, using the course ID from step 1.
3. List registrations with `GET /api/registrations` or filter by status, for example `?status=WAITLISTED`.
4. Set an outcome with `PATCH /api/registrations/{id}/decision`. Use `PENDING`, `WAITLISTED`, `SELECTED`, `ADMITTED`, or `REJECTED`. An `ADMITTED` decision requires a `batch`.

Course create/update, registration, and decision requests return JSON. Invalid requests return HTTP 400, missing IDs return HTTP 404, and duplicate course codes or course registrations return HTTP 409.

## API routes

| Method | Route | Purpose |
| --- | --- | --- |
| GET | `/api/courses` | List courses |
| GET | `/api/courses/{id}` | Get a course |
| POST | `/api/courses` | Create a course |
| PUT | `/api/courses/{id}` | Update a course |
| DELETE | `/api/courses/{id}` | Delete a course |
| GET | `/api/students` | List students |
| GET | `/api/students/{id}` | Get a student |
| POST | `/api/registrations` | Register an applicant for a course |
| GET | `/api/registrations?status=PENDING` | List/filter registrations |
| GET | `/api/registrations/{id}` | Get a registration |
| PATCH | `/api/registrations/{id}/decision` | Set status and optional batch |

Admission decision and student-list routes currently have no authentication because the supplied synopsis does not define account fields, credential policy, or admin provisioning. Add the agreed authentication model before exposing this API beyond a trusted local environment.
