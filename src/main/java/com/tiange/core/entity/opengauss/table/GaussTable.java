package com.tiange.core.entity.opengauss.table;

import com.tiange.core.entity.opengauss.column.GaussColumn;
import com.tiange.core.utils.others.FileUtils;

import java.util.List;

public class GaussTable {

    String table_schema;
    String table_name;
    String table_type;

    String character_set_name;
    String table_collation;
    String table_comment;

    private String CREATE_TABLE_SQL = FileUtils.getStringByClasspath("mysql/create_table.sql");

    private List<GaussColumn> gaussColumns;
//private List<Key> keys;

    public StringBuilder toCreateSql() {
        //将长度设置的大一点，减少 StringBuilder 扩容次数
        StringBuilder sql = new StringBuilder(CREATE_TABLE_SQL);

        // openGauss 的表名前要加上模式名  例如 "jack"."test";
        String tableName = "\"public\"." + "\"" + this.table_name + "\"";

        //替换表名
        int index = sql.indexOf("${tableName}");

        sql = sql.replace(index, index + "${tableName}".length(), tableName);

        return sql;
    }

    /* getter & setter */

    public String getTable_schema() {
        return table_schema;
    }

    public void setTable_schema(String table_schema) {
        this.table_schema = table_schema;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_type() {
        return table_type;
    }

    public void setTable_type(String table_type) {
        this.table_type = table_type;
    }

    public String getCharacter_set_name() {
        return character_set_name;
    }

    public void setCharacter_set_name(String character_set_name) {
        this.character_set_name = character_set_name;
    }

    public String getTable_collation() {
        return table_collation;
    }

    public void setTable_collation(String table_collation) {
        this.table_collation = table_collation;
    }

    public String getTable_comment() {
        return table_comment;
    }

    public void setTable_comment(String table_comment) {
        this.table_comment = table_comment;
    }

    public List<GaussColumn> getGaussColumns() {
        return gaussColumns;
    }

    public void setGaussColumns(List<GaussColumn> gaussColumns) {
        this.gaussColumns = gaussColumns;
    }
}
