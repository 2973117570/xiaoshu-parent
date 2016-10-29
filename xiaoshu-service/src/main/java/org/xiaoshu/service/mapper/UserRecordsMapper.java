package org.xiaoshu.service.mapper;

import java.util.List;

import org.xiaoshu.common.mapper.BaseExpandMapper;
import org.xiaoshu.service.model.UserRecords;


/**
 * 
 * 功能说明：用户信息的持久化，接口声明
 * 
 * CommonQueryService.java
 * 
 * Original Author: deane.jia,2016年9月14日 下午6:54:45
 * 
 * Copyright (C)2013-2016 深圳优必选科技 All rights reserved.
 */
public interface UserRecordsMapper extends BaseExpandMapper<UserRecords> {
	
	public List<UserRecords> login(UserRecords userRecords);
	
}