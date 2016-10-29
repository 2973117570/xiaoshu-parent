package org.xiaoshu.common.provider.impl;



import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;
import org.xiaoshu.common.mapper.BaseExpandMapper;
import org.xiaoshu.common.model.BaseExpandModel;
import org.xiaoshu.common.provider.BaseProvider;
import org.xiaoshu.common.util.Constants;
import org.xiaoshu.common.util.DataUtil;
import org.xiaoshu.common.util.DateUtil;
import org.xiaoshu.common.util.DateUtil.DATE_PATTERN;
import org.xiaoshu.common.util.InstanceUtil;
import org.xiaoshu.common.util.RedisUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 业务逻辑层基类<br/>
 * 继承基类后必须配置CacheConfig(cacheNames="")
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public abstract class BaseProviderImpl<T extends BaseExpandModel> implements BaseProvider<T> {
    @Autowired
    BaseExpandMapper<T> mapper;

    /** 启动分页查询 */
    protected void startPage(Map<String, Object> params) {
        if (DataUtil.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (DataUtil.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 10);
        }
        if (DataUtil.isEmpty(params.get("orderBy"))) {
            params.put("orderBy", "id_ desc");
        }
        PageHelper.startPage(params);
    }

    @SuppressWarnings("unchecked")
    private BaseProviderImpl<T> getProvider() {
        return InstanceUtil.getBean(getClass());
    }

    /** 生成主键策略 */
    public String createId(String key) {
        String redisKey = "REDIS_TBL_" + key;
        String dateTime = DateUtil.getDateTime(DATE_PATTERN.YYYYMMDDHHMMSSSSS);
        return dateTime + RedisUtil.incr(redisKey);
    }

    /** 根据Id查询(默认类型T) */
    public PageInfo<T> getPage(Page<String> ids) {
        if (ids != null) {
            Page<T> page = new Page<T>(ids.getPageNum(), ids.getPageSize());
            page.setTotal(ids.getTotal());
            page.clear();
            BaseProviderImpl<T> provider = getProvider();
            for (String id : ids) {
                page.add(provider.queryById(id));
            }
            return new PageInfo<T>(page);
        }
        return new PageInfo<T>();
    }

    /** 根据Id查询(cls返回类型Class) */
    public <K> PageInfo<K> getPage(Page<String> ids, Class<K> cls) {
        if (ids != null) {
            Page<K> page = new Page<K>(ids.getPageNum(), ids.getPageSize());
            page.setTotal(ids.getTotal());
            page.clear();
            BaseProviderImpl<T> provider = getProvider();
            for (String id : ids) {
                T t = provider.queryById(id);
                K k = InstanceUtil.to(t, cls);
                page.add(k);
            }
            return new PageInfo<K>(page);
        }
        return new PageInfo<K>();
    }

    /** 根据Id查询(默认类型T) */
    public List<T> getList(List<String> ids) {
        List<T> list = InstanceUtil.newArrayList();
        if (ids != null) {
            for (String id : ids) {
                list.add(getProvider().queryById(id));
            }
        }
        return list;
    }

    /** 根据Id查询(cls返回类型Class) */
    public <K> List<K> getList(List<String> ids, Class<K> cls) {
        List<K> list = InstanceUtil.newArrayList();
        if (ids != null) {
            for (String id : ids) {
                T t = getProvider().queryById(id);
                K k = InstanceUtil.to(t, cls);
                list.add(k);
            }
        }
        return list;
    }

    @Transactional
    public void delete(String id, String userId) {
        try {
            T record = queryById(id);
            mapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    @Transactional
    public void insert(T t) {
    	mapper.insert(t);
    }

    @Transactional
    public T update(T record) {
        mapper.updateByPrimaryKey(record);
        return record;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public T queryById(String id) {
        try {
            T record =  mapper.selectByPrimaryKey(id);
            return record;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /** 获取缓存键值 */
    private String getCacheKey(Object id) {
        String cacheName = null;
        CacheConfig cacheConfig = getClass().getAnnotation(CacheConfig.class);
        if (cacheConfig == null || cacheConfig.cacheNames() == null || cacheConfig.cacheNames().length < 1) {
            cacheName = getClass().getName();
        } else {
            cacheName = cacheConfig.cacheNames()[0];
        }
        return new StringBuilder(Constants.CACHE_NAMESPACE).append(cacheName).append(":").append(id).toString();
    }

    public PageInfo<T> query(Map<String, Object> params) {
    	this.startPage(params);
        Page<T> page = mapper.query(params);
        return  new PageInfo<T>(page);
    }
}
