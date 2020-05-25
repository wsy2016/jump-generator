package com.jump.generator.service.impl;
import com.jump.generator.domain.ColumnInfo;
import com.jump.generator.domain.TableInfo;
import com.jump.generator.domain.ViewInfo;
import com.jump.generator.service.GeneratorService;
import com.jump.generator.utils.DbUtil;
import com.jump.generator.utils.GenUtil;
import org.springframework.util.ObjectUtils;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

;

/**
 * @author wsy
 * @date 2019-01-02
 */
public class GeneratorServiceImpl implements GeneratorService {



    @Override
    public List<TableInfo> getTables(String name) throws Exception {
        StringBuilder sql = new StringBuilder("select table_name tableName,create_time createTime from information_schema.tables where table_schema = (select database()) ");
        if (!ObjectUtils.isEmpty(name)) {
            sql.append("and table_name like '%" + name + "%' ");
        }
        sql.append("order by table_name");

        Statement statement = DbUtil.getConn().createStatement();

        ResultSet rs = statement.executeQuery(sql.toString());
        System.out.println(sql.toString());
        List<TableInfo> tableInfos = new ArrayList<>();
        while (rs.next()) {
            tableInfos.add(new TableInfo(rs.getString("tableName"), rs.getObject("createTime")));
        }
        return tableInfos;
    }

    @Override
    public List<ViewInfo> getViews(String name) throws Exception {
        StringBuilder sql = new StringBuilder("SELECT\n" +
                "\ttable_name tableName\n" +
                "FROM\n" +
                "\tinformation_schema. VIEWS\n" +
                "WHERE\n" +
                "\ttable_schema = (SELECT DATABASE())");
        if (!ObjectUtils.isEmpty(name)) {
            sql.append("and table_name like '%" + name + "%' ");
        }
        sql.append("order by table_name");

        Statement statement = DbUtil.getConn().createStatement();

        ResultSet rs = statement.executeQuery(sql.toString());
        System.out.println(sql.toString());
        List<ViewInfo> tableInfos = new ArrayList<>();
        while (rs.next()) {
            tableInfos.add(new ViewInfo(rs.getString("tableName")));
        }
        return tableInfos;    }

    @Override
    public List<ColumnInfo> getColumns(String name) throws Exception {
        StringBuilder sql = new StringBuilder("select column_name, is_nullable, data_type, column_comment, column_key, extra from information_schema.columns where ");
        if (!ObjectUtils.isEmpty(name)) {
            sql.append("table_name = '" + name + "' ");
        }
        sql.append("and table_schema = (select database()) order by ordinal_position");
        Statement statement = DbUtil.getConn().createStatement();

        ResultSet rs = statement.executeQuery(sql.toString());

        List<ColumnInfo> columnInfos = new ArrayList<>();

        while (rs.next()) {
            columnInfos.add(new ColumnInfo(
                    rs.getObject(1),
                    rs.getObject(2),
                    rs.getObject(3),
                    rs.getObject(4),
                    rs.getObject(5),
                    rs.getObject(6),
                    null,
                    "true"
            ));
        }
        return columnInfos;
    }

    @Override
    public void generator(List<ColumnInfo> columnInfos, String tableName) {
        try {
            GenUtil.generatorCode(columnInfos, tableName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
