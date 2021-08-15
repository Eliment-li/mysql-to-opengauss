package com.tiange.core.utils.database.jdbc;

import com.tiange.core.opengauss.column.GaussColumn;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class DbTest {

    public static void main(String[] args) {

        String driver = "org.postgresql.Driver";
        String sourceURL = "jdbc:postgresql://aa71e16381066044.natapp.cc:12321/postgres";
        String url = sourceURL;
        String username = "jack";
        String password = "root123..0";

        Connection connection = null;


        try {
            connection = DriverManager.getConnection(url, username, password);

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

            List<?> empList = queryRunner.query(connection, sql, beanListHandler);

            empList.forEach(e -> System.out.println(e));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
