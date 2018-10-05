package com.sgota.tkcg.core;

import com.sgota.tkcg.GeneratorException;
import com.sgota.tkcg.messages.TkcgMessages;

/**
 * @author tiankuo
 */
public class ProjectConfig extends AbstractConfig {

    /**
     * appName
     */
    private String appName = "demo";
    /**
     * Maven groupId
     */
    private String groupId = "com.example";
    /**
     * Maven artifactId
     */
    private String artifactId = "demo";
    /**
     * Maven Version
     */
    private String version = "1.0.0-SNAPSHOT";
    /**
     * Java包名前缀
     */
    private String basePackage = "com.example.demo";
    /**
     * java目录路径.
     */
    private String baseDir = "com/example/demo";
    /**
     * 作者名字
     */
    private String author = "admin";

    /**
     * Gets appName
     *
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Sets appName
     *
     * @param appName the appName
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Gets groupId
     *
     * @return the groupId
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * Sets groupId
     *
     * @param groupId the groupId
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    /**
     * Gets artifactId
     *
     * @return the artifactId
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * Sets artifactId
     *
     * @param artifactId the artifactId
     */
    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    /**
     * Gets version
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version
     *
     * @param version the version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * Gets basePackage
     *
     * @return the basePackage
     */
    public String getBasePackage() {
        return basePackage;
    }

    /**
     * Sets basePackage
     *
     * @param basePackage the basePackage
     */
    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
        setBaseDir(basePackage.replaceAll("\\.", "/"));
    }

    /**
     * Gets baseDir
     *
     * @return the baseDir
     */
    public String getBaseDir() {
        return baseDir;
    }

    /**
     * Sets baseDir
     *
     * @param baseDir the baseDir
     */
    public void setBaseDir(String baseDir) {
        this.baseDir = baseDir;
    }

    /**
     * Gets author
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets author
     *
     * @param author the author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void checkArgs() {
        super.checkArgs();
        if (appName == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.appName"));
        }
        if (groupId == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.groupId"));
        }
        if (artifactId == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.artifactId"));
        }
        if (version == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.version"));
        }
        if (basePackage == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.basePackage"));
        }
        if (author == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.author"));
        }
    }
}
