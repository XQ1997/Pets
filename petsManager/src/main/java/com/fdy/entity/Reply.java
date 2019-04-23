package com.fdy.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Reply implements Serializable {
    private Integer id;

    /**
     * 留言人
     */
    private String wordname;

    /**
     * 回复人
     */
    private String replyname;

    /**
     * 回复内容
     */
    private String content;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWordname() {
        return wordname;
    }

    public void setWordname(String wordname) {
        this.wordname = wordname;
    }

    public String getReplyname() {
        return replyname;
    }

    public void setReplyname(String replyname) {
        this.replyname = replyname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}