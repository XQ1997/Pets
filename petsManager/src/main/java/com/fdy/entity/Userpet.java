package com.fdy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Userpet implements Serializable {

    private Integer id;

    /**
     * 宠物学名
     */
    private String petLanguageName;

    /**
     * 宠物名称
     */
    private String petname;

    /**
     * 宠物年龄
     */
    private Integer age;

    /**
     * 宠物描述
     */
    private String content;

    /**
     * 发布类型
     */
    private String sendtype;

    /**
     * 发布人联系电话
     */
    private String mobile;

    /**
     * 宠物照片
     */
    private String image;

    /**
     * 发布地点
     */
    private String place;

    /**
     * 认领状态
     */
    private String state;

    /**
     * 发布人姓名
     */
    private String username;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPetLanguageName() {
        return petLanguageName;
    }

    public void setPetLanguageName(String petLanguageName) {
        this.petLanguageName = petLanguageName;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendtype() {
        return sendtype;
    }

    public void setSendtype(String sendtype) {
        this.sendtype = sendtype;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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