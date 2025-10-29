# SpringBurger

A re-make of my classic burger app using Java, Maven, MySQL, and the Spring Boot framework.

## Technology Stack

- Java 21
- Spring Boot 3.5.7
- Spring Data JPA
- Spring Web
- Thymeleaf (Template Engine)
- MySQL Database
- Maven
- Lombok (Reduces boilerplate code)

## Features

- Create and manage burgers
- Track devoured burgers
- Record burger devourers
- Web-based UI with Thymeleaf templates
- RESTful API endpoints

## Prerequisites

### For Docker (Recommended)

- Docker installed
- Docker Compose installed

### For Local Development

- Java 21 or higher installed
- Maven 3.x installed
- MySQL Server running
- MySQL database created (see Database Setup)

## Database Setup

1. Start your MySQL server

2. Create the database and tables using the provided schema:
   ```bash
   mysql -u root -p < src/main/resources/schema.sql
   ```
   Or manually execute the SQL commands in `src/main/resources/schema.sql`

3. Update database credentials in `src/main/resources/application.properties` if needed:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/burgers
spring.datasource.username=root
spring.datasource.password=root
```

## Building the Application

```bash
mvn clean package
```

To build without running tests:

```bash
mvn clean package -DskipTests
```

## Running the Application

### Option 1: Using Docker (Recommended)

The easiest way to run the application is using Docker Compose, which will start both the application and MySQL
database:

```bash
docker-compose up -d
```

This will:

- Start MySQL 9.5.0 container with the database schema automatically initialized
- Build and start the Spring Boot application
- The application will be available at `http://localhost:8080`

To stop the application:

```bash
docker-compose down
```

To stop and remove volumes (clears database data):

```bash
docker-compose down -v
```

**Docker Environment Details:**

- MySQL Version: `9.5.0`
- MySQL Database: `burgers`
- MySQL Username: `yu71`
- MySQL Password: `53cret`
- MySQL Port: `3306`
- Application Port: `8080`

**Note:** MySQL 9.5.0 requires a `tmpfs` mount for `/var/run/mysqld` to properly handle unix socket files. This has been
configured in the docker-compose.yml file

### Option 2: Running Locally

After building, run the application using:

```bash
java -jar target/spring-burger-0.0.1-SNAPSHOT.jar
```

Or use Maven:

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

**Note:** For local development, you need to set up MySQL manually (see Database Setup section)

## Project Structure

```
spring-burger/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/hendisantika/springburger/
│   │   │       ├── SpringBurgerApplication.java
│   │   │       └── model/
│   │   │           ├── Burger.java
│   │   │           └── Devourer.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── schema.sql
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   └── fragments/
│   │       │       └── burgers.html
│   │       └── public/
│   │           ├── img/
│   │           ├── js/
│   │           └── style/
│   └── test/
│       └── java/
└── pom.xml
```

## Docker Support

The application includes full Docker support with:

- **Dockerfile**: Multi-stage build for optimized image size
- **docker-compose.yml**: Complete setup with MySQL 9.5.0 database
- **Automatic database initialization**: Schema is loaded on first startup
- **Health checks**: Ensures MySQL is ready before starting the application
- **Persistent volumes**: Database data persists between container restarts
- **Spring profiles**: Separate configuration for Docker and local environments
- **MySQL 9.5.0 Support**: Configured with tmpfs mount to handle unix socket files properly

### Useful Docker Commands

```bash
# Start services
docker-compose up -d

# View logs
docker-compose logs -f app

# Access MySQL container
docker exec -it springburger-mysql mysql -uyu71 -p53cret burgers

# Rebuild after code changes
docker-compose up -d --build

# Stop services
docker-compose down

# Stop and remove all data
docker-compose down -v
```

## Lombok Integration

This project uses **Lombok** to reduce boilerplate code in model classes. Lombok provides annotations that automatically
generate:

- Getters and setters (`@Data`)
- Constructors (`@NoArgsConstructor`, `@AllArgsConstructor`)
- `toString()`, `equals()`, and `hashCode()` methods
- And much more!

### Model Classes Using Lombok

Both `Burger` and `Devourer` entities use the following Lombok annotations:

- `@Data` - Generates getters, setters, toString, equals, and hashCode
- `@NoArgsConstructor` - Generates a no-argument constructor (required by JPA)
- `@AllArgsConstructor` - Generates a constructor with all fields

This significantly reduces code verbosity while maintaining full functionality.

## Recent Updates

- **Added Lombok support** to reduce boilerplate code in model classes
- Added Docker and Docker Compose support with MySQL 9.5.0
- Configured MySQL 9.5.0 container with custom credentials (username: `yu71`, password: `53cret`)
- Fixed MySQL 9.5.0 socket handling with tmpfs mount for `/var/run/mysqld`
- Added Spring profile configuration for Docker environment
- Migrated from javax.persistence to jakarta.persistence for Spring Boot 3.x compatibility
- Updated JUnit 4 to JUnit 5 (Jupiter)
- Refactored model classes to use Lombok annotations
- Updated to Spring Boot 3.5.7

## License

This project is open source and available for educational purposes.
