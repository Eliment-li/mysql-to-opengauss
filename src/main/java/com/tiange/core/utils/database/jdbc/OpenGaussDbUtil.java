package com.tiange.core.utils.database.jdbc;

import com.tiange.core.utils.database.druid.DruidUtil;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class OpenGaussDbUtil {
    //创建数据库连接。
    public static Connection GetConnection() {

        Connection conn = null;
        try {
            //从线程池获取连接
            conn = DruidUtil.getGaussConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return conn;
    }

    ;

    /**
     * 执行单个增删查改语句
     *
     * @param sql
     * @return
     */
    public static int execute(String sql) {
        {
            Statement stmt = null;
            try {
                stmt = GetConnection().createStatement();

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
                return 0;
            }
        }

    }


    /**
     * 将一个数组中的 sql 语句当做一个整体（事务）执行
     * 若执行失败则回滚
     */
    public static boolean executeSqlListAtomicity(List<String> sqlList) {
        Statement stmt = null;
        Connection conn = null;
        try {

            conn = GetConnection();
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
            //事务回滚
            if (conn != null) {
                try {
                    System.err.print("Transaction is being rolled back");
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        }
    }

    //执行预处理语句，批量插入数据。
    public static void InsertAll(String tableName, List<Map<String, Object>> dataList) {
        Connection conn = GetConnection();
        PreparedStatement preparedStatement = null;

        try {
            if (dataList.size() == 0) return;

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

            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ");
            sql.append(tableName);
            sql.append(" (");
            sql.append(columnNameSql);
            sql.append(" )  VALUES (");
            sql.append(MarkSql);
            sql.append(" )");


            //生成预处理语句。
            preparedStatement = conn.prepareStatement(sql.toString());

            conn.setAutoCommit(false);

            for (int j = 0; j < dataList.size(); j++) {
                for (int k = 0; k < keys.length; k++) {
                    preparedStatement.setObject(k + 1, dataList.get(j).get(keys[k]));
                }
                preparedStatement.addBatch();
            }

            int[] arr = preparedStatement.executeBatch();
            conn.commit();
            int affectRowCount = arr.length;


            //执行批处理。
            preparedStatement.executeBatch();
            preparedStatement.close();

            DbUtils.close(conn);
        } catch (SQLException e) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }


    /**
     * 使用DButils 实现
     *
     * @param sql
     * @param clazz
     * @return
     */
    public static List<?> queryForObjectList(String sql, Class clazz) {

        Connection connection = null;
        try {
            connection = GetConnection();

            QueryRunner queryRunner = new QueryRunner();

            BeanListHandler<?> beanListHandler = new BeanListHandler(clazz);
            List<?> objectList = queryRunner.query(connection, sql, beanListHandler);

            return objectList;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(0);
        }
    }

    /*
163  *  MapListHandler
164  *  将结果集每一行存储到Map集合,键:列名,值:数据
166  */
    public static List<Map<String, Object>> queryForMapList(String sql) throws SQLException {

        QueryRunner qr = new QueryRunner();

        Connection conn = GetConnection();

        List<Map<String, Object>> list = qr.query(conn, sql, new MapListHandler());

        DbUtils.close(conn);

        return list;
    }

    /**
     * 按页查询 Page 中的内容格式为 List<Map<String, Object>>
     *
     * @return Page
     */
    public static Page queryForPage(Page page) {

        StringBuilder sql = new StringBuilder("select * from  " + page.getMysqlTable().getTable_name() + " order by " + page.getMysqlTable().getIndexColumn() + "  asc ");
        try {
            String querysql = getLimitSqlString(sql.toString(), page);
            System.out.println(querysql);
            List<Map<String, Object>> list = queryForMapList(querysql);

            page.setPageContent(list);

        } catch (SQLException e) {

            e.printStackTrace();
            System.out.println("第" + page.getPageNum() + "页 " + "查询失败");
        }
        return page;
    }

    /**
     * 将sql变成分页sql语句
     *
     * @return
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
     * 主程序，逐步调用各静态方法。
     *
     * @param args
     */
    public static void main(String[] args) {
//创建数据库连接。
        Connection conn = GetConnection();
        //批插数据。
        //BatchInsertData(conn);
        //执行预编译语句，更新数据。
        //ExecPreparedSQL(conn);

        //关闭数据库连接。
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
