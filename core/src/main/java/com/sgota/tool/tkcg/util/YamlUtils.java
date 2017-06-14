package com.sgota.tool.tkcg.util;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

/**
 * yaml工具类
 *
 * @author tiankuo
 */
public class YamlUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(YamlUtils.class);

    /**
     * The yaml.
     */
    private static Yaml yaml = new Yaml();

    private YamlUtils(){
    }

    /**
     * Load by file.
     *
     * @param <T> the type parameter
     * @param file file
     * @param type type
     * @return t.
     */
    public static <T> T loadByFile(File file, Class<T> type) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            LOGGER.error("", e);
        }
        return loadByStream(inputStream, type);
    }

    /**
     * Load by class path.
     *
     * @param <T> the type parameter
     * @param path path
     * @param type type
     * @return t.
     */
    public static <T> T loadByClassPath(String path, Class<T> type) {
        InputStream inputStream = YamlUtils.class.getResourceAsStream(path);
        return loadByStream(inputStream, type);
    }

    /**
     * Load by stream.
     *
     * @param <T> the type parameter
     * @param inputStream inputStream
     * @param type type
     * @return t.
     */
    public static <T> T loadByStream(InputStream inputStream, Class<T> type) {
        T load = null;
        try {
            load = yaml.loadAs(inputStream, type);
        } catch (Exception e) {
            LOGGER.error("加载yaml文件出错", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOGGER.error("", e);
                }
            }
        }
        return load;
    }
}
