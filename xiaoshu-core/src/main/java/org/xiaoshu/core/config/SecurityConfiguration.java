/**
 *	Copyright © 2013 - 2016 Ubtrobot Tech. All Rights Reserved 
 */
package org.xiaoshu.core.config;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能说明：Spring Security Oauth2.0 配置文件信息
 * 
 * SecurityConfiguration.java
 * 
 * Original Author: liangliangl.jia-贾亮亮,2016年10月29日上午10:26:16
 * 
 * Copyright (C)1997-2016 小树盛凯科技 All rights reserved.
 */
@Configuration
public class SecurityConfiguration {

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean
				.setFilter(new org.springframework.web.filter.DelegatingFilterProxy());
		filterRegistrationBean.setName("springSecurityFilterChain");
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

}
