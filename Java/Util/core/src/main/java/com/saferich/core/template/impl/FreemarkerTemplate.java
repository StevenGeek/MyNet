package com.saferich.core.template.impl;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saferich.core.Constants;
import com.saferich.core.template.ITemplate;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * @author Jason
 */
public class FreemarkerTemplate implements ITemplate {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Configuration cfgFtl = new Configuration();
    private Configuration cfgString = new Configuration();

    public FreemarkerTemplate(String pathPrefix) {
        cfgFtl.setClassForTemplateLoading(FreemarkerTemplate.class, pathPrefix);
        cfgFtl.setObjectWrapper(new DefaultObjectWrapper());
        cfgFtl.setDefaultEncoding(Constants.UTF8);
        cfgFtl.setTemplateExceptionHandler(TemplateExceptionHandler.DEBUG_HANDLER);
        cfgFtl.setIncompatibleImprovements(new Version(2, 3, 20));
    }

    @Override
    public String generate(String templatePath, Map<String, Object> props, String encoding) {
        try {
            StringWriter stringWriter = new StringWriter();
            Template template = cfgFtl.getTemplate("/" + templatePath);
            template.process(props, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    @Override
    public String generateByData(String templateData, Map<String, Object> props, String encoding) {
        try {
            StringTemplateLoader stringLoader = new StringTemplateLoader();
            stringLoader.putTemplate("myTemplate", templateData);
            cfgString.setTemplateLoader(stringLoader);
            Template template = cfgString.getTemplate("myTemplate", Constants.UTF8);

            StringWriter stringWriter = new StringWriter();
            template.process(props, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return "";
    }

    public static void main(String[] args) {
        FreemarkerTemplate template = new FreemarkerTemplate("");

        Map<String, Object> props = new HashMap<String, Object>();
        props.put("code", "123");
        String str = template.generate("您的验证码是:${code}", props, Constants.UTF8);
        System.out.println(str);
    }
}
