package com.tiange.core.utils.database.jdbc;

import com.tiange.core.mysql.database.DatabaseConfig;
import com.tiange.core.utils.database.druid.DruidUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MySqlDbUtil {


    DatabaseConfig config;

    public MySqlDbUtil() {

    }

    public MySqlDbUtil(DatabaseConfig config) {
        this.config = config;
    }

    /**
     * 获取数据库连接
     *
     * @return Connection 数据库连接
     */
    public static Connection getConnection() {

        Connection connection = null;

        try {
            connection = DruidUtil.getMysqlConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 查询数据库，将查询到的每一行放入一个Map中
     *
     * @param sql 查询语句
     * @return 结果集
     * 166
     */
    public static List<Map<String, Object>> queryForMapList(String sql) throws SQLException {

        QueryRunner qr = new QueryRunner();

        Connection conn = getConnection();

        List<Map<String, Object>> list = qr.query(conn, sql, new MapListHandler());


        DbUtils.close(conn);

        return list;
    }

    /**
     * 按页查询 ， Page 中的内容格式为 List<Map<String, Object>>
     * @return Page
     */
    public static Page queryForPage(String sql, Page page) {

        try {
            System.out.println("第" + page.getPageNum() + "页 ");
            List<Map<String, Object>> list = queryForMapList(getLimitSqlString(sql, page));
            page.setPageContent(list);

            System.out.println();
        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("第" + page.getPageNum() + "页 " + "查询失败");

        }
        return page;
    }

    /**
     * 为 sql添加 LIMIT 语句
     * @param sql 查询语句
     * @param page 分页信息
     * @return 带 LIMIT 的查询语句
     */
    private static String getLimitSqlString(String sql, Page page) {

        if (page.getPageNum() <= 1)
            sql = sql.concat(" limit " + (page.getPageSize()));
        else {
            long offset = (page.getPageNum() - 1) * page.getPageSize();
            sql = sql.concat(" limit " + offset + "," + (page.getPageSize()));

        }
        return sql;
    }

    /**
     * 执行单个增删查改语句
     *
     * @param sql
     * @return effect rows
     */
    public int execute(String sql) {

        Connection conn = getConnection();

        QueryRunner runner = new QueryRunner();
        try {

            int effectRows = runner.update(conn, sql);

            DbUtils.close(conn);

            return effectRows;

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 查询数据库，并按照字段名封装到对象中
     * 只要查询的数据库表字段和对象的属性同名就可以封装成所需对象
     * @param sql 查询语句
     * @param clazz 封装目标类的 class 对象
     * @return 对象List
     */
    public List<?> queryForObjectList(String sql, Class clazz) {

        Connection conn = getConnection();

        QueryRunner queryRunner = new QueryRunner();

        BeanListHandler<?> beanListHandler = new BeanListHandler(clazz);
        try {
            List<?> empList = queryRunner.query(conn, sql, beanListHandler);

            DbUtils.close(conn);
            return empList;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 查询数据库，并按照字段名封装到对象中
     * 只要查询的数据库表字段和对象的属性同名就可以封装成所需对象
     * @param sql 查询语句
     * @param clazz 封装目标类的 class 对象
     * @return 单个对象
     */
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return obj;
    }

    /**
     * @param sql
     * @return 查询单个标量
     * @throws SQLException
     */
    public Long QueryForScalar(String sql) throws SQLException {

        Connection conn = getConnection();

        QueryRunner qr = new QueryRunner();

        long count = qr.query(conn, sql, new ScalarHandler<Long>());

        DbUtils.close(conn);
        return count;
    }



}
