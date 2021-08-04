package com.tiange.core.entity.mysql;


import com.tiange.core.utils.database.jdbc.MySqlDbUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * 数据库结构定义
 */
public class Database {
    /**
     * 数据库配置
     */
    private DatabaseConfig config;

    //SCHEMA_NAME
    //DEFAULT_CHARACTER_SET_NAME
    //DEFAULT_COLLATION_NAME
    private String name;
    private String character;
    private String collate;
    /**
     * 包含的tables
     */
    private List<Table> tables;
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


    /**
     * 初始化
     */
    public void Init(DatabaseConfig config) {
        this.config = config;
    }

    public void Init() {
        this.name = "mysqltest";
        SQL = SQL.replace("?", "'" + this.name + "'");
        this.config = new DatabaseConfig("localhost", 3333, "root", "root123..0", "information_schema");
    }

    /**
     * 从数据库中读取数据
     */
    public Database ReadData() throws SQLException {

        MySqlDbUtil dbUtil = new MySqlDbUtil(this.config);

        Database database = (Database) dbUtil.queryForObject(SQL, Database.class);

        database.Init();

        //读取数据库表
        database.setTables(new Table(this).readData());
        System.out.println(database.getTables().get(3).toCreateSql());

        return database;

    }

    /**
     * 初始化数据库结构
     *
     * @param info 数据库基本信息
     * @return 数据库结构
     * @throws SQLException SQL异常
     */
 /*   public Database init(DatabaseInfo info) throws SQLException {
        this.info = info;
        this.connection = DbUtil.getConnection(info.getUrl(), info.getUsername(), info.getPassword());
        return configure();
    }*/

    /**
     * 配置数据库结构
     *
     * @return 数据库结构
     * @throws SQLException SQL异常
     */
/*    private Database configure() throws SQLException {
        Database bean = DbUtil.getBean(this.connection, SQL, Database.class, this.info.getName());
        bean.setTables(Table.configure(this.connection, this));
        bean.setViews(View.configure(this.connection, this));
        bean.setFunctions(Function.configure(this.connection, this));
        bean.setProcedures(Procedure.configure(this.connection, this));
        bean.setTriggers(Trigger.configure(this.connection, this));
        bean.setInfo(this.info);
        bean.setConnection(this.connection);
        return bean;
    }*/
    public DatabaseConfig getConfig() {
        return config;
    }

    public void setConfig(DatabaseConfig config) {
        this.config = config;
    }

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

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
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
