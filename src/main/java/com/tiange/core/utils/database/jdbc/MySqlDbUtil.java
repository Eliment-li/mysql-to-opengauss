package com.tiange.core.utils.database.jdbc;

import com.tiange.core.mysql.database.DatabaseConfig;
import com.tiange.core.utils.database.manager.Manager;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MySqlDbUtil implements Manager {


    DatabaseConfig config;

    public MySqlDbUtil() {
        this.config = new DatabaseConfig("localhost", 3333, "root", "root123..0", "mysqltest");
    }
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

    /*
163  *  MapListHandler
164  *  将结果集每一行存储到Map集合,键:列名,值:数据
166  */
    public List<Map<String, Object>> queryForMapList(String sql) throws SQLException {

        QueryRunner qr = new QueryRunner();


        Connection conn = getConnection();

        List<Map<String,Object>> list = qr.query(conn, sql, new MapListHandler());

        //遍历集合list
        for( Map<String,Object> map : list ){
            for(String key : map.keySet()){
                // System.out.print(key+"..."+map.get(key)+", ");
            }
                System.out.println();
        }

        return list;
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

    public  Connection getConnection() {

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
