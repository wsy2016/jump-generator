package com.jump.generator.utils;

import com.jump.generator.config.DbProperties;

import java.sql.*;

/**
 * <p>
 * Your vision will become clear only when you can look into your own heart.
 * 唯有审视内心，你的愿景才会清晰起来。
 * </p>
 *
 * @author wsy
 * @date 2020/5/22
 */
public class DbUtil {

    public static final String URL = "jdbc:mysql://"+ DbProperties.DB_URL+"?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false";
    public static final String USER = DbProperties.DB_USER;
    public static final String PASSWORD = DbProperties.DB_USER;

    public static void main(String[] args) throws Exception {


        //1.加载驱动程序
        Class.forName("com.mysql.jdbc.Driver");
        //2. 获得数据库连接
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Connection getConn() {
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //2. 获得数据库连接
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("数据库配置错误");
            e.printStackTrace();
        }
        return null;
    }
}