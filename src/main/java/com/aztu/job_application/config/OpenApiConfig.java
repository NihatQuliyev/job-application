package com.aztu.job_application.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        title = "Job Application",
        description = "",
        termsOfService = "",
        contact = @Contact(
                name = "Nihat Guliyev",
                url = "",
                email = "nihat@div.edu.az"
        ),
        version = "0.0.1"
))
@Configuration
public class OpenApiConfig {

        private SecurityScheme createAPIKeyScheme() {
                return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                        .bearerFormat("JWT")
                        .scheme("bearer");
        }

        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI().addSecurityItem(new SecurityRequirement().
                                addList("Bearer Authentication"))
                        .components(new Components().addSecuritySchemes
                                ("Bearer Authentication", createAPIKeyScheme()))
                        .info(new io.swagger.v3.oas.models.info.Info().title("My REST API")
                                .description("Some custom description of API.")
                                .version("1.0").contact(new io.swagger.v3.oas.models.info.Contact().name("Guliyev Nihat")
                                        .email("www.ecommerce.com").url("nihat@div.edu.az"))
                                .license(new License().name("E-commerce of API")
                                        .url("API license URL")));

        }
}
