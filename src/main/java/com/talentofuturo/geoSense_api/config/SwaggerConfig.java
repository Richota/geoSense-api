package com.talentofuturo.geoSense_api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("GeoSense API")
                .version("v1")
                .description("API para administrar compañías, sensores y datos IoT."))
            .components(new Components()
                .addSecuritySchemes("CompanyApiKey",
                    new SecurityScheme()
                        .type(Type.APIKEY)
                        .in(In.HEADER)
                        .name("company_api_key"))
                .addSecuritySchemes("SensorApiKey",
                    new SecurityScheme()
                        .type(Type.APIKEY)
                        .in(In.HEADER)
                        .name("sensor_api_key")));
    }

    @Bean
    public OperationCustomizer customizeSecurityRequirements() {
        return (operation, handlerMethod) -> {
            String path = operation.getTags().isEmpty() ? "" : operation.getTags().get(0).toLowerCase();

            if (path.contains("company")) {
                operation.addSecurityItem(new SecurityRequirement().addList("CompanyApiKey"));
            } else if (path.contains("sensor")) {
                operation.addSecurityItem(new SecurityRequirement().addList("SensorApiKey"));
            }
            return operation;
        };
    }
}
