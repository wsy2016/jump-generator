package com.jump.generator;

import com.jump.generator.domain.ColumnInfo;
import com.jump.generator.domain.TableInfo;
import com.jump.generator.service.GeneratorService;
import com.jump.generator.service.impl.GeneratorServiceImpl;

import java.util.List;


/**
 * @author wsy
 * @date 2020/5/21
 */
public class GenMainApp {

    private static final String DEFAULT_TABLE_NAME = "tb_device_bind";

    public static void main(String[] args) {
        try {
            GeneratorService service = new GeneratorServiceImpl();
            List<ColumnInfo> columnInfos = service.getColumns(DEFAULT_TABLE_NAME);
            service.generator(columnInfos,DEFAULT_TABLE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getTables(String[] args) {
        try {
            GeneratorService service = new GeneratorServiceImpl();
            List<TableInfo> tables = service.getTables(DEFAULT_TABLE_NAME);
            for (TableInfo table : tables) {
                System.out.println(table.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
