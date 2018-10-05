package com.sgota.tkcg.helper;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import com.sgota.tkcg.GeneratorException;
import com.sgota.tkcg.messages.TkcgMessages;

/**
 * 文件助手
 *
 * @author tiankuo
 */
public class FileHelper {

    /**
     * 排除文件列表
     */
    private static final String[] EXCLUDE_FILE_LIST = new String[] { "*.ftl", "*.include" };
    /**
     * 排除目录列表
     */
    private static final String[] EXCLUDE_DIR_LIST = new String[] { ".git" };

    /**
     * constructor
     */
    private FileHelper(){
        // private
    }

    /**
     * 获取文件名列表
     *
     * @param directory the directory
     * @return the binarys
     */
    public static List<String> getBinarys(File directory) {
        Collection<File> files = FileUtils.listFiles(directory,
                                                     new NotFileFilter(new WildcardFileFilter(EXCLUDE_FILE_LIST)),
                                                     new NotFileFilter(new WildcardFileFilter(EXCLUDE_DIR_LIST)));
        return files.stream().map(file -> directory.toPath().relativize(file.toPath()).toString()).collect(Collectors.toList());
    }

    /**
     * 获取模板名列表
     *
     * @param directory the directory
     * @return the templates
     */
    public static List<String> getTemplates(File directory) {
        Collection<File> files = FileUtils.listFiles(directory, new String[] { "ftl" }, true);
        return files.stream().map(file -> directory.toPath().relativize(file.toPath()).toString()).collect(Collectors.toList());
    }

    /**
     * 复制文件内容
     *
     * @param srcDir srcDir
     * @param srcName srcName
     * @param outDir outDir
     * @param destName destName
     */
    public static void copyFile(File srcDir, String srcName, File outDir, String destName) {
        File targetFile = new File(outDir, destName);
        try {
            FileUtils.touch(targetFile);
        } catch (IOException e) {
            throw new GeneratorException(TkcgMessages.message("file.touch", targetFile.toString()));
        }
        try {
            FileUtils.copyFile(new File(srcDir, srcName), targetFile);
        } catch (IOException e) {
            throw new GeneratorException(TkcgMessages.message("file.copy", e.getMessage()));
        }
    }
}
