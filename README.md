# BookSpringLab

This project is a Book Management System built with Spring Boot and MySQL.

## Prerequisites

- Java 17+ installed
- Maven installed
- Docker installed and running

---

## Step 1: Build the MySQL Docker Image
docker build -t my-mysql-image .
docker run -d \ --name mysql8 \ -p 3308:3306 \ my-mysql-image
# Clean and build the project
mvn clean install

# Run the application
mvn spring-boot:run
