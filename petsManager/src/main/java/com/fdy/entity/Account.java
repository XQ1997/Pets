package com.fdy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Account implements Serializable {
    
	public static final String TYPE_ADMIN = "管理员";
    public static final String TYPE_USER = "会员";
	
	private Integer id;

    /**
     * 角色，一般有管理员，领养人角色
     */
    private String role;

    private String username;

    private Integer age;

    /**
     * 性别
     */
    private String sex;

    private String address;

    /**
     * 身份证号
     */
    private String cardnum;

    private String job;

    private String password;

    /**
     * 一寸照
     */
    private String userPhoto;

    /**
     * 身份证正面照
     */
    private String cardInPhoto;

    /**
     * 身份证背面照
     */
    private String cardOutPhoto;

    /**
     * 用户编号
     */
    private String number;

    private String mobile;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getCardInPhoto() {
        return cardInPhoto;
    }

    public void setCardInPhoto(String cardInPhoto) {
        this.cardInPhoto = cardInPhoto;
    }

    public String getCardOutPhoto() {
        return cardOutPhoto;
    }

    public void setCardOutPhoto(String cardOutPhoto) {
        this.cardOutPhoto = cardOutPhoto;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}