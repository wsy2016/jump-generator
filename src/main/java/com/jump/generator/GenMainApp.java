package com.jump.generator;

import com.jump.generator.domain.ColumnInfo;
import com.jump.generator.domain.TableInfo;
import com.jump.generator.domain.ViewInfo;
import com.jump.generator.service.GeneratorService;
import com.jump.generator.service.impl.GeneratorServiceImpl;

import java.util.List;


/**
 * @author wsy
 * @date 2020/5/21
 */
public class GenMainApp {

    private static final String DEFAULT_TABLE_NAME = "weapp_tutorial";

    public static void main(String[] args) {
        try {

            getTables("weapp");
            generator(DEFAULT_TABLE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generator(String tablename) {
        /**
         * 创建代码
         * @param tablename
         * @return void
         * @author wsy
         * @date 2020/5/25
         */
        GeneratorService service = new GeneratorServiceImpl();
        try {
            List<ColumnInfo> columnInfos = service.getColumns(tablename);
            service.generator(columnInfos, tablename);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 表的集合
     *
     * @param tableName
     * @return void
     * @author wsy
     * @date 2020/5/25
     */
    public static void getTables(String tableName) {

        try {
            GeneratorService service = new GeneratorServiceImpl();
            List<TableInfo> tables = service.getTables(tableName);
            for (TableInfo table : tables) {
                System.out.println("结果:\n"+table.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 试图的集合
     *
     * @param viewName
     * @return void
     * @author wsy
     * @date 2020/5/25
     */
    public static void getViews(String viewName) {
        try {
            GeneratorService service = new GeneratorServiceImpl();
            List<ViewInfo> views = service.getViews(viewName);
            for (ViewInfo view : views) {
                System.out.println("结果:\n"+view.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
