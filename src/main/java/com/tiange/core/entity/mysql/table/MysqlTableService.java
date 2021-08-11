package com.tiange.core.entity.mysql.table;

import com.tiange.core.entity.mysql.Key;
import com.tiange.core.entity.mysql.MysqlColumn;
import com.tiange.core.entity.mysql.database.MysqlDatabase;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;
import com.tiange.core.utils.others.FileUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class MysqlTableService {

    MysqlDatabase mysqlDatabase;
    private String TABLE_SQL = FileUtils.getStringByClasspath("mysql/table.sql");
    private String COlUMN_SQL = FileUtils.getStringByClasspath("mysql/column.sql");
    private String KEY_SQL = FileUtils.getStringByClasspath("mysql/key.sql");

    private String CREATE_TABLE_SQL = FileUtils.getStringByClasspath("mysql/create_table.sql");


    public MysqlTableService(String dataBaseName) {

        TABLE_SQL = TABLE_SQL.replace("?", "'" + dataBaseName + "'");
        COlUMN_SQL = COlUMN_SQL.replace("?", "'" + dataBaseName + "'");
        KEY_SQL = KEY_SQL.replace("?", "'" + dataBaseName + "'");
    }

    public MysqlTableService(MysqlDatabase database) {

        TABLE_SQL = TABLE_SQL.replace("?", "'" + database.getName() + "'");
        COlUMN_SQL = COlUMN_SQL.replace("?", "'" + database.getName() + "'");
        KEY_SQL = KEY_SQL.replace("?", "'" + database.getName() + "'");
    }


    /*获取该数据库下所有的表*/
    public List<MysqlTable> listTables(String databaseName) throws SQLException {

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

        List<Key> keyList = (List<Key>) dbUtil.queryForBeans(
                KEY_SQL,
                Key.class);


        //填充tables
        List<MysqlTable> tabls = tableList.stream().peek(o -> {
            //填充数据库
            o.setMysqlDatabase(mysqlDatabase);
            //填充columns
            o.setMysqlColumns(columnList.stream().peek(s -> s.setMysqlTable(o)).filter(s -> o.getTable_name().equals(s.getTable_name())).collect(Collectors.toList()));
            //填充keys
            o.setKeys(keyList.stream().peek(s -> s.setMysqlTable(o)).filter(s -> o.getTable_name().equals(s.getTable_name())).collect(Collectors.toList()));
        }).collect(Collectors.toList());

        return tabls;
    }
}
