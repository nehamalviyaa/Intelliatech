package com.info.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI productInventoryOpenAPI() {
		
		return new OpenAPI()
                .info(new Info()
                        .title("Product Inventory Management API")
                        .description("Industry standard Product Inventory system with UUID, Pagination, Auditing")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Neha Malviya")
                                .email("nehamalviya819@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                );
		
	}

}



//http://localhost:8080/swagger-ui/index.html
