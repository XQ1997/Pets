package com.fdy.entity;

import java.io.Serializable;
import java.util.Date;

/**宠物就医记录
 * @author 
 */
public class Sick implements Serializable {
    private Integer id;

    /**
     * 宠物名称
     */
    private String petname;

    /**
     * 宠物编号
     */
    private Integer petnum;

    /**
     * 宠物类别
     */
    private String petType;

    /**
     * 宠物生病类型
     */
    private String sickType;

    /**
     * 花费
     */
    private Double money;

    /**
     * 创建时间
     */
    private Date createTime;

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

    public Integer getPetnum() {
        return petnum;
    }

    public void setPetnum(Integer petnum) {
        this.petnum = petnum;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public String getSickType() {
        return sickType;
    }

    public void setSickType(String sickType) {
        this.sickType = sickType;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}