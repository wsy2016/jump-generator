package com.jump.generator.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 表的数据信息
 * @author wsy
 * @date 2019-01-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ViewInfo {

    /** 表名称 **/
    private String tableName;


}
