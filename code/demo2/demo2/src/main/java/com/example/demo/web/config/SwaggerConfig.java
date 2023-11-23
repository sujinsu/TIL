package com.example.demo.web.config;

import com.fasterxml.classmate.TypeResolver;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.ModelRendering;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *  Swagger 관련 설정
 */
@Configuration
@RequiredArgsConstructor
@EnableSwagger2
public class SwaggerConfig {
    private final TypeResolver typeResolver; // Swagger에서 page, size, sort 속성명으로 RequestParam 전달하기 위해 적용

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class)) //
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("KB Data System Saas LLM Agent")
                .description("KB Data System Saas LLM  Agent API V1")
                .version("1.0.0")
                .contact(new Contact(
                        "KB Data System"
                        , "https://www.kds.co.kr/about"
                        , "@kbfg.com")
                )
                .build();
    }

    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(false)
                .displayOperationId(false)    // default false
                .defaultModelsExpandDepth(3)
                .defaultModelExpandDepth(3)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.METHOD)
                .showExtensions(true)
                .validatorUrl(null)
                .build();
    }
}
