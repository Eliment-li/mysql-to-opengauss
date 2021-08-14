package com.tiange.core.mysql.database;

import com.tiange.core.mysql.key.MysqlKey;
import com.tiange.core.mysql.key.MysqlKeyService;
import com.tiange.core.mysql.table.MysqlTable;
import com.tiange.core.mysql.table.MysqlTableService;
import com.tiange.core.opengauss.database.GaussDatabase;
import com.tiange.core.opengauss.key.GaussKey;
import com.tiange.core.opengauss.table.GaussTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlDatabaseService {

    /**
     * 数据库配置
     */
    private DatabaseConfig config;
    private MysqlDatabase mysqlDatabase;

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
        this.mysqlDatabase = database;
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
     * 将 mysql 数据库实体类 转换为 OpenGauss 数据库实体类
     * @param mysqlDatabase
     * @param gaussDatabase
     * @return
     */
    public static GaussDatabase Conert2GaussDatabase(MysqlDatabase mysqlDatabase, GaussDatabase gaussDatabase) {


        List<GaussTable> gaussTables = new ArrayList<>();

        //Tables
        for (MysqlTable mysqlTable : mysqlDatabase.getMysqlTables()) {

            GaussTable gaussTable = mysqlTable.toOpenGaussTable();

            gaussTable.setGaussDatabase(gaussDatabase);

            gaussTables.add(gaussTable);

        }
        gaussDatabase.setTables(gaussTables);

        //keys
        List<GaussKey> gaussKeyList = new ArrayList<>();
        for (MysqlKey mysqlKey : mysqlDatabase.getMysqlKeys()) {

            GaussKey gaussKey = mysqlKey.toGaussKey();

            gaussKey.setGaussDatabase(gaussDatabase);

            gaussKeyList.add(gaussKey);

        }
        gaussDatabase.setTables(gaussTables);

        gaussDatabase.setKeys(gaussKeyList);

        return gaussDatabase;
    }

    /**
     * 从数据库中读取元数据
     */
    public MysqlDatabase ReadMetaData() throws SQLException {


        //读取数据库表
        mysqlDatabase.setMysqlTables(new MysqlTableService(mysqlDatabase).listTables());

        mysqlDatabase.setMysqlKeys(new MysqlKeyService().listKeys(mysqlDatabase.getName()));


        return mysqlDatabase;

    }



    public DatabaseConfig getConfig() {
        return config;
    }

    public void setConfig(DatabaseConfig config) {
        this.config = config;
    }

    public MysqlDatabase getMysqlDatabase() {
        return mysqlDatabase;
    }

    public void setMysqlDatabase(MysqlDatabase mysqlDatabase) {
        this.mysqlDatabase = mysqlDatabase;
    }
}
