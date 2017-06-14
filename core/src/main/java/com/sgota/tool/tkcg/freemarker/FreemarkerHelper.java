package com.sgota.tool.tkcg.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.sgota.tool.tkcg.exception.GeneratorException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Freemarker模板引擎.
 *
 * @author tiankuo
 */
public class FreemarkerHelper {

    /**
     * The Configuration.
     */
    private Configuration configuration = null;

    /**
     * 构造方法.
     *
     * @param configuration configuration
     */
    public FreemarkerHelper(Configuration configuration){
        this.configuration = configuration;
    }

    /**
     * Process to file.
     *
     * @param model model
     * @param templateName templateName
     * @param targetFile targetFile
     * @param encoding encoding
     * @throws IOException io exception
     */
    public void processToFile(Map model, String templateName, File targetFile, String encoding) throws IOException {
        Template template = configuration.getTemplate(templateName);
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), encoding));
        try {
            template.process(model, out);
        } catch (TemplateException e) {
            throw new GeneratorException("处理文件模板出错:" + e.getMessage());
        }
        out.close();
    }

    /**
     * Process to string.
     *
     * @param model model
     * @param stringTemplate stringTemplate
     * @return string.
     * @throws IOException io exception
     */
    public String processToString(Map model, String stringTemplate) throws IOException {
        StringWriter out = new StringWriter();
        try {
            Template template = new Template("String_Template", new StringReader(stringTemplate), configuration);
            template.process(model, out);
        } catch (TemplateException e) {
            throw new GeneratorException("处理字符串模板出错:" + e.getMessage());
        }
        return out.toString();
    }
}
