package com.saferich.core.template;

import java.util.Map;

public interface ITemplate {
    /**
     * 根据路径的模版生成
     * 
     * @param templatePath
     * @param props
     * @param encoding
     * @return
     */
    String generate(String templatePath, Map<String, Object> props, String encoding);

    /**
     * 根据源字符串生成
     * 
     * @param templateData
     * @param props
     * @param encoding
     * @return
     */
    String generateByData(String templateData, Map<String, Object> props, String encoding);
}
