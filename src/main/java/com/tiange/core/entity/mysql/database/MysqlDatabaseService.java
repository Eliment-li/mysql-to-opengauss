package com.tiange.core.entity.mysql.database;

import com.tiange.core.entity.mysql.table.MysqlTableService;

import java.sql.SQLException;

public class MysqlDatabaseService {

    /**
     * 数据库配置
     */
    private DatabaseConfig config;
    private MysqlDatabase database;

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
        Init("mysqltest");
    }

    public MysqlDatabaseService(MysqlDatabase database) {
        this.database = database;
        SQL = SQL.replace("?", "'" + database.getName() + "'");
        this.config = new DatabaseConfig("localhost", 3333, "root", "root123..0", "information_schema");
    }

    /**
     * 初始化
     */

    public void Init(String databaseMame) {
        // String databaseMame = "mysqltest";

    }


    /**
     * 从数据库中读取元数据
     */
    public MysqlDatabase ReadMetaData() throws SQLException {

        //   MySqlDbUtil dbUtil = new MySqlDbUtil(this.config);
        //Init(this.database.getName());
        //database.setName("mysqltest");
        //(MysqlDatabase) dbUtil.queryForObject(SQL, MysqlDatabase.class);

        //读取数据库表
        database.setMysqlTables(new MysqlTableService(database).listTables());


        return database;

    }

    public DatabaseConfig getConfig() {
        return config;
    }

    public void setConfig(DatabaseConfig config) {
        this.config = config;
    }

    public MysqlDatabase getDatabase() {
        return database;
    }

    public void setDatabase(MysqlDatabase database) {
        this.database = database;
    }
}
