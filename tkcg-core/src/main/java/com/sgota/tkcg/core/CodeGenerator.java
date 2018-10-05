package com.sgota.tkcg.core;

import java.io.File;
import java.util.List;

import com.sgota.tkcg.db.DbHelper;
import com.sgota.tkcg.db.Table;
import com.sgota.tkcg.helper.FileHelper;
import com.sgota.tkcg.helper.TemplateHelper;

/**
 * 业务代码生成器
 *
 * @author tiankuo
 */
public class CodeGenerator {

    public void execute(CodeConfig config) {
        config.checkArgs();
        File outDir = new File(config.getOutDir());
        File templateDir = new File(config.getTemplateDir());
        TemplateHelper templateHelper = TemplateHelper.buildTemplateHelper(templateDir);
        DbHelper dbHelper = new DbHelper();
        List<Table> tables = dbHelper.getTables(config.getJdbcUrl(), config.getUsername(), config.getPassword(),
                                                config.getTables());
        // 处理每个表
        for (Table table : tables) {
            CodeModel model = new CodeModel();
            model.setCodeReq(config);
            model.setTableModel(table);
            // 处理模板
            List<String> templates = FileHelper.getTemplates(templateDir);
            for (String template : templates) {
                templateHelper.renderFile(model, template, outDir, "UTF-8");
            }
        }
    }
}
