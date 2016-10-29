
package org.xiaoshu.core.config;


import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 
 * 功能说明：Druid 配置文件信息，配置拦截文件，监控文件配置，监控SQL的执行
 * 
 * DruidConfiguration.java
 * 
 * Original Author: liangliangl.jia-贾亮亮,2016年10月29日上午10:24:33
 * 
 * Copyright (C)1997-2016 小树盛凯科技 All rights reserved.
 */
@Configuration
public class DruidConfiguration {

	@Bean
	public ServletRegistrationBean druidServlet(){
		return new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
	}
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean(){
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}
