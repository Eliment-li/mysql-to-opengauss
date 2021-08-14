package com.tiange.core.mysql;

import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;
import com.tiange.core.utils.others.FileUtils;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Function结构定义
 */
public class MysqlFunction implements Serializable {
    private static final long serialVersionUID = -2051134976379808323L;

    private String securityType;
    private String definer;
    private String schema;
    private String name;
    private String source;

    private MysqlDatabase mysqlDatabase;

    private List<MysqlRoutine> mysqlRoutines = new ArrayList<>();


    public List<MysqlFunction> readData() throws SQLException {

        MySqlDbUtil dbUtil = new MySqlDbUtil();

        List<MysqlRoutine> beanList = (List<MysqlRoutine>) dbUtil.queryForBeans(FileUtils.getStringByClasspath("sql/detail/function.sql"), MysqlRoutine.class);
        Map<String, MysqlFunction> functions = new HashMap<>(0);
        for (MysqlRoutine mysqlRoutine : beanList) {
            MysqlFunction mysqlFunction = functions.get(mysqlRoutine.getSpecific_name());
            if (mysqlFunction == null) {
                mysqlFunction = new MysqlFunction();
            }
            mysqlFunction.setMysqlDatabase(mysqlDatabase);
            mysqlFunction.getMysqlRoutines().add(mysqlRoutine);
            mysqlFunction.setDefiner(mysqlRoutine.getDefiner());
            mysqlFunction.setSecurityType(mysqlRoutine.getSecurity_type());
            mysqlFunction.setSchema(mysqlRoutine.getRoutine_schema());
            mysqlFunction.setName(mysqlRoutine.getSpecific_name());
            mysqlFunction.setSource(mysqlRoutine.getRoutine_definition());
            functions.put(mysqlRoutine.getSpecific_name(), mysqlFunction);
        }
        return new ArrayList<>(functions.values());
    }


    public String toCreateSql() {
        //todo
        return null;
    }





    //getter&setter
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
