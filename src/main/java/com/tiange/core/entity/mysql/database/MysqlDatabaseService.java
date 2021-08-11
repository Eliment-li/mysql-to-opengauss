package com.tiange.core.entity.mysql.database;

import com.tiange.core.entity.mysql.table.MysqlTable;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;

import java.sql.SQLException;

public class MysqlDatabaseService {

    /**
     * 数据库配置
     */
    private DatabaseConfig config;


    private String SQL = "SELECT CONSTRAINT_CATALOG," +
            "       CONSTRAINT_SCHEMA," +
            "       CONSTRAINT_NAME," +
            "       TABLE_CATALOG," +
            "       TABLE_SCHEMA," +
            "       TABLE_NAME," +
            "       COLUMN_NAME," +
            "       ORDINAL_POSITION," +
            "       POSITION_IN_UNIQUE_CONSTRAINT," +
            "       REFERENCED_TABLE_SCHEMA," +
            "       REFERENCED_TABLE_NAME," +
            "       REFERENCED_COLUMN_NAME" +
            " FROM   KEY_COLUMN_USAGE " +
            " WHERE TABLE_SCHEMA = ? ";


    public MysqlDatabaseService() {
        Init();
    }

    public MysqlDatabaseService(DatabaseConfig config) {
        this.config = config;
    }

    /**
     * 初始化
     */

    public void Init() {
        String databaseMame = "mysqltest";
        SQL = SQL.replace("?", "'" + databaseMame + "'");
        this.config = new DatabaseConfig("localhost", 3333, "root", "root123..0", "information_schema");
    }


    /**
     * 从数据库中读取数据
     */
    public MysqlDatabase ReadMetaData() throws SQLException {

        MySqlDbUtil dbUtil = new MySqlDbUtil(this.config);

        MysqlDatabase mysqlDatabase = (MysqlDatabase) dbUtil.queryForObject(SQL, MysqlDatabase.class);

        //读取数据库表
        mysqlDatabase.setMysqlTables(new MysqlTable(mysqlDatabase).readData());

        //输出建表语句
        System.out.println(mysqlDatabase.getMysqlTables().get(0).toCreateSql());

        return mysqlDatabase;

    }

    public DatabaseConfig getConfig() {
        return config;
    }

    public void setConfig(DatabaseConfig config) {
        this.config = config;
    }
}
