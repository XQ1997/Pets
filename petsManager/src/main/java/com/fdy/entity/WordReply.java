package com.fdy.entity;

import java.io.Serializable;

/**
 * @author 
 */
public class WordReply implements Serializable {
    /**
     * 留言id
     */
    private Integer wordId;

    /**
     * 回复id
     */
    private Integer replyId;

    private static final long serialVersionUID = 1L;

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }
}