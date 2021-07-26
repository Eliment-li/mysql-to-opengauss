package com.tiange.core.utils;

import com.tiange.com.tiange.temp.Employee;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


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

    public static void main(String[] args) {

        String url = "jdbc:mysql://" + "localhost" + ":" + "3333" + "/" + "mysqltest";
        String timeZone = "GMT%2B8";

        url += "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true";

        url += "&serverTimezone=" + timeZone;

        Connection conn = getConnection(url, "root", "root123..0");

        List<?> empList = queryForBeans(conn, "select * from employees", Employee.class);

        List<Employee> realList = (List<Employee>) empList;
        for (Employee employee : realList) {
            System.out.println(employee.getId());
        }

    }
}
