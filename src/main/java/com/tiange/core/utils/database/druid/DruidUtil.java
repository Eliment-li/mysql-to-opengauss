package com.tiange.core.utils.database.druid;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.tiange.core.opengauss.column.GaussColumn;
import com.tiange.core.utils.others.FileUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class DruidUtil {
    private static DataSource ds;

    static {

        try {
            Properties pro = new Properties();
            InputStream is = FileUtils.getInputStreamByClasspath("druid/druid.properties");
            pro.load(is);
            ds = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {


        Connection conn = ds.getConnection();
        return conn;
    }

    public static void closeResource(Connection connection, Statement pre) {
        try {
            if (pre != null)
                pre.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            if (connection != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeResource(Connection connection,
                                     Statement pre, ResultSet rs) {
        try {
            if (pre != null)
                pre.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    //测试druid连接池
    public static void main(String[] args) throws Exception {
        QueryRunner queryRunner = new QueryRunner();

        BeanListHandler<?> beanListHandler = new BeanListHandler(GaussColumn.class);
        String sql = "SELECT\n" +
                "\t*\n" +
                "FROM\n" +
                "\tinformation_schema.columns\n" +
                "WHERE\n" +
                "\ttable_name = 'string'\n" +
                "ORDER BY\n" +
                "\tordinal_position;";

        List<?> empList = queryRunner.query(DruidUtil.getConnection(), sql, beanListHandler);

        empList.forEach(e -> System.out.println(e));
    }

}

