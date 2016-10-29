package org.xiaoshu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;
/**
 * 
 * 功能说明：
 * 
 * ApplicationServerStart.java
 * 
 * Original Author: liangliangl.jia-贾亮亮,2016年10月29日上午9:28:42
 * 
 * Copyright (C)1997-2016 小树盛凯科技 All rights reserved.
 */

@Configuration
@EnableAutoConfiguration
@ComponentScan
//@ImportResource("classpath:application-service-provider.xml")
@EnableScheduling
@EnableAspectJAutoProxy
public class ApplicationServerStart  extends SpringBootServletInitializer  {
	private final static Logger _logger = LoggerFactory.getLogger(ApplicationServerStart.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationServerStart.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		_logger.info("start up the ImageServer server ...");
		return application.sources(ApplicationServerStart.class);
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncodingFilter);
		return registrationBean;
	}
}
