package com.tiange.core.utils.database.druid;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.tiange.core.opengauss.column.GaussColumn;
import com.tiange.core.utils.others.SystemProperties;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

/**
 * 基于 阿里巴巴 Druid
 * 数据库连接池工具
 */
public class DruidUtil {
    private static DataSource mysqlDataSource;
    private static DataSource GaussDataSource;

    //日志工具
    private final Logger logger = LoggerFactory.getLogger(DruidUtil.class);

    //读取配置文件 初始化连接池
    public static boolean init() {
        try {
            System.out.println("初始化连接池 开始");
            //初始化MySQL数据源
            Properties mysqlProperties = SystemProperties.getDruidMysqlProperties();
            mysqlDataSource = DruidDataSourceFactory.createDataSource(mysqlProperties);

            //初始化Opengauss 数据源
            Properties gaussProperties = SystemProperties.getDruidOpengausProperties();
            GaussDataSource = DruidDataSourceFactory.createDataSource(gaussProperties);
            System.out.println("初始化连接池 结束");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * 根据传递的参数，初始化数据库连接池
     * @param mysqlProperties mysql 数据库配置
     * @param gaussProperties opengauss 数据库配置
     * @return
     */
    public static boolean init(Properties mysqlProperties,Properties gaussProperties ) {
        try {
            System.out.println("初始化连接池 开始");
            //初始化MySQL数据源
            //Properties mysqlProperties = SystemProperties.getDruidMysqlProperties();
            mysqlDataSource = DruidDataSourceFactory.createDataSource(mysqlProperties);

            //初始化Opengauss 数据源
            //Properties gaussProperties = SystemProperties.getDruidOpengausProperties();
            GaussDataSource = DruidDataSourceFactory.createDataSource(gaussProperties);
            System.out.println("初始化连接池 结束");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    //获取 mysql 连接
    public static Connection getMysqlConnection() throws Exception {
        Connection conn = mysqlDataSource.getConnection();
        return conn;
    }
    //获取 Opengauss 连接
    public static Connection getGaussConnection() throws Exception {
        Connection conn = GaussDataSource.getConnection();
        return conn;
    }


    //释放连接池资源
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
    //释放连接池资源
    public static void closeResource(Connection connection, Statement pre, ResultSet rs) {
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

        Connection connection = DruidUtil.getMysqlConnection();

        List<?> empList = queryRunner.query(connection, sql, beanListHandler);
        connection.close();
        empList.forEach(e -> System.out.println(e));
    }

}

