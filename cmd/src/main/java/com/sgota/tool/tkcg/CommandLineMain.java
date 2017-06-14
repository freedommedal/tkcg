package com.sgota.tool.tkcg;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgota.tool.tkcg.core.DispatcherController;
import com.sgota.tool.tkcg.core.GeneratorConfig;

/**
 * 命令行启动类.
 *
 * @author tiankuo
 */
public class CommandLineMain {

    /**
     * logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineMain.class);

    /**
     * The constant OPTION_H
     */
    public static final String OPTION_H = "h";
    /**
     * The constant OPTION_C
     */
    public static final String OPTION_C = "c";

    /**
     * 主函数.
     *
     * @param args args
     */
    public static void main(String[] args) {
        Options options = builderOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption(OPTION_H)) {
                printHelp(options);
                return;
            }
        } catch (ParseException e) {
            printHelp(options);
            return;
        }
        try {
            GeneratorConfig config = loadConfig(cmd);
            DispatcherController dispatcher = DispatcherController.getInstance();
            dispatcher.dispatch(config);
        } catch (Exception e) {
            LOGGER.error("生成器错误", e);
        }
    }

    /**
     * 构建参数选项.
     *
     * @return options.
     */
    private static Options builderOptions() {
        Options options = new Options();
        options.addOption(OPTION_H, "help", false, "帮助");
        Option option;
        option = Option.builder(OPTION_C).required(true).hasArg().argName("configPath").desc("配置文件路径(必选)").build();
        options.addOption(option);
        return options;
    }

    /**
     * 打印帮助.
     *
     * @param options options
     */
    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("tkcg [options]", options);
    }

    /**
     * 启动生成器.
     *
     * @param cmd cmd
     */
    private static GeneratorConfig loadConfig(CommandLine cmd) {
        String configPath;
        // 从命令行获取参数值
        configPath = cmd.getOptionValue(OPTION_C);
        // 加载全局配置文件
        GeneratorConfig config = GeneratorConfig.loadConfig(configPath);
        LOGGER.info("加载全局配置文件{}", configPath);
        return config;
    }
}
