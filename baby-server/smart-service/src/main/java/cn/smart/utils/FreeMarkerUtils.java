package cn.smart.utils;

import freemarker.core.ParseException;
import freemarker.template.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.*;
import java.nio.channels.FileLock;
import java.util.*;

/**
 * FreeMarker工具类
 * 
 * FreeMarkerUtils
 * 
 */
public class FreeMarkerUtils {
    private final static Logger log = LogManager.getLogger(FreeMarkerUtils.class);
    private final static String DEFAULT_CHARSET = "UTF-8";
    public final static String ROOT_PATH = "/home/";
    public final static String FTL_PATH = "ftl";

    public static String generateArticleByFile(Map<String, Object> model, String path, String fileName) {

        try {
            generate(model, ROOT_PATH + path, fileName, "article");
        } catch (Exception e) {
            e.printStackTrace();
        }

      return path + fileName;
    }

    public static String generate(Map<String, Object> model, String path, String fileName, String ftl) {

        String filePath = path + fileName;
        String ftlPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).toString() + FTL_PATH;

        ftlPath = ftlPath.replace("file:", "").replace("classes/", "").substring(1);
        log.info(ftlPath);
        Configuration configuration;
        configuration = new Configuration(Configuration.getVersion());
        configuration.setDefaultEncoding("UTF-8");
        try {
            configuration.setDirectoryForTemplateLoading(new File("/" + ftlPath));
        } catch (IOException e) {
            log.error("获取ftl文件路径失败! {}", e.getMessage());
        }

        try {
            FreeMarkerUtils.generateFileByFile(ftl + ".ftl", filePath, configuration, model, true);
        } catch (IOException | TemplateException e) {
            log.error("创建文章静态页面失败! {}", e.getMessage());
        }

        return path + fileName;
    }

    /**
     * @param templateFilePath
     * @param destFilePath
     * @param configuration
     * @param model
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateFileByFile(String templateFilePath, String destFilePath, Configuration configuration,
            Map<String, Object> model) throws IOException, TemplateException {
        generateFileByFile(templateFilePath, destFilePath, configuration, model, true, false);
    }

    /**
     * @param templateFilePath
     * @param destFilePath
     * @param configuration
     * @param model
     * @param override
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateFileByFile(String templateFilePath, String destFilePath, Configuration configuration,
            Map<String, Object> model, boolean override) throws IOException, TemplateException {
        generateFileByFile(templateFilePath, destFilePath, configuration, model, override, false);
    }

    /**
     * @param templateFilePath
     * @param destFilePath
     * @param configuration
     * @param model
     * @param override
     * @param append
     * @throws ParseException
     * @throws MalformedTemplateNameException
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateFileByFile(String templateFilePath, String destFilePath, Configuration configuration,
            Map<String, Object> model, boolean override, boolean append)
            throws MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Template t = configuration.getTemplate(templateFilePath);
        File destFile = new File(destFilePath);
        if (override || append || !destFile.exists()) {
            File parent = destFile.getParentFile();
            if (null != parent) {
                parent.mkdirs();
            }
            try (FileOutputStream outputStream = new FileOutputStream(destFile, append);
                    FileLock fileLock = outputStream.getChannel().tryLock();) {
                Writer out = new OutputStreamWriter(outputStream, DEFAULT_CHARSET);
                t.process(model, out);
            }
            log.info(destFilePath + "    saved!");
        } else {
            log.error(destFilePath + "    already exists!");
        }
    }

    /**
     * @param template
     * @param configuration
     * @return render result
     * @throws TemplateException
     * @throws IOException
     */
    public static String generateStringByFile(String template, Configuration configuration)
            throws IOException, TemplateException {
        Map<String, Object> model = Collections.emptyMap();
        return generateStringByFile(template, configuration, model);
    }

    /**
     * @param template
     * @param configuration
     * @param model
     * @return render result
     * @throws IOException
     * @throws TemplateException
     */
    public static String generateStringByFile(String template, Configuration configuration, Map<String, Object> model)
            throws IOException, TemplateException {
        StringWriter writer = new StringWriter();
        generateStringByFile(writer, template, configuration, model);
        return writer.toString();
    }

    /**
     * @param writer
     * @param template
     * @param configuration
     * @param model
     * @throws TemplateNotFoundException
     * @throws MalformedTemplateNameException
     * @throws ParseException
     * @throws IOException
     * @throws TemplateException
     */
    public static void generateStringByFile(Writer writer, String template, Configuration configuration,
            Map<String, Object> model)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
        Template tpl = configuration.getTemplate(template);
        tpl.process(model, writer);
    }

    /**
     * @param templateContent
     * @param configuration
     * @param model
     * @return render result
     * @throws IOException
     * @throws TemplateException
     */
    public static String generateStringByString(String templateContent, Configuration configuration, Map<String, Object> model)
            throws IOException, TemplateException {
        Template tpl = new Template(null, templateContent, configuration);
        StringWriter writer = new StringWriter();
        tpl.process(model, writer);
        return writer.toString();
    }
}
