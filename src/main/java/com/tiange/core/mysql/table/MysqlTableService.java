package com.tiange.core.mysql.table;

import com.tiange.core.mysql.MysqlColumn;
import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.mysql.key.MysqlKey;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;
import com.tiange.core.utils.others.FileUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MysqlTableService {

    private static String TABLE_SQL = FileUtils.getStringByClasspath("mysql/table.sql");
    private static String COlUMN_SQL = FileUtils.getStringByClasspath("mysql/column.sql");
    private static String KEY_SQL = FileUtils.getStringByClasspath("mysql/key.sql");
    MysqlDatabase mysqlDatabase;
    private String CREATE_TABLE_SQL = FileUtils.getStringByClasspath("opengauss/metadata/create_table.sql");


    public MysqlTableService(String dataBaseName) {

        TABLE_SQL = TABLE_SQL.replace("?", "'" + dataBaseName + "'");
        COlUMN_SQL = COlUMN_SQL.replace("?", "'" + dataBaseName + "'");
        KEY_SQL = KEY_SQL.replace("?", "'" + dataBaseName + "'");
    }

    public MysqlTableService(MysqlDatabase database) {
        this.mysqlDatabase = database;
        TABLE_SQL = TABLE_SQL.replace("?", "'" + database.getName() + "'");
        COlUMN_SQL = COlUMN_SQL.replace("?", "'" + database.getName() + "'");
        KEY_SQL = KEY_SQL.replace("?", "'" + database.getName() + "'");
    }


    /*获取该数据库下所有的表*/
    public List<MysqlTable> listTables() throws SQLException {
        String databaseName = mysqlDatabase.getName();

        TABLE_SQL = TABLE_SQL.replace("?", "'" + databaseName + "'");
        COlUMN_SQL = COlUMN_SQL.replace("?", "'" + databaseName + "'");
        KEY_SQL = KEY_SQL.replace("?", "'" + databaseName + "'");

        MySqlDbUtil dbUtil = new MySqlDbUtil();

        //todo 把参数加进去
        List<MysqlTable> tableList = (List<MysqlTable>) dbUtil.queryForBeans(
                TABLE_SQL,
                MysqlTable.class);

        List<MysqlColumn> columnList = (List<MysqlColumn>) dbUtil.queryForBeans(
                COlUMN_SQL,
                MysqlColumn.class);

        List<MysqlKey> mysqlKeyList = (List<MysqlKey>) dbUtil.queryForBeans(
                KEY_SQL,
                MysqlKey.class);


        //填充tables
        List<MysqlTable> tabls = tableList.stream().peek(o -> {
            //填充数据库
            o.setMysqlDatabase(mysqlDatabase);
            //填充columns
            o.setMysqlColumns(columnList.stream().peek(s -> s.setMysqlTable(o)).filter(s -> o.getTable_name().equals(s.getTable_name())).collect(Collectors.toList()));
            //填充keys
            o.setMysqlKeys(mysqlKeyList.stream().peek(s -> s.setMysqlTable(o)).filter(s -> o.getTable_name().equals(s.getTable_name())).collect(Collectors.toList()));
        }).collect(Collectors.toList());

        return tabls;
    }
}
