/*
 * Copyright (C), 2013-2016, 嘉丰瑞德
 * FileName: BaseController.java
 * Author:   Administrator
 * Date:     2016年2月25日 上午11:45:50
 * Description: //模块目的、功能描述
 */
package com.saferich.core.web;

import javax.servlet.http.HttpServletRequest;

import com.saferich.core.exception.BaseException;
import com.saferich.core.exception.ParameterException;
import com.saferich.core.util.StringUtils;
import com.saferich.core.validate.ParameterValidate;
import com.saferich.core.validate.ParameterValidate.ValidateResult;

/**
 * @Date: 2016年2月25日 上午11:45:50
 * @author Jason
 */
public class BaseController {

    public static final String ACCESS_TOKEN_HEADER = "accessToken";

    protected void validate(ParameterValidate parameter) throws ParameterException {
        if (parameter != null) {
            ValidateResult result = parameter.validate();
            if (!result.isSuccess()) {
                throw new ParameterException(result.getMessage());
            }
        }
    }

    /**
     * 获取当前登陆用户Id
     *
     * @param request
     * @return
     * @throws BaseException
     */
    public String getUserId(HttpServletRequest request) throws BaseException {
        if (request.getAttribute("userId") != null) {
            String userId = request.getAttribute("userId").toString();
            if (StringUtils.isNotEmpty(userId)) {
                return userId;
            }
        }
        throw new BaseException(BaseException.ILLEGAL_OPERATION);
    }

    public String getAccessToken(HttpServletRequest request) throws BaseException {
        if (request.getAttribute(ACCESS_TOKEN_HEADER) != null) {
            String accessToken = request.getAttribute(ACCESS_TOKEN_HEADER).toString();
            if (StringUtils.isNotEmpty(ACCESS_TOKEN_HEADER)) {
                return accessToken;
            }
        }
        throw new BaseException(BaseException.ILLEGAL_OPERATION);
    }
}
