package com.fdy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Fodder implements Serializable {
    private Integer id;

    /**
     * 宠物类别
     */
    private String type;

    /**
     * 使用数量
     */
    private Integer number;

    /**
     * 该种类饲料单袋单价
     */
    private Double price;

    /**
     * 总共花费总价
     */
    private Double totalnum;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(Double totalnum) {
        this.totalnum = totalnum;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}