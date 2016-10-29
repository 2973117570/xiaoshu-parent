package org.xiaoshu.service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;


/**
 * 
 * 功能说明：
 * 
 * MainController.java
 * 
 * Original Author: liangliangl.jia-贾亮亮,2016年10月29日上午9:35:38
 * 
 * Copyright (C)1997-2016 小树盛凯科技 All rights reserved.
 */
@RestController
@RequestMapping(value="/admin")
public class MainController {

	private final static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="/status",method=RequestMethod.GET)
	public String  getServerStatus(){
		logger.info("get Server status");
		return JSON.toJSONString("服务器状态正常");
	}
}
