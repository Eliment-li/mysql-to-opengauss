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

public class DbUtil {


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
        }
        return connection;
    }

    public static void execute(Connection connection, String sql) throws SQLException {
        QueryRunner runner = new QueryRunner();
        runner.update(connection, sql);
    }
}
