package com.sgota.tkcg.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sgota.tkcg.GeneratorException;
import com.sgota.tkcg.TkcgLog;
import com.sgota.tkcg.messages.TkcgMessages;
import com.sgota.tkcg.util.StringUtils;

/**
 * 数据库助手
 *
 * @author tiankuo
 */
public class DbHelper {

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
     * The constant COLUMN_SIZE
     */
    public static final String COLUMN_SIZE = "COLUMN_SIZE";
    /**
     * The constant DATA_TYPE.
     */
    public static final String DATA_TYPE = "DATA_TYPE";

    public List<Table> getTables(String jdbcUrl, String username, String password, String tables) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new GeneratorException(e);
        }

        Connection connection = null;
        List<Table> tableModelList;

        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            throw new GeneratorException(TkcgMessages.message("db.connection", e.getMessage()));
        }

        try {
            if (tables != null && !tables.equals("")) {
                tableModelList = getTables(connection, tables.split(","));
            } else {
                tableModelList = getTables(connection, new String[] { "%" });
            }
        } catch (Exception e) {
            throw new GeneratorException(e);
        } finally {
            closeConnection(connection);
        }
        return tableModelList;
    }

    /**
     * Gets tables.
     *
     * @param connection the connection
     * @param tableNames tableNames
     * @throws SQLException the sql exception
     */
    private List<Table> getTables(Connection connection, String[] tableNames) throws SQLException {
        List<Table> tableModelList = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        for (String tableName : tableNames) {
            ResultSet resultSet = metaData.getTables(null, null, tableName, null);
            while (resultSet.next()) {
                Table tableModel = new Table();
                tableName = resultSet.getString(TABLE_NAME);
                TkcgLog.info(TkcgMessages.message("db.001", tableName));
                // 获取主键
                ResultSet primaryKeys = metaData.getPrimaryKeys(null, null, tableName);
                if (primaryKeys.next()) {
                    String primaryColumn = primaryKeys.getString(COLUMN_NAME);
                    tableModel.setPrimaryColumn(primaryColumn);
                    tableModel.setPrimaryName(StringUtils.getCamelCaseString(primaryColumn, false));
                    tableModel.setPrimaryAuto(false);
                } else {
                    TkcgLog.info(TkcgMessages.message("db.002", tableName));
                    continue;
                }
                tableModel.setTableName(tableName);
                String className = StringUtils.getCamelCaseString(tableName, true);
                tableModel.setClassName(className);
                List<Column> columns = getColumns(connection, tableModel);
                tableModel.setColumns(columns);
                tableModelList.add(tableModel);
            }
        }
        return tableModelList;
    }

    /**
     * Gets columns.
     *
     * @param connection the connection
     * @param tableModel the table
     * @return the columns
     * @throws SQLException the sql exception
     */
    private List<Column> getColumns(Connection connection, Table tableModel) throws SQLException {
        List<Column> columns = new ArrayList<>();
        DatabaseMetaData metaData = connection.getMetaData();
        String tableName = tableModel.getTableName();

        ResultSet resultSet = metaData.getColumns(null, null, tableName, null);
        while (resultSet.next()) {
            Column columnModel = new Column();
            String columnName = resultSet.getString(COLUMN_NAME);
            // 获取字段类型
            int dataType = resultSet.getInt(DATA_TYPE);
            SqlTypeEnum type = SqlTypeEnum.find(dataType);
            if (type != null) {
                columnModel.setJavaType(type.getClasszz().getSimpleName());
                columnModel.setJdbcType(type.name());
            } else {
                TkcgLog.info(TkcgMessages.message("db.003", columnName));
                continue;
            }
            String isAutoincrement = resultSet.getString("IS_AUTOINCREMENT");
            if ("YES".equals(isAutoincrement)) {
                tableModel.setPrimaryAuto(true);
            }
            columnModel.setColumnName(columnName);
            columnModel.setProperty(StringUtils.getCamelCaseString(columnName, false));
            columnModel.setSize(resultSet.getInt(COLUMN_SIZE));
            columnModel.setComment(resultSet.getString(REMARKS));

            columns.add(columnModel);
        }
        return columns;
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
}
