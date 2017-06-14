package com.sgota.tool.tkcg.core;

import java.io.File;
import java.util.Map;

import com.sgota.tool.tkcg.exception.GeneratorException;
import com.sgota.tool.tkcg.util.YamlUtils;

/**
 * 生成器配置
 *
 * @author tiankuo
 */
public class GeneratorConfig {

    /**
     * 全局配置文件所在目录.
     */
    private String configPathRoot;
    /**
     * 模板目录.
     */
    private String templateDirectory;
    /**
     * 输出目录.
     */
    private String outDirectory;
    /**
     * 数据提供者配置.
     */
    private Map<String, Object> providerConfig;
    /**
     * 全局数据.
     */
    private Map<String, Object> global;
    /**
     * 要生成的数据分组key列表.
     */
    private String keys;
    /**
     * 是否分组
     */
    private boolean group = true;

    /**
     * 加载全局配置文件
     *
     * @param configPath configPath
     * @return generator config
     */
    public static GeneratorConfig loadConfig(String configPath) {
        File configPathFile = new File(configPath);
        if (!configPathFile.exists()) {
            throw new GeneratorException("配置文件路径不存在{0}", configPathFile);
        }
        GeneratorConfig config = YamlUtils.loadByFile(configPathFile, GeneratorConfig.class);
        config.setConfigPathRoot(configPathFile.getAbsoluteFile().getParent() + File.separator);
        return config;
    }

    /**
     * Gets configPathRoot
     *
     * @return the configPathRoot
     */
    public String getConfigPathRoot() {
        return configPathRoot;
    }

    /**
     * Sets configPathRoot
     *
     * @param configPathRoot the configPathRoot
     */
    public void setConfigPathRoot(String configPathRoot) {
        this.configPathRoot = configPathRoot;
    }

    /**
     * Gets templateDirectory
     *
     * @return the templateDirectory
     */
    public String getTemplateDirectory() {
        return templateDirectory;
    }

    /**
     * Sets templateDirectory
     *
     * @param templateDirectory the templateDirectory
     */
    public void setTemplateDirectory(String templateDirectory) {
        this.templateDirectory = templateDirectory;
    }

    /**
     * Gets outDirectory
     *
     * @return the outDirectory
     */
    public String getOutDirectory() {
        return outDirectory;
    }

    /**
     * Sets outDirectory
     *
     * @param outDirectory the outDirectory
     */
    public void setOutDirectory(String outDirectory) {
        this.outDirectory = outDirectory;
    }

    /**
     * Gets providerConfig
     *
     * @return the providerConfig
     */
    public Map<String, Object> getProviderConfig() {
        return providerConfig;
    }

    /**
     * Sets providerConfig
     *
     * @param providerConfig the providerConfig
     */
    public void setProviderConfig(Map<String, Object> providerConfig) {
        this.providerConfig = providerConfig;
    }

    /**
     * Gets global
     *
     * @return the global
     */
    public Map<String, Object> getGlobal() {
        return global;
    }

    /**
     * Sets global
     *
     * @param global the global
     */
    public void setGlobal(Map<String, Object> global) {
        this.global = global;
    }

    /**
     * Gets keys
     *
     * @return the keys
     */
    public String getKeys() {
        return keys;
    }

    /**
     * Sets keys
     *
     * @param keys the keys
     */
    public void setKeys(String keys) {
        this.keys = keys;
    }

    /**
     * Gets group
     *
     * @return the group
     */
    public boolean isGroup() {
        return group;
    }

    /**
     * Sets group
     *
     * @param group the group
     */
    public void setGroup(boolean group) {
        this.group = group;
    }
}
