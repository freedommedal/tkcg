package com.sgota.tool.tkcg.provider;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.sgota.tool.tkcg.core.DataProvider;
import com.sgota.tool.tkcg.exception.GeneratorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSource;

import com.sgota.tool.tkcg.core.GeneratorConfig;

/**
 * 数据提供者接口实现类-jdbc数据库数据
 *
 * @author tiankuo
 */
public class JdbcDataProviderImpl implements DataProvider {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcDataProviderImpl.class);
    /**
     * The Data map helper
     */
    private DbDataMapHelper dataMapHelper = new DbDataMapHelper();

    @Override
    public String getId() {
        return "jdbc";
    }

    @Override
    public Map<String, Map<String, Object>> loadData(GeneratorConfig generatorConfig,
                                                     Map<String, Object> providerConfig) {
        Map<String, Map<String, Object>> dataMap;
        Map<String, Map<String, Object>> tablesOverride = (Map<String, Map<String, Object>>) providerConfig.get("tables");
        if(tablesOverride == null){
            tablesOverride = new HashMap<>(0);
        }
        DruidDataSource dataSource = null;
        Connection connection = null;
        try {
            dataSource = new DruidDataSource();
            dataSource.setMaxActive(1);
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl((String) providerConfig.get("url"));
            dataSource.setUsername((String) providerConfig.get("username"));
            dataSource.setPassword(providerConfig.get("password").toString());
            connection = DbDataMapHelper.openConnection(dataSource);
            String keys = generatorConfig.getKeys();
            dataMap = dataMapHelper.getTables(connection, keys, tablesOverride);
        } catch (SQLException e) {
            LOGGER.error("", e);
            throw new GeneratorException(e);
        } finally {
            DbDataMapHelper.closeConnection(connection);
            DbDataMapHelper.closeDruidDataSource(dataSource);
        }
        if(dataMap == null){
            dataMap = new HashMap<>(0);
        }
        return dataMap;
    }
}
