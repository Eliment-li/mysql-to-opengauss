package com.tiange.core.entity.mysql;

import cn.codeforfun.migrate.core.diff.Difference;
import cn.codeforfun.migrate.core.entity.structure.annotations.DbUtilProperty;
import cn.codeforfun.migrate.core.utils.DbUtil;
import cn.codeforfun.migrate.core.utils.FileUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangbin
 */
@Getter
@Setter
public class Trigger implements Serializable, Difference {
    private static final long serialVersionUID = 182067591134835538L;

    @DbUtilProperty("TRIGGER_SCHEMA")
    private String trigger_schema;
    @DbUtilProperty("TRIGGER_NAME")
    private String trigger_name;
    @DbUtilProperty("DEFINER")
    private String definer;
    @DbUtilProperty("ACTION_TIMING")
    private String action_timing;
    @DbUtilProperty("EVENT_MANIPULATION")
    private String event_manipulation;
    @DbUtilProperty("EVENT_OBJECT_SCHEMA")
    private String event_object_schema;
    @DbUtilProperty("EVENT_OBJECT_TABLE")
    private String event_object_table;
    @DbUtilProperty("ACTION_ORIENTATION")
    private String action_orientation;
    @DbUtilProperty("ACTION_STATEMENT")
    private String action_statement;

    private Database database;

    public static List<Trigger> configure(Connection connection, Database database) throws SQLException {
        return DbUtil.getBeanList(connection,
                FileUtil.getStringByClasspath("sql/detail/trigger.sql"),
                Trigger.class, database.getInfo().getName())
                .stream().peek(s -> s.setDatabase(database)).collect(Collectors.toList());
    }

    @JsonIgnore
    @Override
    public String getCreateSql() {
        return "CREATE TRIGGER " + this.name + " " +
                this.actionTiming + " " + this.eventManipulation + " ON `" + this.objectTable + "` " +
                "FOR EACH " + this.actionOrientation + " " + this.source + ";";
    }



    /*getter & setter*/

    public String getTrigger_schema() {
        return trigger_schema;
    }

    public void setTrigger_schema(String trigger_schema) {
        this.trigger_schema = trigger_schema;
    }

    public String getTrigger_name() {
        return trigger_name;
    }

    public void setTrigger_name(String trigger_name) {
        this.trigger_name = trigger_name;
    }

    public String getDefiner() {
        return definer;
    }

    public void setDefiner(String definer) {
        this.definer = definer;
    }

    public String getAction_timing() {
        return action_timing;
    }

    public void setAction_timing(String action_timing) {
        this.action_timing = action_timing;
    }

    public String getEvent_manipulation() {
        return event_manipulation;
    }

    public void setEvent_manipulation(String event_manipulation) {
        this.event_manipulation = event_manipulation;
    }

    public String getEvent_object_schema() {
        return event_object_schema;
    }

    public void setEvent_object_schema(String event_object_schema) {
        this.event_object_schema = event_object_schema;
    }

    public String getEvent_object_table() {
        return event_object_table;
    }

    public void setEvent_object_table(String event_object_table) {
        this.event_object_table = event_object_table;
    }

    public String getAction_orientation() {
        return action_orientation;
    }

    public void setAction_orientation(String action_orientation) {
        this.action_orientation = action_orientation;
    }

    public String getAction_statement() {
        return action_statement;
    }

    public void setAction_statement(String action_statement) {
        this.action_statement = action_statement;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
