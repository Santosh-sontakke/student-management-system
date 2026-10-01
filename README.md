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

## API reference

Use `http://localhost:8080` as the base URL. For requests with a JSON body, set `Content-Type: application/json`. IDs in the examples are illustrative; use IDs returned by your API.

### Courses

#### `GET /api/courses`

Lists all courses. No request body.

Example response (`200 OK`):

```json
[
  {
    "id": 1,
    "name": "Bachelor of Computer Science",
    "code": "BCS",
    "description": "Undergraduate computer science course",
    "active": true
  }
]
```

#### `GET /api/courses/{id}`

Gets one course by its ID. For example, request `GET /api/courses/1`. No request body. Returns the course object shown above (`200 OK`), or `404 Not Found` if the ID does not exist.

#### `POST /api/courses`

Creates a course. `name` and `code` are required; `code` must be unique. `description` is optional and `active` defaults to `true`.

Example request:

```json
{
  "name": "Bachelor of Computer Science",
  "code": "BCS",
  "description": "Undergraduate computer science course",
  "active": true
}
```

Returns the created course including its generated `id` (`201 Created`). Duplicate codes return `409 Conflict`; missing required values return `400 Bad Request`.

#### `PUT /api/courses/{id}`

Replaces the course fields for the given ID. Send the same JSON fields as the create request. `name` and `code` are required; `active` is optional and, when omitted, keeps its existing value.

Example request to `PUT /api/courses/1`:

```json
{
  "name": "Bachelor of Computer Science",
  "code": "BCS",
  "description": "Updated course description",
  "active": true
}
```

Returns the updated course (`200 OK`), or `404 Not Found` when the course ID does not exist.

#### `DELETE /api/courses/{id}`

Deletes the course with the given ID. For example, `DELETE /api/courses/1`. No request body. Success returns `204 No Content` with an empty body. Missing courses return `404 Not Found`. A course referenced by a registration cannot be deleted and returns `409 Conflict`.

### Students

Students are created or updated as part of registration; these endpoints are read-only.

#### `GET /api/students`

Lists all student records. No request body.

Example response (`200 OK`):

```json
[
  {
    "id": 1,
    "name": "Asha Patil",
    "email": "asha.patil@example.com",
    "phone": "9876543210",
    "address": "Latur, Maharashtra",
    "dateOfBirth": "2005-03-15",
    "gender": "Female",
    "guardianName": "Ramesh Patil",
    "highestQualification": "Higher Secondary Certificate",
    "percentage": 82.5,
    "category": "Open",
    "religion": "",
    "occupation": "Student",
    "physicallyChallenged": false
  }
]
```

#### `GET /api/students/{id}`

Gets one student by ID. For example, request `GET /api/students/1`. No request body. Returns the student object shown above (`200 OK`), or `404 Not Found` if the ID does not exist.

### Registrations and admissions

#### `POST /api/registrations`

Registers a student for a course. `courseId` must refer to an existing, active course. `student.name` and a valid `student.email` are required. Percentage, if supplied, must be between 0 and 100. If a student with that email already exists, their student details are updated and a new course registration is created.

Example request:

```json
{
  "courseId": 1,
  "student": {
    "name": "Asha Patil",
    "email": "asha.patil@example.com",
    "phone": "9876543210",
    "address": "Latur, Maharashtra",
    "dateOfBirth": "2005-03-15",
    "gender": "Female",
    "guardianName": "Ramesh Patil",
    "highestQualification": "Higher Secondary Certificate",
    "percentage": 82.5,
    "category": "Open",
    "religion": "",
    "occupation": "Student",
    "physicallyChallenged": false
  }
}
```

Example response (`201 Created`):

```json
{
  "id": 1,
  "registrationNumber": "REG-A1B2C3D4",
  "student": {
    "id": 1,
    "name": "Asha Patil",
    "email": "asha.patil@example.com",
    "phone": "9876543210",
    "address": "Latur, Maharashtra",
    "dateOfBirth": "2005-03-15",
    "gender": "Female",
    "guardianName": "Ramesh Patil",
    "highestQualification": "Higher Secondary Certificate",
    "percentage": 82.5,
    "category": "Open",
    "religion": "",
    "occupation": "Student",
    "physicallyChallenged": false
  },
  "course": {
    "id": 1,
    "name": "Bachelor of Computer Science",
    "code": "BCS",
    "description": "Undergraduate computer science course",
    "active": true
  },
  "status": "PENDING",
  "batch": null,
  "registeredAt": "2026-09-29T10:30:00"
}
```

The registration number and date are generated by the server. Registering the same email for the same course twice returns `409 Conflict`. A missing/inactive course returns `404 Not Found`/`409 Conflict` respectively; invalid fields return `400 Bad Request`.

#### `GET /api/registrations`

Lists all registrations. No request body. Example response (`200 OK`):

```json
[
  {
    "id": 1,
    "registrationNumber": "REG-A1B2C3D4",
    "student": { "id": 1, "name": "Asha Patil", "email": "asha.patil@example.com" },
    "course": { "id": 1, "name": "Bachelor of Computer Science", "code": "BCS", "description": "Undergraduate computer science course", "active": true },
    "status": "PENDING",
    "batch": null,
    "registeredAt": "2026-09-29T10:30:00"
  }
]
```

#### `GET /api/registrations?status={status}`

Filters registrations by status. Valid values are `PENDING`, `WAITLISTED`, `SELECTED`, `ADMITTED`, and `REJECTED`. For example, `GET /api/registrations?status=WAITLISTED` returns only waiting-list registrations. No request body; response is an array in the same format as above. An invalid status returns `400 Bad Request`.

#### `GET /api/registrations/{id}`

Gets a single registration, including student and course details. For example, request `GET /api/registrations/1`. No request body. Returns one registration object (`200 OK`), or `404 Not Found` if it does not exist.

#### `PATCH /api/registrations/{id}/decision`

Updates a registration's admission status. Send a status from `PENDING`, `WAITLISTED`, `SELECTED`, `ADMITTED`, or `REJECTED`. Include `batch` when setting status to `ADMITTED`; other statuses clear the batch.

Example request to admit registration 1:

```json
{
  "status": "ADMITTED",
  "batch": "2026-A"
}
```

Example response (`200 OK`) is the updated registration object, with `status` set to `ADMITTED` and `batch` set to `2026-A`. Missing batch for an admission returns `400 Bad Request`; an unknown registration ID returns `404 Not Found`.

### Error response examples

Validation errors identify invalid fields (`400 Bad Request`):

```json
{
  "timestamp": "2026-09-29T10:30:00Z",
  "status": 400,
  "error": "Validation failed",
  "details": {
    "student.email": "must be a well-formed email address"
  }
}
```

Other common errors return a message (`404 Not Found`, `409 Conflict`, or `400 Bad Request`):

```json
{
  "timestamp": "2026-09-29T10:30:00Z",
  "status": 404,
  "error": "Course 999 was not found"
}
```
