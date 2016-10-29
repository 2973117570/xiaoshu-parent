package org.xiaoshu.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * 功能说明：
 * 
 * Swagger2Configuration.java
 * 
 * Original Author: liangliangl.jia-贾亮亮,2016年10月29日上午10:12:22
 * 
 * Copyright (C)1997-2016 小树盛凯科技 All rights reserved.
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
	 	@Bean
	    public Docket buildDocket() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(buildApiInf())
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("org.xiaoshu.service.controller"))//要扫描的API(Controller)基础包
	                .paths(PathSelectors.any())
	                .build();
	    }
	    private ApiInfo buildApiInf() {
	        return new ApiInfoBuilder()
	                .title("Spring Boot中使用Swagger2 UI构建API文档")
	                .contact("deane163@126.com")
	                .version("1.0")
	                .build();
	    }
}
