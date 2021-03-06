package com.example.university.config;


import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * @see <a href="http://localhost:8080/swagger-ui.html">Swagger page</a>
 */
@Configuration
@EnableSwagger2
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SwaggerConfig {

    final TypeResolver typeResolver;

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
//                .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .apis(Predicate.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(AlternateTypeRules.newRule(
                        typeResolver.resolve(
                                DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)
                        ),
                        typeResolver.resolve(WildcardType.class)
                ))
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(true);
    }
}