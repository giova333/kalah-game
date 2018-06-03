package com.gladunalexander.kalah.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Basic API documentation configuration.
 *
 * Created by Alexander Gladun on 02/06/2018.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gladunalexander.kalah.controller"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Oleksandr Hladun", null, "aleksgladun4@gmail.com");
        return new ApiInfoBuilder()
                .title("Kalah Game")
                .description("Java RESTful Web Service that runs a game of 6-stone Kalah")
                .contact(contact)
                .version("1.0.0")
                .build();

    }
}
