package com.sisuz.organization.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("Organization API")
                        .description("Organization REST API services")
                        .license(new License().name("SISUZ").url("https://sisuz.com"))
                        .version("1.0.0")
                );
    }

    @Bean
    public OpenApiCustomizer companyHeaderForStoreEndpoints() {
        return openApi -> {
            if (openApi.getPaths() == null) return;

            openApi.getPaths().forEach((path, pathItem) -> {
                if (!path.startsWith("/v1/stores")) return;

                pathItem.readOperations().forEach(operation -> {
                    boolean exists = operation.getParameters() != null
                            && operation.getParameters().stream().anyMatch(p ->
                            "Company-Id".equalsIgnoreCase(p.getName())
                                    && "header".equalsIgnoreCase(p.getIn())
                    );

                    if (!exists) {
                        Parameter companyHeader = new Parameter()
                                .in("header")
                                .name("Company-Id")
                                .required(true)
                                .description("Company context id")
                                .schema(new StringSchema().format("uuid"));

                        operation.addParametersItem(companyHeader);
                    }
                });
            });
        };
    }

}
