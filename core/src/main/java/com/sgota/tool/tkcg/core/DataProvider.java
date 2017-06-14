package com.sgota.tool.tkcg.core;

import java.util.Map;

import com.sgota.tool.tkcg.core.GeneratorConfig;

/**
 * 数据提供者接口.
 *
 * @author tiankuo
 */
public interface DataProvider {

    /**
     * 返回提供者id.
     *
     * @return id值. id
     */
    String getId();

    /**
     * 加载数据.
     *
     * @param generatorConfig generatorConfig
     * @param providerConfig  providerConfig
     * @return 数据map.
     */
    Map<String, Map<String, Object>> loadData(GeneratorConfig generatorConfig, Map<String, Object> providerConfig);
}
