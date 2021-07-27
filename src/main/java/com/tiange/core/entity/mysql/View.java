package com.tiange.core.entity.mysql;

import cn.codeforfun.migrate.core.utils.DbUtil;
import cn.codeforfun.migrate.core.utils.FileUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * View结构定义
 */
public class View implements Serializable {
    private static final long serialVersionUID = 2957489532511114320L;

    private String schema;
    private String name;
    private String source;
    private String definer;
    private String securityType;
    private String character;
    private String collation;

    private String checkOption;
    private String updatable;

    private Database database;

    public static List<View> configure(Connection connection, Database database) throws SQLException {
        return DbUtil.getBeanList(connection,
                FileUtil.getStringByClasspath("sql/detail/view.sql"),
                View.class, database.getInfo().getName())
                .stream().peek(s -> s.setDatabase(database)).collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public String getCreateSql() {
        return getString("CREATE ");
    }



    /* getter & setter */

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

    public String getDefiner() {
        return definer;
    }

    public void setDefiner(String definer) {
        this.definer = definer;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCollation() {
        return collation;
    }

    public void setCollation(String collation) {
        this.collation = collation;
    }

    public String getCheckOption() {
        return checkOption;
    }

    public void setCheckOption(String checkOption) {
        this.checkOption = checkOption;
    }

    public String getUpdatable() {
        return updatable;
    }

    public void setUpdatable(String updatable) {
        this.updatable = updatable;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
