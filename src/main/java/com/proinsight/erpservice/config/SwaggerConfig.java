/**
 * 
 */
package com.proinsight.erpservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * @author Ajayi Segun on 21st August 2019
 *
 */
@Configuration
public class SwaggerConfig {
	@Bean
    public OpenAPI customConfiguration() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Olade Consulting ERP REST API")
                        .description("Spring Boot REST API for ERP Service"));
       
    }

}
