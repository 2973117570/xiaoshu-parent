/**
*	Copyright © 2013 - 2016 Ubtrobot Tech. All Rights Reserved 
*/
package org.xiaoshu.core.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * 
 * 功能说明：MyBatis的配置信息，参数请参考 application.properties
 * 
 * MyBatisConfig.java
 * 
 * Original Author: liangliangl.jia-贾亮亮,2016年10月29日上午10:25:46
 * 
 * Copyright (C)1997-2016 小树盛凯科技 All rights reserved.
 */
@Configuration // 该注解类似于spring配置文件
public class MyBatisConfig implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }
    
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage(environment.getProperty("Mybatis.BasePackage"));
        Properties props = new Properties();
        props.setProperty("mappers", environment.getProperty("Mybatis.mappers"));
        props.setProperty("IDENTITY", environment.getProperty("Mybatis.IDENTITY"));
        props.setProperty("notEmpty", environment.getProperty("Mybatis.notEmpty"));
        scannerConfigurer.setProperties(props);
        return scannerConfigurer;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dataSource);// 指定数据源(这个必须有，否则报错)
        // 下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
        fb.setTypeAliasesPackage(environment.getProperty("Mybatis.basemodel"));// 指定基包
        fb.setMapperLocations(resolver.getResources(environment.getProperty("Mybatis.mapper.xml")));// **Mapper.xml配置文件地址
        fb.setConfigLocation(resolver.getResource(environment.getProperty("Mybatis.config.resources")));//设置分页插件信息
        return fb.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
