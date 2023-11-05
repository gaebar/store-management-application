# Store Management Application

## Overview

The Store Management Application streamlines the process of opening new stores, managing inventory, and optimizing store
operations. The main aim is to eliminate the inefficiencies, inaccuracies, and delays in store operations that arise
from manual processes.

## Technology Stack

- **Backend:** [Java](https://www.java.com/) with [Spring Boot](https://spring.io/projects/spring-boot) - for creating
  RESTful services.
- **Security:** [Spring Security](https://spring.io/projects/spring-security) with JWT for authentication - ensures
  secure access control.
- **Database:** [H2 Database](https://www.h2database.com/) - in-memory database for rapid development and testing.
- **Object-Relational Mapping (ORM):** [Hibernate](https://hibernate.org/)
  with [JPA](https://jakarta.ee/specifications/persistence/) - for efficient database operations and easy data
  manipulation.
- **Data Validation:** Enforced by the [Jakarta Persistence API](https://jakarta.ee/specifications/persistence/) - for
  robust data integrity.
- **Build and Dependency Management:** Managed with [Maven](https://maven.apache.org/) - simplifies project builds and
  manages dependencies.
- **Code Simplification:** [Lombok](https://projectlombok.org/)- minimizes boilerplate code for cleaner and more
  readable codebase.
- **API Design:** RESTful APIs - for scalable and maintainable service architecture.
- **API Documentation:** [Swagger (OpenAPI)](https://swagger.io/) - provides interactive documentation, allowing users
  to understand and consume services easily.

## Features

- **Store Creation and Search:** Allows admins to create stores and users to search for them based on various criteria.
- **Item Creation and Search:** Admins can create items and users can search for items.
- **Inventory Management:** Store managers can add items to their store's inventory and monitor stock levels.
- **Purchase Order Management:** Facilitates the creation, display, and management of purchase orders.

## User Roles

1. **Admin:** Manages stores, user access, item categories, and generates purchase orders.
2. **Store Manager:** Manages the store's inventory and generates purchase orders.
3. **Store Staff:** Views store inventory, requests item additions, and updates item quantities.

## Project Requirements

For detailed specifications and features, please refer to [Project Requirements](ProjectRequirements.md).

## API Documentation

Interactive API documentation is provided through Swagger UI. This tool facilitates a clear understanding and
interaction with the API's endpoints.

### Using Swagger UI

- Navigate to [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html) once the application is
  running to view and interact with the API's endpoints.
- Explore the comprehensive list of available REST endpoints.
- View detailed information about request and response formats by expanding each endpoint.
- Directly test API endpoints using the "Try it out" feature within the Swagger UI interface.

### Security in Swagger

- Use the `authenticate` endpoint to log into the application with the default admin credentials

```
{
  "email": "admin@example.com",
  "password": "adminPassword"
}
```

- The `authenticate` endpoint will return a JWT token that can be used to authorize requests to protected endpoints.

```
{
  "token": "XXX"
}
```

- Click the "Authorize" button at the top of the Swagger UI page, and input your JWT token value.
- Once authorized, you will be able to send requests to protected endpoints directly from the Swagger UI.

## Installation Steps

To get started with the Store Management Application, follow these steps:

### 1. **Clone the Repository:**

```bash
git clone https://github.com/gaebar/store-management-application.git
```

### 2. Navigate to the Project Directory:

```bash
cd store-management-application
```

### 3. Ensure Prerequisites are Installed:

Ensure Java, Maven and MySQL are installed and properly configured on your system before starting.
The application is tested with Java 17 and Maven 3.9.4.

### 4. Start the application:

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### 5. Test the application endpoints:

- Run the HTTP requests in IntelliJ IDEA's REST Client or Postman to test the application's endpoints.
- The sample requests are provided in the [http](http) folder.
- Alternatively, use the Swagger UI to test the application's endpoints.

## Future Enhancements

- Real-time notification system for stock thresholds.
- Integration with third-party e-commerce platforms.
- Analytics dashboard for business insights.

## Contributing

Your contributions are welcome! If you're interested in improving the Store Management Application, please feel free to
fork the repository and submit a pull request. For substantial changes, please open an issue first to discuss what you
propose. Check out the [Contribution Guidelines](CONTRIBUTING.md) for more details on how to contribute effectively.

## License

The Store Management Application is made available as open source under the [MIT License](LICENSE.txt), which provides a
broad permission to use, modify, and distribute the software.

