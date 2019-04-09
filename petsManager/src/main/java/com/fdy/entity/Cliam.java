package com.fdy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Cliam implements Serializable {
    
	public static final String STATE_PASS = "已通过";
    public static final String STATE_IN = "已提交";
    public static final String STATE_NO = "未通过";
	
	private Integer id;

    /**
     * 领养人姓名
     */
    private String username;

    /**
     * 领养宠物名字
     */
    private String petname;

    /**
     * 领养原因
     */
    private String content;

    /**
     * 领养人联系电话
     */
    private String mobile;

    /**
     * 审核状态
     */
    private String state;

    /**
     * 审核人
     */
    private String cliamName;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCliamName() {
        return cliamName;
    }

    public void setCliamName(String cliamName) {
        this.cliamName = cliamName;
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