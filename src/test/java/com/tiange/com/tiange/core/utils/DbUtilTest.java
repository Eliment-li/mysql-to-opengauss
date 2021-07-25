package com.tiange.com.tiange.core.utils;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class DbUtilTest {


    /**
     * 获取数据库连接
     * @param url 数据库地址
     * @param username 账号
     * @param password 密码
     * @return Connection
     */
    public static Connection getConnection(String url, String username, String password) {
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接失败");
        }
        return connection;
    }

    public static void execute(Connection connection, String sql) throws SQLException {
        QueryRunner runner = new QueryRunner();
        runner.update(connection, sql);
    }

    public static void main(String[] args) {

        String url = "jdbc:mysql://" + "localhost" + ":" + "3333" + "/" + "mysqltest";
        String timeZone = "GMT%2B8";

        url += "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";

        url += "&serverTimezone=" + timeZone;

        getConnection(url, "root","root123..0");
    }
}
