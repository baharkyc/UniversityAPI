# UniversityAPI

**UniversityAPI** is a **RESTful API** designed for university management. This project handles key university operations such as student registration, course management, user authentication, and more. It is developed using **Spring Boot**, **Spring Security**, and **SQL**.

## Features

- **Student Registration**: Allows students to register in the system.
- **Course Management**: Manages course enrollment and administration.
- **User Authentication**: Secure login and registration using JWT-based authentication.
- **User Authorization**: Role-based access control (RBAC) for different user roles (e.g., Admin, Student).
- **Exception Handling**: Custom exceptions for handling errors like duplicate registrations or invalid inputs.

## Technologies Used

- **Spring Boot**: Framework used for building the RESTful API.
- **Spring Security**: Provides security and authentication features.
- **JPA (Java Persistence API)**: Manages database interactions.
- **SQL**: Relational database to store user and course data.
- **JWT**: JSON Web Tokens for stateless authentication.

## Endpoints

- **GET /users**: Get a list of all users.
- **GET /users/{id}**: Get details of a specific user.
- **POST /users**: Register a new user.
- **PUT /users/{id}**: Update an existing user's information.
- **DELETE /users/{id}**: Delete a user from the system.

- **GET /courses**: Get a list of all courses.
- **GET /courses/{id}**: Get details of a specific course.
- **POST /courses**: Add a new course.
- **PUT /courses/{id}**: Update course details.
- **DELETE /courses/{id}**: Remove a course from the system.
