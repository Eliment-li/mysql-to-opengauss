package com.tiange.core.entity.mysql;

import com.tiange.core.entity.mysql.table.MysqlTable;
import com.tiange.core.utils.others.StringUtils;

import java.io.Serializable;

/**
 * Key结构定义
 */
public class Key implements Serializable {
    private static final long serialVersionUID = 3145627924529636485L;

    public static final String FLAG_PRIMARY = "PRIMARY";

    private String constraint_schema;
    private String constraint_name;
    private String table_name;
    private String column_name;
    private Long ordinal_position;
    private Long position_in_unique_constraint;
    private String referenced_table_schema;
    private String referenced_table_name;
    private String referenced_column_name;

    private MysqlTable mysqlTable;

    public enum KeyType {
        PRIMARY, FOREIGN, UNIQUE
    }

    public KeyType getKeyType() {
        if ("PRIMARY".equals(this.getTable_name())) {
            return KeyType.PRIMARY;
        } else if (this.getReferenced_table_schema() != null || this.getReferenced_table_name() != null || this.getReferenced_column_name() != null) {
            return KeyType.FOREIGN;
        } else {
            return KeyType.UNIQUE;
        }
    }


    public String toCreateTableSql() {
        //获取key sql
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isEmpty(this.referenced_table_schema)
                && StringUtils.isEmpty(this.referenced_table_name)
                && StringUtils.isEmpty(this.referenced_column_name)) {
            // 唯一索引
            sb.append("CONSTRAINT `").append(this.constraint_name).append("` UNIQUE (`").append(this.column_name).append("`),");
        } else {
            // 外键
            sb.append("CONSTRAINT `").append(this.constraint_name).append("` ")
                    .append("FOREIGN KEY (`").append(this.column_name).append("`) ")
                    .append("REFERENCES `").append(this.referenced_table_name).append("` ")
                    .append("(`").append(this.referenced_column_name).append("`),");
        }
        return sb.toString();
    }



    //getter&setter

    public static String getFlagPrimary() {
        return FLAG_PRIMARY;
    }

    public String getConstraint_schema() {
        return constraint_schema;
    }

    public void setConstraint_schema(String constraint_schema) {
        this.constraint_schema = constraint_schema;
    }

    public String getConstraint_name() {
        return constraint_name;
    }

    public void setConstraint_name(String constraint_name) {
        this.constraint_name = constraint_name;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public Long getOrdinal_position() {
        return ordinal_position;
    }

    public void setOrdinal_position(Long ordinal_position) {
        this.ordinal_position = ordinal_position;
    }

    public Long getPosition_in_unique_constraint() {
        return position_in_unique_constraint;
    }

    public void setPosition_in_unique_constraint(Long position_in_unique_constraint) {
        this.position_in_unique_constraint = position_in_unique_constraint;
    }

    public String getReferenced_table_schema() {
        return referenced_table_schema;
    }

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

    public MysqlTable getMysqlTable() {
        return mysqlTable;
    }

    public void setMysqlTable(MysqlTable mysqlTable) {
        this.mysqlTable = mysqlTable;
    }
}
