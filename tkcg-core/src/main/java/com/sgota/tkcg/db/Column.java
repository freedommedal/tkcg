package com.sgota.tkcg.db;

/**
 * 表字段
 *
 * @author tiankuo
 */
public class Column {

    /**
     * 字段名
     */
    private String columnName;
    /**
     * 属性名
     */
    private String property;
    /**
     * 字段注释
     */
    private String comment;
    /**
     * 字段大小
     */
    private Integer size;
    /**
     * Java映射类型
     */
    private String javaType;
    /**
     * Jdbc映射类型
     */
    private String jdbcType;

    /**
     * Gets columnName
     *
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * Sets columnName
     *
     * @param columnName the columnName
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Gets property
     *
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * Sets property
     *
     * @param property the property
     */
    public void setProperty(String property) {
        this.property = property;
    }

    /**
     * Gets comment
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Gets size
     *
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Sets size
     *
     * @param size the size
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * Gets javaType
     *
     * @return the javaType
     */
    public String getJavaType() {
        return javaType;
    }

    /**
     * Sets javaType
     *
     * @param javaType the javaType
     */
    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    /**
     * Gets jdbcType
     *
     * @return the jdbcType
     */
    public String getJdbcType() {
        return jdbcType;
    }

    /**
     * Sets jdbcType
     *
     * @param jdbcType the jdbcType
     */
    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
