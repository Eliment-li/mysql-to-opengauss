package com.tiange.core.entity.mysql;


import com.tiange.core.utils.database.MySqlDbUtil;
import com.tiange.core.utils.others.FileUtils;
import com.tiange.core.utils.others.ObjectUtils;
import com.tiange.core.utils.others.StringUtils;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.tiange.core.entity.mysql.Key.FLAG_PRIMARY;

/**
 * 表结构定义
 */
public class Table implements Serializable {
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

    private List<Column> columns;
    private List<Key> keys;

    private Database database;

    private String TABLE_SQL = FileUtils.getStringByClasspath("mysql/table.sql");
    private String COlUMN_SQL = FileUtils.getStringByClasspath("mysql/column.sql");
    private String KEY_SQL = FileUtils.getStringByClasspath("mysql/key.sql");

    public Table() {
        System.out.println("create table");
    }

    public Table(Database database) {

        this.database = database;
        TABLE_SQL = TABLE_SQL.replace("?", "'" + this.database.getName() + "'");
        COlUMN_SQL = COlUMN_SQL.replace("?", "'" + this.database.getName() + "'");
        KEY_SQL = KEY_SQL.replace("?", "'" + this.database.getName() + "'");
    }

    /**
     * 是否含有外键
     *
     * @return
     */
    public boolean hasForeignKey() {
        for (Key key : this.keys) {
            if (!FLAG_PRIMARY.equals(key.getTable_name())
                    && !StringUtils.isEmpty(key.getReferenced_column_name())
                    && !StringUtils.isEmpty(key.getReferenced_table_name())
                    && !StringUtils.isEmpty(key.getReferenced_table_schema())
                    ) {
                return true;
            }
        }
        return false;
    }


    public String toCreateSql() {
        String sql = TABLE_SQL;
        sql = sql.replace("${tableName}", this.table_name);
        sql = sql.replace("${engine}", " ENGINE = " + this.engine);

        //charset
       /* if (this.getDatabase().getInfo().getIgnoreCharacterCompare()) {
            sql = sql.replace("${charset}", "");
            sql = sql.replace("${collate}", "");
        } else {
            sql = sql.replace("${charset}", " DEFAULT CHARSET = " + this.character_set_name);
            sql = sql.replace("${collate}", " COLLATE = " + this.table_collation);
        }*/

        //comment
        sql = sql.replace("${comment}", StringUtils.isEmpty(this.table_comment) ? "" : " COMMENT = '" + this.table_comment + "'");
        StringBuilder sb = new StringBuilder();
        for (Column column : this.columns) {
            String columnSql = column.toCreateTableSql();
            sb.append(columnSql);
        }

        //todo 优化groupingBy方式，直接根据tableName去查询
        //获取 UNIQUE KEY
        List<Key> uniqueIndexList = this.keys.stream().filter(s -> !FLAG_PRIMARY.equals(s.getConstraint_name()) &&
                StringUtils.isEmpty(s.getReferenced_column_name())).collect(Collectors.toList());

        if (!ObjectUtils.isEmpty(uniqueIndexList)) {
            //通过索引获取tablelist 根据getTable_name来去重  <表名：LIST<key>>
            Map<String, List<Key>> tableList = uniqueIndexList.stream().collect(Collectors.groupingBy(Key::getTable_name));

            if (!ObjectUtils.isEmpty(tableList)) {

                for (Map.Entry<String, List<Key>> e : tableList.entrySet()) {

                    //对每个table中的 key 根据Constraint去中
                    Map<String, List<Key>> listMap = e.getValue().stream().collect(Collectors.groupingBy(Key::getConstraint_name));

                    if (!ObjectUtils.isEmpty(listMap)) {

                        for (Map.Entry<String, List<Key>> j : listMap.entrySet()) {

                            sb.append("UNIQUE KEY `").append(j.getKey()).append("`(");
                            List<String> columnList = j.getValue().stream().map(Key::getColumn_name).collect(Collectors.toList());
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
            this.keys.removeAll(uniqueIndexList);
        }
        // 获取主键
        List<Key> primaryKeyList = this.keys.stream().filter(k -> FLAG_PRIMARY.equals(k.getConstraint_name())).collect(Collectors.toList());
        if (primaryKeyList.size() > 0) {
            sb.append("PRIMARY KEY (");
            primaryKeyList.forEach(k -> sb.append("`").append(k.getColumn_name()).append("`,"));
            sb.delete(sb.length() - 1, sb.length());
            sb.append("),");
        }
        this.keys.removeAll(primaryKeyList);
        for (Key key : this.keys) {
            String keySql = key.toCreateTableSql();
            sb.append(keySql);
        }
        String columnSql = sb.substring(0, sb.length() - 1);
        sql = sql.replace("${columnSql}", columnSql);
        return sql;
    }

    public List<Table> readData() throws SQLException {

        MySqlDbUtil dbUtil = new MySqlDbUtil(this.database.getConfig());

        //todo 把参数加进去
        List<Table> list1 = (List<Table>) dbUtil.queryForBeans(
                TABLE_SQL,
                Table.class);

        List<Column> list2 = (List<Column>) dbUtil.queryForBeans(
                COlUMN_SQL,
                Column.class);

        List<Key> list3 = (List<Key>) dbUtil.queryForBeans(
                KEY_SQL,
                Key.class);

        //(List<Table>)


        List<Table> tabls = list1.stream().peek(o -> {
            o.setDatabase(database);

            o.setColumns(list2.stream().peek(s -> s.setTable(o)).filter(s -> o.getTable_name().equals(s.getTable_name())).collect(Collectors.toList()));

            o.setKeys(list3.stream().peek(s -> s.setTable(o)).filter(s -> o.getTable_name().equals(s.getTable_name())).collect(Collectors.toList()));
        }).collect(Collectors.toList());

        return tabls;
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

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public List<Key> getKeys() {
        return keys;
    }

    public void setKeys(List<Key> keys) {
        this.keys = keys;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

}
