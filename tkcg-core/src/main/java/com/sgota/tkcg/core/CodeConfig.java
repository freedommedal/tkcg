package com.sgota.tkcg.core;

import com.sgota.tkcg.GeneratorException;
import com.sgota.tkcg.messages.TkcgMessages;

/**
 * @author tiankuo
 */
public class CodeConfig extends AbstractConfig {

    /**
     * Maven artifactId.
     */
    private String artifactId = "demo";
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
     * host
     */
    private String host = "localhost";
    /**
     * port
     */
    private String portStr = "3306";
    /**
     * port
     */
    private int port = 3306;
    /**
     * database
     */
    private String database = "test";
    /**
     * The Username
     */
    private String username = "app";
    /**
     * The Password
     */
    private String password = "123456";
    /**
     * The Tables
     */
    private String tables;

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

    /**
     * Gets host
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets host
     *
     * @param host the host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Gets port
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets port
     *
     * @param port the port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Gets database
     *
     * @return the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * Sets database
     *
     * @param database the database
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * Gets username
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets tables
     *
     * @return the tables
     */
    public String getTables() {
        return tables;
    }

    /**
     * Sets tables
     *
     * @param tables the tables
     */
    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getJdbcUrl() {
        return "jdbc:mysql://" + host + ":" + port + "/" + database + "?useUnicode=true&characterEncoding=UTF-8";
    }

    @Override
    public void checkArgs() {
        super.checkArgs();
        if (artifactId == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.artifactId"));
        }
        if (basePackage == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.basePackage"));
        }
        if (author == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.author"));
        }
        if (host == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.appName"));
        }
        if (database == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.database"));
        }
        if (username == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.username"));
        }
        if (password == null) {
            throw new GeneratorException(TkcgMessages.message("args.notNull.password"));
        }
    }

    /**
     * Gets portStr
     *
     * @return the portStr
     */
    public String getPortStr() {
        return portStr;
    }

    /**
     * Sets portStr
     *
     * @param portStr the portStr
     */
    public void setPortStr(String portStr) {
        this.portStr = portStr;
        port = Integer.parseInt(portStr);
    }
}
