package com.tiange.core.utils.database;

import com.tiange.core.utils.database.manager.Manager;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySqlDbUtil implements Manager {

    static String DATABASE_URL = "jdbc:mysql://" + "localhost" + ":" + "3333" + "/" + "mysqltest" + "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";
    static String DATABASE_USERNAME = "root";
    static String DATABASE_PASSWORD = "root123..0";

    public static List<?> queryForBeans(Connection connection, String sql, Class clazz) {

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

    @Override
    public List<?> queryForObjectList(String sql, Class clazz) {

        Connection conn = getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        BeanListHandler<?> beanListHandler = new BeanListHandler(clazz);

        try {

            QueryRunner queryRunner = new QueryRunner();

            return queryRunner.query(conn, sql, beanListHandler);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object queryForObject(String sql, Class clazz) {
        Object obj = null;

        Connection conn = getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

        QueryRunner queryRunner = new QueryRunner();

        BeanListHandler<?> beanListHandler = new BeanListHandler(clazz);
        try {

            List<?> empList = queryRunner.query(conn, sql, beanListHandler);

            if (empList.size() > 0)
                obj = empList.get(0);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public int execute(String sql) {
        Connection connection = getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        QueryRunner runner = new QueryRunner();
        try {
            return runner.update(connection, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int execute(String sql, Object[] args) {
        return 0;
    }


    /**
     * 获取数据库连接
     * @param url 数据库地址
     * @param username 账号
     * @param password 密码
     * @return Connection
     */
    private static Connection getConnection(String url, String username, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


}
