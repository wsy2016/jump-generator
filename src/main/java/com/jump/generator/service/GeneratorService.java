package com.jump.generator.service;


import com.jump.generator.config.GenProperties;
import com.jump.generator.domain.ColumnInfo;
import com.jump.generator.domain.TableInfo;
import com.jump.generator.domain.ViewInfo;

import java.util.List;

/**
 * @author wsy
 * @date 2019-01-02
 */
public interface GeneratorService {

    /**
     * 查询数据库元数据
     *
     * @param name
     * @return
     */
    List<TableInfo> getTables(String name) throws Exception;


    /**
     * 查询数据库试图元数据
     *
     * @param name
     * @return
     */
    List<ViewInfo> getViews(String name) throws Exception;

    /**
     * 得到数据表的元数据
     *
     * @param name
     * @return
     */
    List<ColumnInfo> getColumns(String name) throws Exception;

    /**
     * 生成代码
     *
     * @param columnInfos
     * @param tableName
     */
    void generator(List<ColumnInfo> columnInfos, String tableName) throws Exception;
}
