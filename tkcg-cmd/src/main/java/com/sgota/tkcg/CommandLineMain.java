package com.sgota.tkcg;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.sgota.tkcg.core.CodeConfig;
import com.sgota.tkcg.core.CodeGenerator;
import com.sgota.tkcg.core.ProjectConfig;
import com.sgota.tkcg.core.ProjectGenerator;
import com.sgota.tkcg.util.YamlUtils;

/**
 * 命令行启动类
 *
 * @author tiankuo
 */
public class CommandLineMain {

    /**
     * OPTION_T
     */
    public static final String OPTION_T = "t";
    /**
     * OPTION_G
     */
    public static final String OPTION_G = "g";
    /**
     * OPTION_F
     */
    public static final String OPTION_F = "f";
    /**
     * OPTION_O
     */
    public static final String OPTION_O = "o";

    /**
     * 主函数
     *
     * @param args args
     */
    public static void main(String[] args) {
        Options options = builderOptions();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            printHelp(options);
            return;
        }
        launch(cmd, options);
    }

    private static void launch(CommandLine cmd, Options options) {
        TkcgCmdLog.register();
        try {
            String template = "template";
            String configPath = null;
            String templateDir;
            String outDir = "dest";
            if (cmd.hasOption(OPTION_T)) {
                template = cmd.getOptionValue(OPTION_T);
            }
            if (cmd.hasOption(OPTION_F)) {
                configPath = cmd.getOptionValue(OPTION_F);
            }
            if (cmd.hasOption(OPTION_O)) {
                outDir = cmd.getOptionValue(OPTION_O);
            }
            String type = cmd.getOptionValue(OPTION_G);
            switch (type) {
                case "p":
                    if (configPath == null) {
                        configPath = template + "/project.yml";
                    }
                    templateDir = template + "/default_project";
                    genProject(configPath, templateDir, outDir);
                    TkcgCmdLog.info(TkcgCmdMessages.message("generator.succeed"));
                    break;
                case "c":
                    if (configPath == null) {
                        configPath = template + "/code.yml";
                    }
                    templateDir = template + "/default_code";
                    genCode(configPath, templateDir, outDir);
                    TkcgCmdLog.info(TkcgCmdMessages.message("generator.succeed"));
                    break;
                default:
                    printHelp(options);
            }
        } catch (Exception e) {
            TkcgCmdLog.error(TkcgCmdMessages.message("generator.failure"));
            TkcgCmdLog.error(e.getMessage());
        }
    }

    private static void genProject(String configPath, String templateDir, String outDir) {
        File file = new File(configPath);
        ProjectConfig config = YamlUtils.loadByFile(file, ProjectConfig.class);
        config.setTemplateDir(templateDir);
        if (outDir != null) {
            config.setOutDir(outDir);
        }
        config.setOutDir(outDir);
        ProjectGenerator generator = new ProjectGenerator();
        generator.execute(config);
    }

    private static void genCode(String configPath, String templateDir, String outDir) {
        File file = new File(configPath);
        CodeConfig config = YamlUtils.loadByFile(file, CodeConfig.class);
        config.setTemplateDir(templateDir);
        config.setOutDir(outDir);
        CodeGenerator generator = new CodeGenerator();
        generator.execute(config);
    }

    /**
     * 构建参数选项
     * 
     * @return options
     */
    private static Options builderOptions() {
        Options options = new Options();
        Option option;
        option = Option.builder(OPTION_T).hasArg().build();
        options.addOption(option);
        option = Option.builder(OPTION_G).desc("生成器类别\n参数p 生成项目\n参数c 生成代码").hasArg().required().build();
        options.addOption(option);
        option = Option.builder(OPTION_F).desc("配置文件路径").hasArg().build();
        options.addOption(option);
        option = Option.builder(OPTION_O).desc("输出路径").hasArg().build();
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
        formatter.printHelp("tkcg [options args]", options);
    }
}
