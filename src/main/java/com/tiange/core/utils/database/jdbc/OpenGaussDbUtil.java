package com.tiange.core.utils.database.jdbc;

import com.tiange.core.utils.database.druid.DruidUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class OpenGaussDbUtil {

    //日志工具
    private static final Logger logger = LoggerFactory.getLogger(OpenGaussDbUtil.class);
    /**
     * 获取数据库连接
     *
     * @return Connection 数据库连接
     */
    private static Connection getConnection() {

        Connection connection = null;
        try {
            //从线程池获取连接
            connection = DruidUtil.getGaussConnection();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取数据库连接失败");
        }
        return connection;
    }

    /**
     * 执行单个增删查改语句
     * @param sql 查询语句
     * @return effect rows
     */
    public static int execute(String sql) {
        {
            Statement stmt = null;
            try {
                stmt = getConnection().createStatement();

                //执行普通SQL语句。
                int rc = stmt.executeUpdate(sql);

                stmt.close();
                return rc;
            } catch (SQLException e) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                e.printStackTrace();
                logger.error("{}-执行失败", sql);
                return 0;
            }
        }

    }


    /**
     * 将一个数组中的 sql 语句当做一个整体（事务）执行
     * 若执行失败则回滚
     */
    @Deprecated
    public static boolean executeSqlListAtomicity(List<String> sqlList) {
        Statement stmt;
        Connection conn = null;
        try {

            conn = getConnection();
            conn.setAutoCommit(false);

            stmt = conn.createStatement();

            for (String sql : sqlList) {
                stmt.execute(sql);
            }
            conn.commit();
            DbUtils.close(conn);

            return true;

        } catch (SQLException e) {
            e.printStackTrace();

                try {
                    logger.error("执行失败，事务自动回滚");
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            return false;
        }
    }

    /**
     * 批量插入数据
     * @param tableName 目标数据库表
     * @param dataList  要插入的数据
     */
    public static long InsertAll(String tableName, List<Map<String, Object>> dataList) {
        Connection conn = getConnection();
        PreparedStatement preparedStatement = null;
        int affectRowCount = 0;
        try {
            if (dataList.size() == 0) return affectRowCount;

            Map<String, Object> valueMap = dataList.get(0);
            Set<String> keySet = valueMap.keySet();
            Iterator<String> iterator = keySet.iterator();

            //字段名
            StringBuilder columnNameSql = new StringBuilder();
            //占位符
            StringBuilder MarkSql = new StringBuilder();

            Object[] keys = new Object[valueMap.size()];

            int i = 0;
            while (iterator.hasNext()) {
                String key = iterator.next();
                keys[i] = key;
                columnNameSql.append(i == 0 ? "" : ",");
                columnNameSql.append(key);

                MarkSql.append(i == 0 ? "" : ",");
                MarkSql.append("?");
                i++;
            }

            String sql = "INSERT INTO " + tableName + " (" + columnNameSql + " )  VALUES (" + MarkSql + " )";

            //预处理语句。
            preparedStatement = conn.prepareStatement(sql);

            conn.setAutoCommit(false);

            for (Map<String, Object> data : dataList) {

                for (int k = 0; k < keys.length; k++) {

                    preparedStatement.setObject(k + 1, data.get(keys[k]));

                }
                preparedStatement.addBatch();
            }

            int[] arr = preparedStatement.executeBatch();
            conn.commit();

            affectRowCount = arr.length;

            //执行批处理。
            preparedStatement.executeBatch();
            preparedStatement.close();

            DbUtils.close(conn);

            return affectRowCount;

        } catch (SQLException e) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
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
    public static List<?> queryForObjectList(String sql, Class clazz) {

        Connection connection;
        try {
            connection = getConnection();

            QueryRunner queryRunner = new QueryRunner();

            BeanListHandler<?> beanListHandler = new BeanListHandler(clazz);

            List<?> objectList = queryRunner.query(connection, sql, beanListHandler);

            return objectList;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{}-执行失败", sql);
            return new ArrayList<>(0);
        }
    }

    /**
     * 查询数据库，将查询到的每一行放入一个Map中
     * @param sql 查询语句
     * @return  结果集
166  */
    public static List<Map<String, Object>> queryForMapList(String sql) {

        QueryRunner qr = new QueryRunner();

        Connection conn = getConnection();

        try {
            List<Map<String, Object>> list = qr.query(conn, sql, new MapListHandler());

            DbUtils.close(conn);
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("{}-执行失败", sql);
            return null;
        }
    }

    /**
     * 按页查询 ， Page 中的内容格式为 List<Map<String, Object>>
     * @return Page
     */
    public static Page queryForPage(Page page) {

        String sql = "select * from  " + page.getMysqlTable().getTable_name() +
                " order by " + page.getMysqlTable().getIndexColumn() + "  asc ";

        String querysql = getLimitSqlString(sql, page);

        List<Map<String, Object>> list = queryForMapList(querysql);

            page.setPageContent(list);

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

}
