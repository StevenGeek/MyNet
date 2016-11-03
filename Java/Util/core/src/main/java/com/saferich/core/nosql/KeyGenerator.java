package com.saferich.core.nosql;

import org.apache.commons.lang3.StringUtils;

public abstract class KeyGenerator {

    public static final String CACHE_KEY_SEPARATOR = ".";

    protected static String genKey(long primaryKey, String... funcs) {
        return genKey(String.valueOf(primaryKey), funcs);
    }

    protected static String genKey(String primaryKey, String... funcs) {
        StringBuilder sb = new StringBuilder();
        sb.append(primaryKey);
        for (String func : funcs) {
            if (StringUtils.isNotEmpty(func)) {
                sb.append(CACHE_KEY_SEPARATOR).append(func);
            }
        }
        return sb.toString();
    }
}
