package com.sgota.tool.tkcg.provider;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.sgota.tool.tkcg.core.DataProvider;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgota.tool.tkcg.core.GeneratorConfig;
import com.sgota.tool.tkcg.exception.GeneratorException;

/**
 * 数据提供者接口实现类-java文件数据
 *
 * @author tiankuo
 */
public class JavaDataProviderImpl implements DataProvider {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaDataProviderImpl.class);
    /**
     * The Data map helper
     */
    private JavaDataMapHelper dataMapHelper = new JavaDataMapHelper();

    @Override
    public String getId() {
        return "java";
    }

    @Override
    public Map<String, Map<String, Object>> loadData(GeneratorConfig generatorConfig,
                                                     Map<String, Object> providerConfig) {
        Map<String, Map<String, Object>> dataMap = new HashMap<>(0);
        String javaPath = (String) providerConfig.get("javaPath");
        File file = new File(javaPath);
        if (!file.isAbsolute()) {
            file = new File(generatorConfig.getConfigPathRoot(), javaPath);
        }
        if (!file.exists()) {
            throw new GeneratorException("javaPath路径不存在  " + file.getAbsolutePath());
        }
        Collection<File> javaFiles = FileUtils.listFiles(file, new String[] { "java" }, true);
        try {
            dataMap = dataMapHelper.loadJava(javaFiles);
        } catch (Exception e) {
            LOGGER.error("", e);
            throw new GeneratorException(e);
        }
        return dataMap;
    }
}
