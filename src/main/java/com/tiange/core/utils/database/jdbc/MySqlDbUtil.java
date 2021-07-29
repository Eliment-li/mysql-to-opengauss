package com.tiange.core.utils.database.jdbc;

import com.tiange.core.entity.mysql.DatabaseConfig;
import com.tiange.core.utils.database.manager.Manager;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class MySqlDbUtil implements Manager {

   /* static String DATABASE_URL = "jdbc:mysql://" + "localhost" + ":" + "3333" + "/" + "mysqltest" + "?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT%2B8";
    static String DATABASE_USERNAME = "root";
    static String DATABASE_PASSWORD = "root123..0";*/

    DatabaseConfig config;

    public MySqlDbUtil(DatabaseConfig config) {
        this.config = config;
    }

    public List<?> queryForBeans(String sql, Class clazz) {

        Connection conn = getConnection();

        QueryRunner queryRunner = new QueryRunner();

        BeanListHandler<?> beanListHandler = new BeanListHandler(clazz);
        try {
            List<?> empList = queryRunner.query(conn, sql, beanListHandler);

            return empList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<?> queryForObjectList(String sql, Class clazz) {

        Connection conn = getConnection();

        BeanListHandler<?> beanListHandler = new BeanListHandler(clazz);

        try {

            QueryRunner queryRunner = new QueryRunner();

            DbUtils.close(conn);

            return queryRunner.query(conn, sql, beanListHandler);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object queryForObject(String sql, Class clazz) {
        Object obj = null;

        Connection conn = getConnection();

        QueryRunner queryRunner = new QueryRunner();

        BeanListHandler<?> beanListHandler = new BeanListHandler(clazz);

        try {

            List<?> empList = queryRunner.query(conn, sql, beanListHandler);

            if (empList.size() > 0)
                obj = empList.get(0);

            DbUtils.close(conn);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return obj;
    }

    @Override
    public int execute(String sql) {

        Connection conn = getConnection();

        QueryRunner runner = new QueryRunner();
        try {

            int effectRows = runner.update(conn, sql);

            DbUtils.close(conn);

            return effectRows;

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
     *  url 数据库地址
     *  username 账号
     *  password 密码
     * @return Connection
     */

    public Connection getConnection() {

        String url = this.config.getUrl();
        String username = this.config.getUsername();
        String password = this.config.getPassword();

        Connection connection = null;


        try {
            connection = DriverManager.getConnection(url, username, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }


}
