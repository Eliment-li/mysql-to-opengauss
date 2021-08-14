package com.tiange.core.opengauss.key;

import com.tiange.core.opengauss.database.GaussDatabase;
import com.tiange.core.utils.others.FileUtils;
import com.tiange.core.utils.others.StringUtils;

public class GaussKey {

    static String CREATE_KEY_SQL = FileUtils.getStringByClasspath("opengauss/metadata/create_key.sql");
    static String CREATE_PRIMARY_KEY_SQL = FileUtils.getStringByClasspath("opengauss/metadata/create_primary_key.sql");
    /* 数据库表名*/
    String table_name;
    /* 数据库名*/
    String table_schema;
    /* 字段名*/
    String column_name;
    String constraint_name;
    String referenced_table_schema;
    String referenced_table_name;
    String referenced_column_name;
    GaussDatabase gaussDatabase;

    public StringBuilder toCreateTableSql() {

        String databaseName = this.getGaussDatabase().getName();
        //获取key sql
        StringBuilder sql = null;
        if (StringUtils.isEmpty(this.referenced_table_schema)
                && StringUtils.isEmpty(this.referenced_table_name)
                && StringUtils.isEmpty(this.referenced_column_name)) {

            if (constraint_name.equals("PRIMARY")) {

                sql = new StringBuilder(CREATE_PRIMARY_KEY_SQL);

                constraint_name = constraint_name + "_" + table_name + "_" + column_name;

                StringUtils.replace("${keyType}", "UNIQUE", sql);
                StringUtils.replace("${keyName}", constraint_name, sql);
                StringUtils.replace("${databaseName}", databaseName, sql);
                StringUtils.replace("${tableName}", table_name, sql);
                StringUtils.replace("${columnName}", column_name, sql);
            } else {
                // 唯一索引
                sql = new StringBuilder(CREATE_KEY_SQL);
                if (constraint_name.equals("PRIMARY"))
                    constraint_name = constraint_name + column_name;

                StringUtils.replace("${keyType}", "UNIQUE", sql);
                StringUtils.replace("${keyName}", constraint_name, sql);
                StringUtils.replace("${databaseName}", databaseName, sql);
                StringUtils.replace("${tableName}", table_name, sql);
                StringUtils.replace("${columnName}", column_name, sql);
            }


        } else {
            //todo  处理外键，联合主键等类型的索引
           /*
            sb.append("CONSTRAINT `").append(this.constraint_name).append("` ")
                    .append("FOREIGN KEY (`").append(this.column_name).append("`) ")
                    .append("REFERENCES `").append(this.referenced_table_name).append("` ")
                    .append("(`").append(this.referenced_column_name).append("`),");*/
        }
        return sql;
    }

    public String getReferenced_table_schema() {
        return referenced_table_schema;
    }


    /* getter & setter */

    public void setReferenced_table_schema(String referenced_table_schema) {
        this.referenced_table_schema = referenced_table_schema;
    }

    public String getReferenced_table_name() {
        return referenced_table_name;
    }

    public void setReferenced_table_name(String referenced_table_name) {
        this.referenced_table_name = referenced_table_name;
    }

    public String getReferenced_column_name() {
        return referenced_column_name;
    }

    public void setReferenced_column_name(String referenced_column_name) {
        this.referenced_column_name = referenced_column_name;
    }

    public String getConstraint_name() {
        return constraint_name;
    }

    public void setConstraint_name(String constraint_name) {
        this.constraint_name = constraint_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public GaussDatabase getGaussDatabase() {
        return gaussDatabase;
    }

    public void setGaussDatabase(GaussDatabase gaussDatabase) {
        this.gaussDatabase = gaussDatabase;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getTable_schema() {
        return table_schema;
    }

    public void setTable_schema(String table_schema) {
        this.table_schema = table_schema;
    }

    public enum KeyType {
        PRIMARY, FOREIGN, UNIQUE
    }
}
