package com.study.common.config.swagger;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Harlan
 * @date 2021 10 16 15:17
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties(com.study.common.config.swagger.SwaggerProperties.class)
public class SwaggerAutoConfig {

    private static final String AUTH = "Authorization";

    private final SwaggerProperties swaggerProperties;

    public SwaggerAutoConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
        //安全配置
        docket.securitySchemes(getSecuritySchemes())
                .securityContexts(getSecurityContexts());
        return docket;
    }

    /**
     * api信息简介
     * @return apiInfo
     */
    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact(swaggerProperties.getAuthor(), swaggerProperties.getUrl(), swaggerProperties.getEmail()))
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                .build();
    }

    private List<SecurityScheme> getSecuritySchemes() {
        return Collections.singletonList(
                new ApiKey(AUTH, AUTH, AUTH));
    }

    private List<SecurityContext> getSecurityContexts() {
        AuthorizationScope[] authScopeArray = new AuthorizationScope[]{new AuthorizationScope("global", "accessResource")};
        List<SecurityReference> referenceList = new ArrayList<>();
        referenceList.add(new SecurityReference(AUTH, authScopeArray));
        return Collections.singletonList(new SecurityContext(referenceList, PathSelectors.any(), httpMethod -> true, operationContext -> true));
    }
}
