package com.ynov.olympicker.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        Components components = new Components().addSecuritySchemes("bearer-jwt", securityScheme);
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearer-jwt", Arrays.asList("read", "write"));
        License license = new License().name("Apache 2.0").url("https://springdoc.org/");
        Info info = new Info().title("Olympicker API").version("v0.0.1").license(license);

        return new OpenAPI()
                .info(info)
                .components(components)
                .addSecurityItem(securityRequirement);
    }

    @Bean
    public GroupedOpenApi completeApi() {
        return GroupedOpenApi.builder().group("Complete").pathsToMatch("/**").build();
    }
}