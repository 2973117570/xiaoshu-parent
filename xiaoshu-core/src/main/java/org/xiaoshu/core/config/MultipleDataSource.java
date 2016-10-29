package org.xiaoshu.core.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 
 * 功能说明：提供数据源的设置操作，通过setDataSourceKey进行数据源设置
 * 
 * MultipleDataSource.java
 * 
 * Original Author: liangliangl.jia-贾亮亮,2016年10月29日上午10:25:21
 * 
 * Copyright (C)1997-2016 小树盛凯科技 All rights reserved.
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

	public static void setDataSourceKey(String dataSource) {
		dataSourceKey.set(dataSource);
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return dataSourceKey.get();
	}
}