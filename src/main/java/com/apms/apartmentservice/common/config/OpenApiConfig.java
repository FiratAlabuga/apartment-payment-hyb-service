package com.apms.apartmentservice.common.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * Configuration class named {@link OpenApiConfig} for OpenAPI documentation in the Apartment Service application.
 * This class defines the metadata for the OpenAPI documentation, including the title, version,
 * description, contact information for the API and , and JWT-based security scheme.
 */
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Fırat Alabuga",
                        url = "https://github.com/FiratAlabuga/apartmentservice"
                ),
                description = "Case Study - Apartment Service" +
                        " (Java 21, Spring Boot, MySql, JUnit, Spring Security, JWT, Docker, Kubernetes, Prometheus, Grafana, Github Actions (CI/CD) ) ",
                title = "apartment-service",
                version = "1.0.0"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}