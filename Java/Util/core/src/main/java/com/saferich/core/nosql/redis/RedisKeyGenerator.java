package com.saferich.core.nosql.redis;

import com.saferich.core.nosql.KeyGenerator;

public class RedisKeyGenerator extends KeyGenerator {

    /**
     * 用户验证的key
     * 
     * @param code
     * @param type
     * @return
     */
    public static String genMemberTokenKey(String code) {
        return genKey(code, "MEMBER");
    }

    /**
     * 用户验证的key
     * 
     * @param code
     * @param type
     * @return
     */
    public static String genUserVerifyCodeKey(String code, String type) {
        return genKey(code, type);
    }

    /**
     * 获取手机验证码的key
     * 
     * @param mobilePhone
     * @return
     */
    public static String genMobileCaptchaKey(String mobilePhone) {
        return genKey(mobilePhone, "captcha");
    }

    /**
     * 获取手机验证码间隔时间key
     * 
     * @param mobilePhone
     * @return
     */
    public static String genMobileCaptchaIntervalKey(String mobilePhone) {
        return genKey(mobilePhone, "captchaInterval");
    }

}
