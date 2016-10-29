package org.xiaoshu.common.mapper;

import java.util.Map;

import org.xiaoshu.common.model.BaseExpandModel;

import tk.mybatis.mapper.common.Mapper;

import com.github.pagehelper.Page;

public interface BaseExpandMapper<T extends BaseExpandModel> extends Mapper<T>{

	/** 条件分页查询 */
	Page<T> query(Map<String, Object> params);
}
