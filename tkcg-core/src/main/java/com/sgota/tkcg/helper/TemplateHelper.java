package com.sgota.tkcg.helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.commons.io.FileUtils;

import com.sgota.tkcg.GeneratorException;
import com.sgota.tkcg.messages.TkcgMessages;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

/**
 * 模板助手
 *
 * @author tiankuo
 */
public class TemplateHelper {

    /**
     * The Configuration.
     */
    private Configuration configuration;

    /**
     * 构造方法.
     */
    private TemplateHelper(){
        configuration = new Configuration(new Version(2, 3, 0));
        configuration.setNumberFormat("###############");
        configuration.setBooleanFormat("true,false");
        configuration.setDefaultEncoding("UTF-8");
    }

    /**
     * Build template helper
     *
     * @param templateDirectory templateDirectory
     * @return template helper
     */
    public static TemplateHelper buildTemplateHelper(File templateDirectory) {
        TemplateHelper freemarkerHelper = new TemplateHelper();
        try {
            TemplateLoader templateLoader = new FileTemplateLoader(templateDirectory);
            freemarkerHelper.configuration.setTemplateLoader(templateLoader);
        } catch (IOException e) {
            throw new GeneratorException(e.getMessage());
        }
        return freemarkerHelper;
    }

    /**
     * Build template helper
     *
     * @param resourceLoaderClass resourceLoaderClass
     * @param basePackagePath basePackagePath
     * @return template helper
     */
    public static TemplateHelper buildTemplateHelper(Class<?> resourceLoaderClass, String basePackagePath) {
        TemplateHelper freemarkerHelper = new TemplateHelper();
        TemplateLoader templateLoader = new ClassTemplateLoader(resourceLoaderClass, basePackagePath);
        freemarkerHelper.configuration.setTemplateLoader(templateLoader);
        return freemarkerHelper;
    }

    /**
     * 渲染文件模板
     *
     * @param model model
     * @param templateName templateName
     * @param outDir outDir
     * @param encoding encoding
     */
    public void renderFile(Object model, String templateName, File outDir, String encoding) {
        File targetFile = new File(outDir, renderString(model, templateName.replace(".ftl", "")));
        try {
            FileUtils.touch(targetFile);
        } catch (IOException e) {
            throw new GeneratorException(TkcgMessages.message("file.touch", targetFile.toString()));
        }
        try (Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), encoding))) {
            Template template = configuration.getTemplate(templateName);
            template.process(model, out);
        } catch (TemplateException | MalformedTemplateNameException | ParseException | TemplateNotFoundException e) {
            throw new GeneratorException(TkcgMessages.message("template.parse", templateName));
        } catch (IOException e) {
            throw new GeneratorException(TkcgMessages.message("template.process", e.getMessage()));
        }
    }

    /**
     * 渲染字符串模板
     *
     * @param model model
     * @param templateContent stringTemplate
     * @return string
     */
    public String renderString(Object model, String templateContent) {
        StringWriter out = new StringWriter();
        try {
            Template template = new Template("String_Template", new StringReader(templateContent), configuration);
            template.process(model, out);
        } catch (TemplateException e) {
            throw new GeneratorException(TkcgMessages.message("template.parse", e.getMessage()));
        } catch (IOException e) {
            throw new GeneratorException(TkcgMessages.message("template.process", e.getMessage()));
        }
        return out.toString();
    }
}
