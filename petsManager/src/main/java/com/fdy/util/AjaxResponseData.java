package com.fdy.util;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * ajax请求返回的对象
 * @author Administrator
 * 使用 @JsonInclude 注解可以控制在哪些情况下才将被注解的属性转换成 json，JsonInclude.Include.NON_NULL属性标明当属性不为null时，才将属性转换为json数据
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AjaxResponseData {

    private static final String STATE_SUCCESS = "success";
    private static final String STATE_ERROR = "error";

    private String state;
    private String message;
    private Object data;

    public AjaxResponseData(){}

    public static AjaxResponseData error(String message){
        AjaxResponseData ajaxResponseData = new AjaxResponseData();
        ajaxResponseData.setState(STATE_ERROR);
        ajaxResponseData.setMessage(message);
        return ajaxResponseData;
    }
    public static AjaxResponseData success(){
        AjaxResponseData ajaxResponseData = new AjaxResponseData();
        ajaxResponseData.setState(STATE_SUCCESS);
        return ajaxResponseData;
    }

    public static AjaxResponseData success(Object data){
        AjaxResponseData ajaxResponseData = new AjaxResponseData();
        ajaxResponseData.setState(STATE_SUCCESS);
        ajaxResponseData.setData(data);
        return ajaxResponseData;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
