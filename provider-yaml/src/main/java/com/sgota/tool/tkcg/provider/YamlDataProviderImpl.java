package com.sgota.tool.tkcg.provider;

import java.io.File;
import java.util.Map;

import com.sgota.tool.tkcg.core.DataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgota.tool.tkcg.core.GeneratorConfig;
import com.sgota.tool.tkcg.exception.GeneratorException;
import com.sgota.tool.tkcg.util.YamlUtils;

/**
 * 数据提供者接口实现类-yaml文件数据
 *
 * @author tiankuo
 */
public class YamlDataProviderImpl implements DataProvider {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(YamlDataProviderImpl.class);

    @Override
    public String getId() {
        return "yaml";
    }

    @Override
    public Map<String, Map<String, Object>> loadData(GeneratorConfig generatorConfig,
                                                     Map<String, Object> providerConfig) {
        String dataPath = (String) providerConfig.get("dataPath");
        File file = new File(dataPath);
        if (!file.isAbsolute()) {
            file = new File(generatorConfig.getConfigPathRoot(), dataPath);
        }
        if (!file.exists()) {
            throw new GeneratorException("dataPath路径不存在  " + file.getAbsolutePath());
        }
        @SuppressWarnings("unchecked")
        Map<String, Map<String, Object>> dataMap = YamlUtils.loadByFile(file, Map.class);
        return dataMap;
    }
}
