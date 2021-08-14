package com.tiange.core.mysql;

import com.tiange.core.mysql.database.MysqlDatabase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Procedure结构定义
 */
public class MysqlProcedure implements Serializable {
    private static final long serialVersionUID = -1316847891519066692L;

    private String securityType;
    private String definer;
    private String schema;
    private String name;
    private String source;

    private MysqlDatabase mysqlDatabase;

    private List<MysqlRoutine> mysqlRoutines = new ArrayList<>();


    public void readData() {
        //todo
    }

    public String toCreateSql() {
        //todo
        return null;
    }



    /*getter & setter */

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getDefiner() {
        return definer;
    }

    public void setDefiner(String definer) {
        this.definer = definer;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public MysqlDatabase getMysqlDatabase() {
        return mysqlDatabase;
    }

    public void setMysqlDatabase(MysqlDatabase mysqlDatabase) {
        this.mysqlDatabase = mysqlDatabase;
    }

    public List<MysqlRoutine> getMysqlRoutines() {
        return mysqlRoutines;
    }

    public void setMysqlRoutines(List<MysqlRoutine> mysqlRoutines) {
        this.mysqlRoutines = mysqlRoutines;
    }
}
