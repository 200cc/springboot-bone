package me.cc200.base.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Objects;

@Configuration
public class Swagger2Config {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("groupName")
                .useDefaultResponseMessages(false)
                .apiInfo(new ApiInfoBuilder()
                        .title("title")
                        .description("desc")
                        .termsOfServiceUrl("serviceUrl")
                        .contact(new Contact("name", "url", "email"))
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("me.cc200"))
                .paths(path -> !StringUtils.isEmpty(path))
                .paths(path -> Objects.requireNonNull(path).startsWith("/api"))
                .build();
    }
}
