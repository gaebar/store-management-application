# WIP Store Management Application

Status: Work in Progress

## Overview

The Store Management Application streamlines the process of opening new stores, managing inventory, and optimizing store
operations. The main aim is to eliminate the inefficiencies, inaccuracies, and delays in store operations that arise
from manual processes.

## Technology Stack

- **Backend:** [Java](https://www.java.com/) with [Spring Boot](https://spring.io/projects/spring-boot)
- **Security:** [Spring Security](https://spring.io/projects/spring-security) with JWT for authentication
- **Database:** [H2 Database](https://www.h2database.com/)
- **Object-Relational Mapping (ORM):** [Hibernate](https://hibernate.org/)
  with [JPA](https://jakarta.ee/specifications/persistence/)
- **Data Validation:** [Jakarta Persistence API](https://jakarta.ee/specifications/persistence/)
- **Build and Dependency Management:** [Maven](https://maven.apache.org/)
- **Code Simplification:** [Lombok](https://projectlombok.org/)
- **API Design:** RESTful APIs
- **API Documentation:** [Swagger (OpenAPI)](https://swagger.io/)

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

For detailed project requirements, see [Project Requirements](ProjectRequirements.md).

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

- If the API requires authentication, click the "Authorize" button in the Swagger UI to input your JWT credentials.
- Once authorized, you will be able to send requests to protected endpoints directly from the Swagger UI.

## Installation and Setup

To be added once the application is ready for deployment.

## Usage

## Screenshots

## Future Enhancements

- Real-time notification system for stock thresholds.
- Integration with third-party e-commerce platforms.
- Analytics dashboard for business insights.

## Contributing

For major changes, please open an issue first to discuss what you would like to change. Pull requests are welcome.
Ensure to update tests as appropriate.

License
[MIT](https://choosealicense.com/licenses/mit/)
