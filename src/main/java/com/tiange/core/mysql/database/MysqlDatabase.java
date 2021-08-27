package com.tiange.core.mysql.database;

import com.tiange.core.mysql.MysqlFunction;
import com.tiange.core.mysql.MysqlProcedure;
import com.tiange.core.mysql.MysqlTrigger;
import com.tiange.core.mysql.MysqlView;
import com.tiange.core.mysql.key.MysqlKey;
import com.tiange.core.mysql.table.MysqlTable;

import java.sql.SQLException;
import java.util.List;

/**
 * 数据库结构定义
 */
public class MysqlDatabase {


    //SCHEMA_NAME
    //DEFAULT_CHARACTER_SET_NAME
    //DEFAULT_COLLATION_NAME
    private String name;
    private String character;
    private String collate;
    /**
     * 包含的tables
     */
    private List<MysqlTable> mysqlTables;

    /**
     * 包含的tables
     */
    private List<MysqlKey> mysqlKeys;

    /**
     * 包含的views
     */
    private List<MysqlView> mysqlViews;
    /**
     * 包含的functions
     */
    private List<MysqlFunction> mysqlFunctions;
    /**
     * 包含的procedures
     */
    private List<MysqlProcedure> mysqlProcedures;
    /**
     * 包含的triggers
     */
    private List<MysqlTrigger> mysqlTriggers;

    public MysqlDatabase(String name) {
        this.name = name;
    }


    /**
     * 配置数据库结构
     *
     * @return 数据库结构
     * @throws SQLException SQL异常
     */
/*    private MysqlDatabase configure() throws SQLException {
        MysqlDatabase bean = DbUtil.getBean(this.connection, SQL, MysqlDatabase.class, this.info.getName());
        bean.setMysqlTables(MysqlTable.configure(this.connection, this));
        bean.setMysqlViews(MysqlView.configure(this.connection, this));
        bean.setMysqlFunctions(MysqlFunction.configure(this.connection, this));
        bean.setMysqlProcedures(MysqlProcedure.configure(this.connection, this));
        bean.setMysqlTriggers(MysqlTrigger.configure(this.connection, this));
        bean.setInfo(this.info);
        bean.setConnection(this.connection);
        return bean;
    }*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCollate() {
        return collate;
    }

    public void setCollate(String collate) {
        this.collate = collate;
    }

    public List<MysqlTable> getMysqlTables() {
        return mysqlTables;
    }

    public void setMysqlTables(List<MysqlTable> mysqlTables) {
        this.mysqlTables = mysqlTables;
    }

    public List<MysqlView> getMysqlViews() {
        return mysqlViews;
    }

    public void setMysqlViews(List<MysqlView> mysqlViews) {
        this.mysqlViews = mysqlViews;
    }

    public List<MysqlFunction> getMysqlFunctions() {
        return mysqlFunctions;
    }

    public void setMysqlFunctions(List<MysqlFunction> mysqlFunctions) {
        this.mysqlFunctions = mysqlFunctions;
    }

    public List<MysqlProcedure> getMysqlProcedures() {
        return mysqlProcedures;
    }

    public void setMysqlProcedures(List<MysqlProcedure> mysqlProcedures) {
        this.mysqlProcedures = mysqlProcedures;
    }

    public List<MysqlTrigger> getMysqlTriggers() {
        return mysqlTriggers;
    }

    public void setMysqlTriggers(List<MysqlTrigger> mysqlTriggers) {
        this.mysqlTriggers = mysqlTriggers;
    }

    public List<MysqlKey> getMysqlKeys() {
        return mysqlKeys;
    }

    public void setMysqlKeys(List<MysqlKey> mysqlKeys) {
        this.mysqlKeys = mysqlKeys;
    }
}
