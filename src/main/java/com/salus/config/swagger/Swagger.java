package com.salus.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {

    @Bean
    public OpenAPI configuration() {

        return new OpenAPI()
                .info(
                        new Info().title("Salus")
                                .version("v1")
                                .termsOfService("www.salus.com/terms-services")
                                .license(new License().name("License")
                                        .url("www.salus.com/license"))
                );
    }
}
