package com.tiange.core.entity.mysql.database;

import com.tiange.core.entity.mysql.Function;
import com.tiange.core.entity.mysql.Procedure;
import com.tiange.core.entity.mysql.Trigger;
import com.tiange.core.entity.mysql.View;
import com.tiange.core.entity.mysql.table.MysqlTable;

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
     * 包含的views
     */
    private List<View> views;
    /**
     * 包含的functions
     */
    private List<Function> functions;
    /**
     * 包含的procedures
     */
    private List<Procedure> procedures;
    /**
     * 包含的triggers
     */
    private List<Trigger> triggers;

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
        bean.setViews(View.configure(this.connection, this));
        bean.setFunctions(Function.configure(this.connection, this));
        bean.setProcedures(Procedure.configure(this.connection, this));
        bean.setTriggers(Trigger.configure(this.connection, this));
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

    public List<View> getViews() {
        return views;
    }

    public void setViews(List<View> views) {
        this.views = views;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public void setTriggers(List<Trigger> triggers) {
        this.triggers = triggers;
    }
}
