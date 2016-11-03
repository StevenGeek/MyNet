package com.saferich.core.web;

public interface ResultEnum {

    /** 是否成功 **/
    public Boolean getSuccess();

    /** 获取错误码 **/
    public String getErrCode();

    /** 获取错信息 **/
    public String getMessage();

}
