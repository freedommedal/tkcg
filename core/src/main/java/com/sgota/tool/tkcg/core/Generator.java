package com.sgota.tool.tkcg.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgota.tool.tkcg.exception.GeneratorException;
import com.sgota.tool.tkcg.freemarker.FreemarkerHelper;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Version;

/**
 * 生成器
 *
 * @author tiankuo
 */
public class Generator {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Generator.class);
    /**
     * Freemarker模板文件扩展名.
     */
    public static final String FREEMARKER_TEMPLATE_SUFFIX = ".ftl";
    /**
     * Freemarker自动导入文件名.
     */
    public static final String FREEMARKER_AUTO_INCLUDE_SUFFIX = "auto.include";
    /**
     * Freemarker导入文件扩展名.
     */
    public static final String FREEMARKER_INCLUDE_SUFFIX = ".include";
    /**
     * 模板目录file.
     */
    private File templateDirectoryFile;
    /**
     * 输出目录file.
     */
    private File outDirectoryFile;
    /**
     * The Template list
     */
    private List<File> templateList = new ArrayList<>();
    /**
     * The Binary list
     */
    private List<File> binaryList = new ArrayList<>();
    /**
     * The Directory list
     */
    private List<File> directoryList = new ArrayList<>();
    /**
     * 自动导入的模板文件.
     */
    private File autoIncludeFile;

    /**
     * 初始化
     * 
     * @param config the config
     */
    public void init(GeneratorConfig config) {
        String templateDirectory = config.getTemplateDirectory();
        templateDirectoryFile = new File(templateDirectory);
        if (!templateDirectoryFile.isAbsolute()) {
            templateDirectoryFile = new File(config.getConfigPathRoot(), templateDirectory);
        }
        LOGGER.info("加载模板目录>>{}", templateDirectoryFile.getAbsoluteFile());
        String outPath = config.getOutDirectory();
        if (outPath == null) {
            outPath = "dest";
        }
        outDirectoryFile = new File(outPath);
        if (!outDirectoryFile.isAbsolute()) {
            outDirectoryFile = new File(config.getConfigPathRoot(), outPath).getAbsoluteFile();
        }
        // 处理文件列表
        Collection<File> fileList = getTemplateDirectoryFiles(templateDirectoryFile);
        fileList.remove(templateDirectoryFile);
        fileList.forEach(file -> {
            if (file.isDirectory()) {
                directoryList.add(file);
            } else if (file.getName().endsWith(FREEMARKER_TEMPLATE_SUFFIX)) {
                templateList.add(file);
            } else if (!file.getName().endsWith(FREEMARKER_AUTO_INCLUDE_SUFFIX)
                       || !file.getName().endsWith(FREEMARKER_INCLUDE_SUFFIX)) {
                // 跳过导入文件
                binaryList.add(file);
            }
        });
        autoIncludeFile = new File(templateDirectoryFile, FREEMARKER_AUTO_INCLUDE_SUFFIX);
        // 清理目标
        deleteDirectory();
    }

    /**
     * 清理目标目录
     */
    public void deleteDirectory() {
        try {
            LOGGER.info("清理目标目录>>{}", outDirectoryFile);
            FileUtils.deleteDirectory(outDirectoryFile);
        } catch (IOException e) {
            LOGGER.error("", e);
            throw new GeneratorException("删除输出目录出错:" + e.getMessage());
        }
    }

    /**
     * 执行.
     *
     * @param dataMap dataMap
     * @param global global
     * @param keys keys
     */
    public void executeGroup(Map<String, Map<String, Object>> dataMap, Map<String, Object> global, String keys) {
        LOGGER.info("执行生成开始");
        long currentTimeMillis = System.currentTimeMillis();
        List<String> keyList;
        // 处理key列表
        if (keys == null || "".equals(keys.trim())) {
            Set<String> keySet = dataMap.keySet();
            keyList = new ArrayList<>(keySet);
        } else {
            String[] keyArray = keys.split(",");
            keyList = Arrays.asList(keyArray);
        }
        try {
            // 循环生成每组数据模板
            boolean first = true;
            for (String key : keyList) {
                Map<String, Object> value = dataMap.get(key);
                if (value == null) {
                    LOGGER.warn("无法找到{}数据,跳过该key", key);
                    continue;
                }
                LOGGER.info("生成数据模板>>{}", key);
                value.put("dataKey", key);
                // 模型数据组装
                Map<String, Object> model = new HashMap<>(16);
                if (global != null) {
                    model.putAll(global);
                }
                model.putAll(value);
                FreemarkerHelper templateHelper = buildFreemarkerHelper(templateDirectoryFile.getAbsoluteFile());
                if (first) {
                    // 创建目录
                    processDirectory(templateHelper, model, directoryList, templateDirectoryFile);
                    // 复制二进制文件
                    processBinary(templateHelper, model, binaryList, templateDirectoryFile);
                    first = false;
                }
                // 转换模板
                processTemplate(templateHelper, model, templateList, templateDirectoryFile);
            }
        } catch (IOException e) {
            LOGGER.error("", e);
            throw new GeneratorException("生成模板或二进制文件出错:" + e.getMessage());
        }
        currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
        String totalTime = String.format("%,d", currentTimeMillis);
        LOGGER.info("执行生成结束,耗时{}毫秒", totalTime);
    }

    /**
     * 执行.
     *
     * @param data data
     * @param global global
     */
    public void execute(Map<String, Object> data, Map<String, Object> global) {
        LOGGER.info("执行生成开始");
        long currentTimeMillis = System.currentTimeMillis();
        try {
            // 模型数据组装
            Map<String, Object> model = new HashMap<>(16);
            if (global != null) {
                model.putAll(global);
            }
            model.putAll(data);
            FreemarkerHelper templateHelper = buildFreemarkerHelper(templateDirectoryFile.getAbsoluteFile());
            // 创建目录
            processDirectory(templateHelper, model, directoryList, templateDirectoryFile);
            // 复制二进制文件
            processBinary(templateHelper, model, binaryList, templateDirectoryFile);
            // 转换模板
            processTemplate(templateHelper, model, templateList, templateDirectoryFile);
        } catch (IOException e) {
            LOGGER.error("", e);
            throw new GeneratorException("生成模板或二进制文件出错:" + e.getMessage());
        }
        currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
        String totalTime = String.format("%,d", currentTimeMillis);
        LOGGER.info("执行生成结束,耗时{}毫秒", totalTime);
    }

    /**
     * 处理模板生成.
     *
     * @param model model
     * @param files files
     * @param templateDirectory templateDirectoryFile
     * @throws IOException io exception
     */
    private void processTemplate(FreemarkerHelper templateHelper, Map<String, Object> model, List<File> files,
                                 File templateDirectory) throws IOException {
        for (File file : files) {
            String fileName = file.getName();
            String targetName = templateHelper.processToString(model, fileName.replace(FREEMARKER_TEMPLATE_SUFFIX, ""));
            File targetFile = getTargetFile(templateHelper, model, templateDirectory, file, outDirectoryFile,
                                            targetName);
            FileUtils.touch(targetFile);
            String templateName = getRelativizeTemplateFile(templateDirectory, file).toPath().toString();
            templateHelper.processToFile(model, templateName, targetFile, "UTF-8");
        }
    }

    /**
     * 处理目录.
     *
     * @param model model
     * @param files files
     * @param templateDirectory templateDirectoryFile
     */
    private void processDirectory(FreemarkerHelper templateHelper, Map<String, Object> model, List<File> files,
                                  File templateDirectory) {
        for (File file : files) {
            File targetFile = getTargetFile(templateHelper, model, templateDirectory, file, outDirectoryFile,
                                            file.getName());
            targetFile.mkdirs();
        }
    }

    /**
     * 处理非模板文件生成.
     *
     * @param model model
     * @param files files
     * @param templateDirectory templateDirectoryFile
     * @throws IOException io exception
     */
    private void processBinary(FreemarkerHelper templateHelper, Map<String, Object> model, List<File> files,
                               File templateDirectory) throws IOException {
        for (File file : files) {
            File targetFile = getTargetFile(templateHelper, model, templateDirectory, file, outDirectoryFile,
                                            file.getName());
            FileUtils.copyFile(file, targetFile);
        }
    }

    /**
     * 获取模板目录所有文件列表和目录.
     *
     * @param directory the directory
     * @return the file list
     */
    private Collection<File> getTemplateDirectoryFiles(File directory) {
        return FileUtils.listFilesAndDirs(directory, TrueFileFilter.INSTANCE, DirectoryFileFilter.INSTANCE);
    }

    /**
     * 获取相对模板文件路径.
     *
     * @param rootDirectory the root directory
     * @param srcFile the src file
     * @return the relativize template file
     */
    private File getRelativizeTemplateFile(File rootDirectory, File srcFile) {
        return rootDirectory.toPath().relativize(srcFile.toPath()).toFile();
    }

    /**
     * 获取目标文件.
     *
     * @param model the model
     * @param rootDirectory the root directory
     * @param srcFile the src file
     * @param destDirectory the dest directory
     * @param fileName the file name
     * @return the target file
     */
    private File getTargetFile(FreemarkerHelper templateHelper, Map<String, Object> model, File rootDirectory,
                               File srcFile, File destDirectory, String fileName) {
        String targetPath;
        Path relativizePath = rootDirectory.toPath().relativize(srcFile.toPath());
        Path parent = relativizePath.getParent();
        if (parent == null) {
            targetPath = fileName;
        } else {
            targetPath = parent.toString() + File.separator + fileName;
        }
        // 处理路径被转义问题
        targetPath = targetPath.replace("\\$", "/$");
        try {
            targetPath = templateHelper.processToString(model, targetPath);
        } catch (IOException e) {
            LOGGER.error("", e);
            throw new GeneratorException("处理字符串模板出错:" + e.getMessage());
        }
        return new File(destDirectory, targetPath);
    }

    private FreemarkerHelper buildFreemarkerHelper(File templateBaseDir) {
        Configuration configuration = new Configuration(new Version(2, 3, 0));
        try {
            TemplateLoader templateLoader = new FileTemplateLoader(templateBaseDir);
            configuration.setTemplateLoader(templateLoader);
        } catch (IOException e) {
            throw new GeneratorException("构建模板助手出错:" + e.getMessage());
        }
        configuration.setNumberFormat("###############");
        configuration.setBooleanFormat("true,false");
        configuration.setDefaultEncoding("UTF-8");

        // 自动导入公共文件,用于支持灵活变量
        if (autoIncludeFile.exists()) {
            List<String> autoIncludeList = new ArrayList<>();
            autoIncludeList.add(FREEMARKER_AUTO_INCLUDE_SUFFIX);
            configuration.setAutoIncludes(autoIncludeList);
        }
        return new FreemarkerHelper(configuration);
    }
}
