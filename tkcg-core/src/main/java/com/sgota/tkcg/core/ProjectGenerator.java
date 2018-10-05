package com.sgota.tkcg.core;

import java.io.File;
import java.util.List;

import com.sgota.tkcg.helper.FileHelper;
import com.sgota.tkcg.helper.TemplateHelper;

/**
 * 项目结构生成器
 * 
 * @author tiankuo
 */
public class ProjectGenerator {

    public void execute(ProjectConfig config) {
        config.checkArgs();
        File outDir = new File(config.getOutDir());
        File templateDir = new File(config.getTemplateDir());

        TemplateHelper templateHelper = TemplateHelper.buildTemplateHelper(templateDir);

        // 处理模板
        List<String> templates = FileHelper.getTemplates(templateDir);
        for (String template : templates) {
            templateHelper.renderFile(config, template, outDir, "UTF-8");
        }

        // 处理文件
        List<String> binarys = FileHelper.getBinarys(templateDir);
        for (String binary : binarys) {
            FileHelper.copyFile(templateDir, binary, outDir, templateHelper.renderString(config, binary));
        }
    }
}
