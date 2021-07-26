package com.tiange.com.tiange.core.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import   com.tiange.com.tiange.*;

public class DbUtil {

    static  Connection connection=null;
    /**
     * 获取数据库连接
     * @param url 数据库地址
     * @param username 账号
     * @param password 密码
     * @return Connection
     */
     static Connection getConnection(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * 读取数据库多行记录，转换为对象
     * @param connection 数据库连接
     * @param sql    查询语句
     * @param clazz  对象类型
     * @return List<?> 结果集
     */
     static List<?> queryForBeans(Connection connection,String sql,Class clazz){

        QueryRunner queryRunner = new QueryRunner();

        BeanListHandler<?> beanListHandler = new BeanListHandler(clazz);
        try {
            List<?> empList = queryRunner.query(connection, sql, beanListHandler);

            return empList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static void execute(Connection connection, String sql) throws SQLException {
        QueryRunner runner = new QueryRunner();
        runner.update(connection, sql);
    }
}
