package com.sgota.tool.tkcg.provider;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;

import com.sgota.tool.tkcg.util.StringUtils;

/**
 * 数据助手
 *
 * @author tiankuo
 */
public class DbDataMapHelper {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DbDataMapHelper.class);

    /**
     * The constant TABLE_NAME.
     */
    public static final String TABLE_NAME = "TABLE_NAME";
    /**
     * The constant REMARKS.
     */
    public static final String REMARKS = "REMARKS";
    /**
     * The constant COLUMN_NAME.
     */
    public static final String COLUMN_NAME = "COLUMN_NAME";
    /**
     * The constant COLUMN_SIZE.
     */
    public static final String COLUMN_SIZE = "COLUMN_SIZE";
    /**
     * The constant DATA_TYPE.
     */
    public static final String DATA_TYPE = "DATA_TYPE";

    /**
     * Gets tables.
     *
     * @param connection the connection
     * @param keys keys
     * @param tablesOverride the tables override @return the tables
     * @throws SQLException the sql exception
     */
    public Map<String, Map<String, Object>> getTables(Connection connection, String keys,
                                                      Map<String, Map<String, Object>> tablesOverride) throws SQLException {
        Map<String, Map<String, Object>> dataMap = new HashMap<>(16);
        DatabaseMetaData metaData = connection.getMetaData();
        // 处理key列表
        List<String> keyList = new ArrayList<>(1);
        if (keys != null) {
            String[] keyArray = keys.split(",");
            keyList = Arrays.asList(keyArray);
        } else {
            keyList.add("%");
        }
        // 根据key列表抽取指定表
        for (String key : keyList) {
            ResultSet resultSet = metaData.getTables(null, null, key, null);
            while (resultSet.next()) {
                Map<String, Object> table = new HashMap<>(4);
                String tableName = resultSet.getString(TABLE_NAME);
                LOGGER.info("抽取表结构>>{}", tableName);
                // 获取主键
                ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
                if (primaryKeys.next()) {
                    String primaryColumn = primaryKeys.getString(COLUMN_NAME);
                    table.put("primaryColumn", primaryColumn);
                    table.put("primaryName", StringUtils.getCamelCaseString(primaryColumn, false));
                    table.put("primaryAuto", false);
                } else {
                    LOGGER.warn("获取表{}主键失败,跳过该表", tableName);
                    continue;
                }
                table.put("tableName", tableName);
                table.put("comment", resultSet.getString(REMARKS));
                String className = StringUtils.getCamelCaseString(tableName, true);
                table.put("className", className);

                table.put("imports", new HashSet<>(5));

                // 使用配置文件进行覆盖
                Map<String, Object> tableOverride = tablesOverride.get(tableName);
                Map<String, Map<String, Object>> columnsOverride = new HashMap<>(0);
                if (tableOverride != null) {
                    table.putAll(tableOverride);
                    table.remove("columns");
                    List<Map<String, Object>> columnsOverrideList = (List<Map<String, Object>>) tableOverride.get("columns");
                    if (columnsOverrideList != null) {
                        columnsOverride = columnsOverrideList.stream().collect(Collectors.toMap(o -> (String) o.get("columnName"),
                                                                                                o -> o));
                    }
                }
                List<Map<String, Object>> columns = getColumns(connection, table, columnsOverride);
                table.put("columns", columns);
                dataMap.put(tableName, table);
            }
        }
        return dataMap;
    }

    /**
     * Gets columns.
     *
     * @param connection the connection
     * @param table the table
     * @param columnsOverride the columns override
     * @return the columns
     * @throws SQLException the sql exception
     */
    public List<Map<String, Object>> getColumns(Connection connection, Map<String, Object> table,
                                                Map<String, Map<String, Object>> columnsOverride) throws SQLException {
        List<Map<String, Object>> columns = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        String tableName = (String) table.get("tableName");

        ResultSet resultSet = metaData.getColumns(null, null, tableName, null);
        while (resultSet.next()) {
            Map<String, Object> column = new HashMap<>(6);
            String columnName = resultSet.getString(COLUMN_NAME);

            // 获取字段类型
            int dataType = resultSet.getInt(DATA_TYPE);
            SqlTypeEnum type = SqlTypeEnum.find(dataType);
            if (type != null) {
                // 处理非lang包下的类型
                String typeName = type.getClasszz().getTypeName();
                if (!typeName.startsWith("java.lang")) {
                    Set<String> imports = (Set<String>) table.get("imports");
                    imports.add(typeName);
                }
                column.put("javaType", type.getClasszz().getSimpleName());
                column.put("jdbcType", type.name());
            } else {
                LOGGER.warn("获取字段{}类型失败,跳过该字段", columnName);
                continue;
            }

            String isAutoincrement = resultSet.getString("IS_AUTOINCREMENT");
            if ("YES".equals(isAutoincrement)) {
                table.put("primaryAuto", true);
            }
            column.put("columnName", columnName);
            column.put("property", StringUtils.getCamelCaseString(columnName, false));
            column.put("comment", resultSet.getString(REMARKS));
            column.put("size", resultSet.getInt(COLUMN_SIZE));

            // 使用配置文件进行覆盖
            Map<String, Object> columnOverride = columnsOverride.get(columnName);
            if (columnOverride != null) {
                column.putAll(columnOverride);
            }
            columns.add(column);
        }
        return columns;
    }

    /**
     * Open connection.
     *
     * @param dataSource dataSource
     * @return connection.
     * @throws SQLException sql exception
     */
    public static Connection openConnection(DataSource dataSource) throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Close connection.
     *
     * @param connection connection
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // ignore
            }
        }
    }

    /**
     * Close druid data source.
     *
     * @param dataSource dataSource
     */
    public static void closeDruidDataSource(DruidDataSource dataSource) {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
