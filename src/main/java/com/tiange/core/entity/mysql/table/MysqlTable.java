package com.tiange.core.entity.mysql.table;


import com.tiange.core.entity.mysql.MysqlColumn;
import com.tiange.core.entity.mysql.database.MysqlDatabase;
import com.tiange.core.entity.mysql.key.MysqlKey;
import com.tiange.core.entity.opengauss.column.GaussColumn;
import com.tiange.core.entity.opengauss.table.GaussTable;
import com.tiange.core.utils.others.ObjectUtils;
import com.tiange.core.utils.others.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tiange.core.entity.mysql.key.MysqlKey.FLAG_PRIMARY;

/**
 * 表结构定义
 */
public class MysqlTable implements Serializable {
    private static final long serialVersionUID = 1108952612345752323L;

    private String table_schema;
    private String table_name;
    private String table_type;
    private String engine;
    private Timestamp create_time;
    private Timestamp update_time;
    private String character_set_name;
    private String table_collation;
    private String table_comment;

    private List<MysqlColumn> mysqlColumns;
    private List<MysqlKey> mysqlKeys;

    private MysqlDatabase mysqlDatabase;


    public MysqlTable() {

    }

    public MysqlTable(MysqlDatabase mysqlDatabase) {

        this.mysqlDatabase = mysqlDatabase;

    }

    /**
     * Mysql Table 转换成 Opengauss Table
     *
     * @return
     */
    public GaussTable toOpenGaussTable() {
        GaussTable gaussTable = new GaussTable();

        gaussTable.setTable_name(this.table_name);
        gaussTable.setTable_schema(this.table_schema);
        gaussTable.setTable_type(this.table_type);

        //set columns
        gaussTable.setGaussColumns(this.listGaussColumns(gaussTable));

        return gaussTable;

    }

    /**
     * 将 mysql 的 columns 转换成 openGauss 的 columns
     *
     * @return
     */
    private List<GaussColumn> listGaussColumns(GaussTable gaussTable) {

        List<GaussColumn> gaussColumns = new ArrayList<>();

        mysqlColumns.forEach(e -> {
            GaussColumn gaussColumn = e.toGaussColumn();
            gaussColumn.setGaussTable(gaussTable);
            gaussColumns.add(gaussColumn);
        });

        return gaussColumns;
    }

    /**
     * 是否含有外键
     *
     * @return
     */
    public boolean hasForeignKey() {
        for (MysqlKey mysqlKey : this.mysqlKeys) {
            if (!FLAG_PRIMARY.equals(mysqlKey.getTable_name())
                    && !StringUtils.isEmpty(mysqlKey.getReferenced_column_name())
                    && !StringUtils.isEmpty(mysqlKey.getReferenced_table_name())
                    && !StringUtils.isEmpty(mysqlKey.getReferenced_table_schema())
                    ) {
                return true;
            }
        }
        return false;
    }


    /**
     * @return 创建数据库表的Sql语句
     */
    public String toCreateSql() {

        String sql = "";

        // openGauss 的表名前要加上模式名  例如 "jack"."test";
        String tableName = "\"public\"." + "\"" + this.table_name + "\"";
        sql = sql.replace("${tableName}", tableName);

        //engine


        //charset


        //comment
        String comment = toOpenGausscomment(this.table_comment);

        sql = sql.replace("${comment}", comment);

        //建表语句
        StringBuilder sb = new StringBuilder();

        //去除最后一个逗号
        sb.deleteCharAt(sb.length() - 1);

        //todo 优化groupingBy方式，直接根据tableName去查询


        //获取 unique 索引
        List<MysqlKey> uniqueIndexList = this.mysqlKeys.stream().filter(
                s -> !FLAG_PRIMARY.equals(s.getConstraint_name()) &&
                        StringUtils.isEmpty(s.getReferenced_column_name())
        ).collect(Collectors.toList());

        if (!ObjectUtils.isEmpty(uniqueIndexList)) {

            //根据getTable_name来去重  <表名：LIST<key>>

            Map<String, List<MysqlKey>> tableList = uniqueIndexList.stream().collect(Collectors.groupingBy(MysqlKey::getTable_name));

            if (!ObjectUtils.isEmpty(tableList)) {

                for (Map.Entry<String, List<MysqlKey>> e : tableList.entrySet()) {

                    //对每个table中的 key 根据Constraint去重
                    Map<String, List<MysqlKey>> listMap = e.getValue().stream().collect(Collectors.groupingBy(MysqlKey::getConstraint_name));

                    if (!ObjectUtils.isEmpty(listMap)) {

                        for (Map.Entry<String, List<MysqlKey>> j : listMap.entrySet()) {

                            sb.append("UNIQUE KEY `").append(j.getKey()).append("`(");

                            List<String> columnList = j.getValue().stream()
                                    .map(MysqlKey::getColumn_name)
                                    .collect(Collectors.toList());

                            if (!ObjectUtils.isEmpty(columnList)) {
                                for (String s : columnList) {
                                    sb.append("`").append(s).append("`, ");
                                }
                            }
                            sb.delete(sb.length() - 2, sb.length());
                            sb.append("),");
                        }
                    }
                }
            }
            this.mysqlKeys.removeAll(uniqueIndexList);
        }
        // 获取主键
        List<MysqlKey> primaryMysqlKeyList = this.mysqlKeys.stream().filter(k -> FLAG_PRIMARY.equals(k.getConstraint_name())).collect(Collectors.toList());
        if (primaryMysqlKeyList.size() > 0) {
            sb.append("PRIMARY KEY (");
            primaryMysqlKeyList.forEach(k -> sb.append("`").append(k.getColumn_name()).append("`,"));
            sb.delete(sb.length() - 1, sb.length());
            sb.append("),");
        }
        this.mysqlKeys.removeAll(primaryMysqlKeyList);
        for (MysqlKey mysqlKey : this.mysqlKeys) {
            //todo
            // String keySql = mysqlKey.toCreateTableSql();
            // sb.append(keySql);
        }
        String columnSql = sb.substring(0, sb.length() - 1);
        sql = sql.replace("${columnSql}", columnSql);
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

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
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

    public List<MysqlColumn> getMysqlColumns() {
        return mysqlColumns;
    }

    public void setMysqlColumns(List<MysqlColumn> mysqlColumns) {
        this.mysqlColumns = mysqlColumns;
    }

    public List<MysqlKey> getMysqlKeys() {
        return mysqlKeys;
    }

    public void setMysqlKeys(List<MysqlKey> mysqlKeys) {
        this.mysqlKeys = mysqlKeys;
    }

    public MysqlDatabase getMysqlDatabase() {
        return mysqlDatabase;
    }

    public void setMysqlDatabase(MysqlDatabase mysqlDatabase) {
        this.mysqlDatabase = mysqlDatabase;
    }

    // convertt
    private String toOpenGausscomment(String table_comment) {

        if (StringUtils.isEmpty(table_comment))
            return "";
        else
            return " COMMENT ON TABLE \"public\".\"" + this.table_name + "\" IS '" + table_comment + "' ";
    }
}
