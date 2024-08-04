package com.E_CommerceApplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Let's Buy",
                description = "This API is for E_Commerce Backend Application",
                termsOfService = "Terms of service",
                contact = @Contact(
                        name = "Achaman",
                        email = "achamanpathak6@gmail.com",
                        url = "https://codewithachaman"
                ),
                license = @License(name = "Achaman Licence"),
                version = "v3"
        ),
        servers = {
                @Server(description = "testEnv", url = "http://localhost:8080"),
                @Server(description = "devEnv", url = "http://localhost:8080")
        },
        security = @SecurityRequirement(name = "Achaman's Security")
)
@SecurityScheme(
        name = "Achaman's Security",
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer",
        description = "This is Jwt Token Authorization"
)
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springshop-public")
                .pathsToMatch("/**")
                .build();
    }
}

