package com.tiange.core.utils.database.jdbc;

import java.sql.*;

public class OpenGaussDbUtil {

    //创建数据库连接。
    public static Connection GetConnection(String username, String passwd) {
        String driver = "org.postgresql.Driver";
        String sourceURL = "jdbc:postgresql://aa71e16381066044.natapp.cc:12321/postgres";
        Connection conn = null;
        try {
            //加载数据库驱动。
            Class.forName(driver).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
            //创建数据库连接。
            conn = DriverManager.getConnection(sourceURL, username, passwd);
            System.out.println("Connection succeed!");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return conn;
    }

    ;

    public static int execute(String sql) {
        {
            Statement stmt = null;
            try {
                stmt = GetConnection("jack", "root123..0").createStatement();

                //执行普通SQL语句。
                int rc = stmt
                        .executeUpdate(sql);

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

    //执行普通SQL语句，创建customer_t1表。
    public static void CreateTable(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            //执行普通SQL语句。
            int rc = stmt
                    .executeUpdate("CREATE TABLE  \"test1\" (c_customer_sk INTEGER, c_customer_name VARCHAR(32));");

            stmt.close();
        } catch (SQLException e) {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    //执行预处理语句，批量插入数据。
    public static void BatchInsertData(Connection conn) {
        PreparedStatement pst = null;

        try {
//生成预处理语句。
            pst = conn.prepareStatement("INSERT INTO test1 VALUES (?,?)");
            for (int i = 0; i < 3; i++) {
                //添加参数。
                pst.setInt(1, i);
                pst.setString(2, "data " + i);
                pst.addBatch();
            }
//执行批处理。
            pst.executeBatch();
            pst.close();
        } catch (SQLException e) {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    //执行预编译语句，更新数据。
    public static void ExecPreparedSQL(Connection conn) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn
                    .prepareStatement("UPDATE test1 SET c_customer_name = ? WHERE c_customer_sk = 1");
            pstmt.setString(1, "new Data");
            int rowcount = pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException e) {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }


    /**
     * 主程序，逐步调用各静态方法。
     *
     * @param args
     */
    public static void main(String[] args) {
//创建数据库连接。
        Connection conn = GetConnection("jack", "root123..0");
        CreateTable(conn);//创建表。
        //批插数据。
        BatchInsertData(conn);
        //执行预编译语句，更新数据。
        ExecPreparedSQL(conn);


//关闭数据库连接。
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
