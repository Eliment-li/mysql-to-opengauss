package com.tiange.core.entity.mysql;

import com.tiange.core.utils.database.MySqlDbUtil;
import com.tiange.core.utils.others.FileUtils;
import com.tiange.core.utils.others.StringUtils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

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

    public static List<Function> configure(Connection connection, Database database) throws SQLException {
        List<Routine> beanList = MySqlDbUtil.queryForBeans(connection,
                FileUtils.getStringByClasspath("sql/detail/function.sql"),
                Routine.class, database.getInfo().getName());
        Map<String, Function> functions = new HashMap<>(0);
        for (Routine routine : beanList) {
            Function function = functions.get(routine.getName());
            if (function == null) {
                function = new Function();
            }
            function.setDatabase(database);
            function.getRoutines().add(routine);
            function.setDefiner(routine.getDefiner());
            function.setSecurityType(routine.getSecurityType());
            function.setSchema(routine.getSchema());
            function.setName(routine.getName());
            function.setSource(routine.getSource());
            functions.put(routine.getName(), function);
        }
        return new ArrayList<>(functions.values());
    }

    /**
     * @return 创建 FUNCTION 的 sql
     */
    public String getCreateSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE FUNCTION `").append(this.name).append("`");
        sb.append("(");
        List<Routine> inputTypeList = this.getRoutines().stream().filter(s -> "IN".equals(s.getParamMode())).collect(Collectors.toList());
        List<Routine> resultTypeList = this.getRoutines().stream().filter(s -> null == s.getParamMode()).collect(Collectors.toList());
        for (Routine param : inputTypeList) {
            sb.append(param.getParamName()).append(" ").append(param.getResultType()).append(",");
        }
        sb = new StringBuilder(sb.substring(0, sb.length() - 1));
        sb.append(") ");
        sb.append(" RETURNS ");
        Routine param = resultTypeList.get(0);
        sb.append(param.getResultType());
        if (!StringUtils.isEmpty(param.getCharacter()) && !StringUtils.isEmpty(param.getCollation())) {
            sb.append(" CHARSET ").append(param.getCharacter()).append(" COLLATE ").append(param.getCollation());
        }
        sb.append(" ").append(this.source).append(";");
        return sb.toString();
    }


    public String getUpdateSql() {
        return null;
    }

    public String getDeleteSql() {
        return "DROP FUNCTION `" + this.name + "`;";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Function)) {
            return false;
        }
        Function function = (Function) o;
        return
                Objects.equals(getName(), function.getName()) &&
                        Objects.equals(getSource(), function.getSource()) &&
                        Objects.equals(getRoutines(), function.getRoutines());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),
                getSource(),
                getRoutines());
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
