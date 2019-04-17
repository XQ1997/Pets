package com.nf.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class Pets implements Serializable {

    public static final String STATE_CLIAM = "已认领";
    public static final String STATE_NO = "未认领";
    private Integer id;

    /**
     * 流浪宠物名字
     */
    private String petname;

    /**
     * 流浪宠物认领状态
     */
    private String state;

    /**
     * 流浪宠物年龄
     */
    private Integer age;

    /**
     * 描述
     */
    private String content;

    /**
     * 宠物编号
     */
    private String num;

    /**
     * 宠物视频
     */
    private String video;

    /**
     * 宠物照片
     */
    private String image;

    /**
     * 救助该宠物的地点
     */
    private String place;

    /**
     * 对应的种类
     */
    private String type;

    /**
     * 领养人
     */
    private String accname;

    /**
     * 认领该宠物的主人
     */
    private Integer userid;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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