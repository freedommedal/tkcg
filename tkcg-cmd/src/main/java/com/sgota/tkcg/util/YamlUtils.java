package com.sgota.tkcg.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

import com.sgota.tkcg.GeneratorException;
import com.sgota.tkcg.TkcgCmdMessages;

/**
 * yaml工具类
 *
 * @author tiankuo
 */
public class YamlUtils {

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
        try (InputStream inputStream = new FileInputStream(file)) {
            return yaml.loadAs(inputStream, type);
        } catch (Exception e) {
            throw new GeneratorException(TkcgCmdMessages.message("config.load_error", file.getAbsolutePath(), e.getMessage()));
        }
    }
}
