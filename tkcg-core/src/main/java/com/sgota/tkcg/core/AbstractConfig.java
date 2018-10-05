package com.sgota.tkcg.core;

import com.sgota.tkcg.GeneratorException;
import com.sgota.tkcg.messages.TkcgMessages;

/**
 * 抽象生成器参数
 *
 * @author tiankuo
 */
public abstract class AbstractConfig {

    /**
     * 模板目录
     */
    private String templateDir;
    /**
     * 输出目录
     */
    private String outDir;

    /**
     * 检查参数
     */
    public void checkArgs() {
        if (templateDir == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.templateDir"));
        }
        if (outDir == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.outDir"));
        }
    }

    /**
     * Gets templateDir
     *
     * @return the templateDir
     */
    public String getTemplateDir() {
        return templateDir;
    }

    /**
     * Sets templateDir
     *
     * @param templateDir the templateDir
     */
    public void setTemplateDir(String templateDir) {
        this.templateDir = templateDir;
    }

    /**
     * Gets outDir
     *
     * @return the outDir
     */
    public String getOutDir() {
        return outDir;
    }

    /**
     * Sets outDir
     *
     * @param outDir the outDir
     */
    public void setOutDir(String outDir) {
        this.outDir = outDir;
    }
}
