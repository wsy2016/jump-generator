package com.jump.generator.utils;


import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.*;
import com.jump.commons.utils.FileUtil;
import com.jump.commons.utils.StringUtils;
import com.jump.generator.config.GenProperties;
import com.jump.generator.domain.ColumnInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成
 *
 * @author wsy
 * @date 2019-01-02
 */
@Slf4j
@Service
public class GenUtil {

    private static final String TIMESTAMP = "Timestamp";

    private static final String BIGDECIMAL = "BigDecimal";

    private static final String PK = "PRI";

    private static final String EXTRA = "auto_increment";

    /**
     * 获取后端代码模板名称
     *
     * @return
     */
    public static List<String> getAdminTemplateNames() {
        List<String> templateNames = new ArrayList<>();
        templateNames.add("Entity");
        templateNames.add("Dto");
        templateNames.add("Mapper");
        templateNames.add("Repository");
        templateNames.add("Service");
        templateNames.add("ServiceImpl");
        templateNames.add("QueryCriteria");
        templateNames.add("Controller");
        return templateNames;
    }

    /**
     * 获取前端代码模板名称
     *
     * @return
     */
    public static List<String> getFrontTemplateNames() {
        List<String> templateNames = new ArrayList<>();
        templateNames.add("api");
        templateNames.add("index");
        templateNames.add("header");
        templateNames.add("edit");
        templateNames.add("eForm");
        return templateNames;
    }

    /**
     * 生成代码
     *
     * @param columnInfos 表元数据
     */
    public static void generatorCode(List<ColumnInfo> columnInfos, String tableName) throws IOException {
        Map<String, Object> map = new HashMap();
        map.put("package", GenProperties.pack);
        map.put("moduleName", GenProperties.moduleName);
        map.put("author", GenProperties.author);
        map.put("date", LocalDate.now().toString());
        map.put("tableName", tableName);
        String className = StringUtils.toCapitalizeCamelCase(tableName);
        String changeClassName = StringUtils.toCamelCase(tableName);

        // 判断是否去除表前缀
        if (StringUtils.isNotEmpty(GenProperties.prefix)) {
            className = StringUtils.toCapitalizeCamelCase(StrUtil.removePrefix(tableName, GenProperties.prefix));
            changeClassName = StringUtils.toCamelCase(StrUtil.removePrefix(tableName, GenProperties.prefix));
        }
        map.put("className", className);
        map.put("upperCaseClassName", className.toUpperCase());
        map.put("changeClassName", changeClassName);
        map.put("hasTimestamp", false);
        map.put("hasBigDecimal", false);
        map.put("hasQuery", false);
        map.put("auto", false);

        List<Map<String, Object>> columns = new ArrayList<>();
        List<Map<String, Object>> queryColumns = new ArrayList<>();
        for (ColumnInfo column : columnInfos) {
            Map<String, Object> listMap = new HashMap();
            listMap.put("columnComment", column.getColumnComment());
            listMap.put("columnKey", column.getColumnKey());

            String colType = ColUtil.cloToJava(column.getColumnType().toString());
            String changeColumnName = StringUtils.toCamelCase(column.getColumnName().toString());
            String capitalColumnName = StringUtils.toCapitalizeCamelCase(column.getColumnName().toString());
            if (PK.equals(column.getColumnKey())) {
                map.put("pkColumnType", colType);
                map.put("pkChangeColName", changeColumnName);
                map.put("pkCapitalColName", capitalColumnName);
            }
            if (TIMESTAMP.equals(colType)) {
                map.put("hasTimestamp", true);
            }
            if (BIGDECIMAL.equals(colType)) {
                map.put("hasBigDecimal", true);
            }
            if (EXTRA.equals(column.getExtra())) {
                map.put("auto", true);
            }
            listMap.put("columnType", colType);
            listMap.put("columnName", column.getColumnName());
            listMap.put("isNullable", column.getIsNullable());
            listMap.put("columnShow", column.getColumnShow());
            listMap.put("changeColumnName", changeColumnName);
            listMap.put("capitalColumnName", capitalColumnName);

            if (!StringUtils.isBlank(column.getColumnQuery())) {
                listMap.put("columnQuery", column.getColumnQuery());
                map.put("hasQuery", true);
                queryColumns.add(listMap);
            }
            columns.add(listMap);
        }
        map.put("columns", columns);
        map.put("queryColumns", queryColumns);
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig("template", TemplateConfig.ResourceMode.CLASSPATH));

        // 生成后端代码
        List<String> templates = getAdminTemplateNames();
        for (String templateName : templates) {
            Template template = engine.getTemplate("generator/admin/" + templateName + ".ftl");
            String filePath = getAdminFilePath(templateName, className);

            File file = new File(filePath);

            // 如果非覆盖生成
            if (!GenProperties.cover) {
                if (FileUtil.exist(file)) {
                    continue;
                }
            }
            // 生成代码
            genFile(file, template, map);
        }

        // 生成前端代码
        templates = getFrontTemplateNames();
        for (String templateName : templates) {
            Template template = engine.getTemplate("generator/front/" + templateName + ".ftl");
            String filePath = getFrontFilePath(templateName, map.get("changeClassName").toString());

            File file = new File(filePath);

            // 如果非覆盖生成
            if (!GenProperties.cover) {
                if (FileUtil.exist(file)) {
                    continue;
                }
            }
            // 生成代码
            genFile(file, template, map);
        }
    }

    /**
     * 定义后端文件路径以及名称
     */
    public static String getAdminFilePath(String templateName, String className) {
        String ProjectPath = System.getProperty("user.dir") + File.separator + GenProperties.moduleName;
        String packagePath = ProjectPath + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator;
        if (!ObjectUtils.isEmpty(GenProperties.pack)) {
            packagePath += GenProperties.pack.replace(".", File.separator) + File.separator;
        }

        if ("Entity".equals(templateName)) {
            return packagePath + "domain" + File.separator +"po"+  File.separator+className + ".java";
        }

        if ("Controller".equals(templateName)) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if ("Service".equals(templateName)) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if ("ServiceImpl".equals(templateName)) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if ("Dto".equals(templateName)) {
            return packagePath + "domain" + File.separator + "dto" + File.separator + className + "DTO.java";
        }

        if ("QueryCriteria".equals(templateName)) {
            return packagePath + "domain" + File.separator + "qeuery" + File.separator + className + "QueryCriteria.java";
        }

        if ("Mapper".equals(templateName)) {
            return packagePath + "service" + File.separator + "mapper" + File.separator + className + "Mapper.java";
        }

        if ("Repository".equals(templateName)) {
            return packagePath + "repository" + File.separator + className + "Repository.java";
        }

        return null;
    }

    /**
     * 定义前端文件路径以及名称
     */
    public static String getFrontFilePath(String templateName, String apiName) {
        String path = GenProperties.path;

        if ("api".equals(templateName)) {
            return GenProperties.apiPath + File.separator + apiName + ".js";
        }

        if ("index".equals(templateName)) {
            return path + File.separator + "index.vue";
        }

        if ("header".equals(templateName)) {
            return path + File.separator + "module" + File.separator + "header.vue";
        }

        if ("edit".equals(templateName)) {
            return path + File.separator + "module" + File.separator + "edit.vue";
        }

        if ("eForm".equals(templateName)) {
            return path + File.separator + "module" + File.separator + "form.vue";
        }
        return null;
    }

    public static void genFile(File file, Template template, Map<String, Object> map) throws IOException {
        // 生成目标文件
        Writer writer = null;
        try {
            FileUtil.touch(file);
            writer = new FileWriter(file);
            template.render(map, writer);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            writer.close();
        }
    }
}