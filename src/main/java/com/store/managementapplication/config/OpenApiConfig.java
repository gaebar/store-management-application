package com.store.managementapplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Gaetano Barreca",
                        email = "gaetanobarreca@gmail.com"
                ),
                title = "Store Management API",
                version = "1.0",
                description = "API for managing stores and users",
                license = @License(name = "MIT License", url = "https://github.com/gaebar/store-management-application/blob/master/LICENSE.txt")
        ),
        servers = @Server(url = "http://localhost:8080")

)

public class OpenApiConfig {

}
