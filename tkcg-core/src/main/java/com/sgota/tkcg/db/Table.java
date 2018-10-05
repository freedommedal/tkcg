package com.sgota.tkcg.db;

import java.util.List;

/**
 * 数据库表
 *
 * @author tiankuo
 */
public class Table {

    /**
     * 表名
     */
    private String tableName;
    /**
     * 表名映射类名
     */
    private String className;
    /**
     * 主键字段名
     */
    private String primaryColumn;
    /**
     * 主键属性名
     */
    private String primaryName;
    /**
     * 主键是否自增
     */
    private Boolean primaryAuto;
    /**
     * 字段集合
     */
    private List<Column> columns;

    /**
     * Gets tableName
     *
     * @return the tableName
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Sets tableName
     *
     * @param tableName the tableName
     */
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    /**
     * Gets className
     *
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * Sets className
     *
     * @param className the className
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * Gets primaryColumn
     *
     * @return the primaryColumn
     */
    public String getPrimaryColumn() {
        return primaryColumn;
    }

    /**
     * Sets primaryColumn
     *
     * @param primaryColumn the primaryColumn
     */
    public void setPrimaryColumn(String primaryColumn) {
        this.primaryColumn = primaryColumn;
    }

    /**
     * Gets primaryName
     *
     * @return the primaryName
     */
    public String getPrimaryName() {
        return primaryName;
    }

    /**
     * Sets primaryName
     *
     * @param primaryName the primaryName
     */
    public void setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
    }

    /**
     * Gets primaryAuto
     *
     * @return the primaryAuto
     */
    public Boolean getPrimaryAuto() {
        return primaryAuto;
    }

    /**
     * Sets primaryAuto
     *
     * @param primaryAuto the primaryAuto
     */
    public void setPrimaryAuto(Boolean primaryAuto) {
        this.primaryAuto = primaryAuto;
    }

    /**
     * Gets columns
     *
     * @return the columns
     */
    public List<Column> getColumns() {
        return columns;
    }

    /**
     * Sets columns
     *
     * @param columns the columns
     */
    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
