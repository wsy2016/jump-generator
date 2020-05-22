package com.jump.generator.config;

import lombok.Data;

/**
 * <p>
 * Your vision will become clear only when you can look into your own heart.
 * 唯有审视内心，你的愿景才会清晰起来。
 * </p>
 *
 * @author wsy
 * @date 2020/5/21
 */
@Data
public class GenProperties {
    /**
     * 包路径
     **/
    public final static String pack = "com.jump.generator.test";

    /**
     * 模块名
     **/
    public final static String moduleName = "";

    /**
     * 前端文件路径
     **/
    public final static String path = "E:\\code\\views\\";

    /**
     * 前端文件路径
     **/
    public final static String apiPath = "E:\\code\\api\\";

    /**
     * 作者
     **/
    public final static String author = "wsy";

    /**
     * 表前缀
     **/
    public final static String prefix = "tb";

    /**
     * 是否覆盖
     **/
    public final static Boolean cover = true;



}
