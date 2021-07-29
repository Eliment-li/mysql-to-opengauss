package com.tiange.core.entity.mysql;

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
public class Function implements Serializable {
    private static final long serialVersionUID = -2051134976379808323L;

    private String securityType;
    private String definer;
    private String schema;
    private String name;
    private String source;

    private Database database;

    private List<Routine> routines = new ArrayList<>();


    public List<Function> readData() throws SQLException {

        MySqlDbUtil dbUtil = new MySqlDbUtil(this.database.getConfig());

        List<Routine> beanList = (List<Routine>) dbUtil.queryForBeans(FileUtils.getStringByClasspath("sql/detail/function.sql"), Routine.class);
        Map<String, Function> functions = new HashMap<>(0);
        for (Routine routine : beanList) {
            Function function = functions.get(routine.getSpecific_name());
            if (function == null) {
                function = new Function();
            }
            function.setDatabase(database);
            function.getRoutines().add(routine);
            function.setDefiner(routine.getDefiner());
            function.setSecurityType(routine.getSecurity_type());
            function.setSchema(routine.getRoutine_schema());
            function.setName(routine.getSpecific_name());
            function.setSource(routine.getRoutine_definition());
            functions.put(routine.getSpecific_name(), function);
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

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public List<Routine> getRoutines() {
        return routines;
    }

    public void setRoutines(List<Routine> routines) {
        this.routines = routines;
    }
}
