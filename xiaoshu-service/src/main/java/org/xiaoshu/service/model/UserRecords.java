package org.xiaoshu.service.model;

import javax.persistence.*;

import org.xiaoshu.common.model.BaseExpandModel;

/**
 * 
 * 功能说明：model 声明
 * 
 * CommonQueryService.java
 * 
 * Original Author: deane.jia,2016年9月14日 下午6:55:19
 * 
 * Copyright (C)2013-2016 深圳优必选科技 All rights reserved.
 */
@Table(name = "user_records")
public class UserRecords extends BaseExpandModel{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String email;

    private String password;

    private String nick;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return nick
     */
    public String getNick() {
        return nick;
    }

    /**
     * @param nick
     */
    public void setNick(String nick) {
        this.nick = nick;
    }
}