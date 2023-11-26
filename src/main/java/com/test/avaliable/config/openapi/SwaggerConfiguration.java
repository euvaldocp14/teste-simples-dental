package com.test.avaliable.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI(@Value("${version:latest}") String version){
        return new OpenAPI().info(info(version));
    }

    private Info info(String version) {
        return new Info()
                .title("API Exericio de Teste");
    }
}
