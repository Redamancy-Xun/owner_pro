package com.forum.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@ConditionalOnExpression("${swagger.enable:true}")//当enable为true时才选择加载该配置类
@EnableSwagger2
public class Swagger2Config {


    @Bean
    public Docket createAccountApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("account")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.phoenix.logistics.controller.account"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createAdminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("admin")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.phoenix.logistics.controller.admin"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createUserApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.phoenix.logistics.controller.user"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createRequestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("request")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.phoenix.logistics.controller.request"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createResponseApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("response")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.phoenix.logistics.controller.response"))//设定扫描范围
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("物流平台 API Documentation")
                .description("物流平台接口文档")
//                .termsOfServiceUrl("http://localhost:8081/swagger-ui.html")//数据源
                .version("1.0")
                .build();
    }
}
