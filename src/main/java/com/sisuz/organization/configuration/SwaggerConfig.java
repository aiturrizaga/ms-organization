package com.sisuz.organization.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

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

}
