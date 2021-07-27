package com.tiange.core.entity.mysql;

import com.tiange.core.utils.database.MySqlDbUtil;
import com.tiange.core.utils.others.FileUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Procedure结构定义
 */
public class Procedure implements Serializable {
    private static final long serialVersionUID = -1316847891519066692L;

    private String securityType;
    private String definer;
    private String schema;
    private String name;
    private String source;

    private Database database;

    private List<Routine> routines = new ArrayList<>();

    public static List<Procedure> configure(Connection connection, Database database) throws SQLException {
        List<?> beanListTemp = MySqlDbUtil.queryForBeans(connection, FileUtils.getStringByClasspath("sql/detail/procedure.sql"), Routine.class);
        Map<String, Procedure> procedures = new HashMap<>(0);

        List<Routine> beanList = (List<Routine>) beanListTemp;
        for (Routine routine : beanList) {
            Procedure procedure = procedures.get(routine.getName());
            if (procedure == null) {
                procedure = new Procedure();
            }
            procedure.setDatabase(database);
            procedure.getRoutines().add(routine);
            procedure.setDefiner(routine.getDefiner());
            procedure.setSecurityType(routine.getSecurityType());
            procedure.setSchema(routine.getSchema());
            procedure.setName(routine.getName());
            procedure.setSource(routine.getSource());
            procedures.put(routine.getName(), procedure);
        }
        return new ArrayList<>(procedures.values());
    }

    public String getCreateSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE PROCEDURE `").append(this.name).append("`");
        sb.append("(");
        List<Routine> inputTypeList = this.getRoutines().stream().filter(s -> "IN".equals(s.getParamMode())).collect(Collectors.toList());
        List<Routine> resultTypeList = this.getRoutines().stream().filter(s -> "OUT".equals(s.getParamMode())).collect(Collectors.toList());
        for (Routine inList : inputTypeList) {
            sb.append("IN ").append(inList.getParamName()).append(" ").append(inList.getResultType()).append(",");
        }
        for (Routine outList : resultTypeList) {
            sb.append("OUT ").append(outList.getParamName()).append(" ").append(outList.getResultType()).append(",");
        }
        if (inputTypeList.size() > 0 || resultTypeList.size() > 0) {
            sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        }
        sb.append(") ");
        sb.append(" ").append(this.source).append(";");
        return sb.toString();
    }


/*    @Override
    public boolean equals(Object o) {

    }

    @Override
    public int hashCode(){

    }*/

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
