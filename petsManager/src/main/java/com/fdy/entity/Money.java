package com.fdy.entity;

import java.io.Serializable;
import java.util.Date;

/**捐助记录实体类
 * @author 
 */
public class Money implements Serializable {
    private Integer id;

    /**
     * 救助来源：政府救助或社会捐款
     */
    private String type;

    /**
     * 救助金额
     */
    private Double price;

    /**
     * 捐助时间
     */
    private Date createTime;

    /**
     * 捐助经办人
     */
    private String name;

    /**
     * 联系电话
     */
    private String mobile;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}