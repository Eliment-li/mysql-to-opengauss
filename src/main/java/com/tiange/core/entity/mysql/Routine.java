package com.tiange.core.entity.mysql;


/**
 */
public class Routine {

    private String security_type;
    private String definer;
    private String routine_schema;
    private String specific_name;
    private String routine_definition;

    private String parameter_mode;
    private String parameter_name;
    private String data_type;
    private Integer character_maximum_length;
    private Integer numeric_precision;
    private Integer numeric_scale;
    private String datetime_precision;
    private String dtd_identifier;
    private String character_set_name;
    private String collation_name;

    /*getter & setter  */

    public String getSecurity_type() {
        return security_type;
    }

    public void setSecurity_type(String security_type) {
        this.security_type = security_type;
    }

    public String getDefiner() {
        return definer;
    }

    public void setDefiner(String definer) {
        this.definer = definer;
    }

    public String getRoutine_schema() {
        return routine_schema;
    }

    public void setRoutine_schema(String routine_schema) {
        this.routine_schema = routine_schema;
    }

    public String getSpecific_name() {
        return specific_name;
    }

    public void setSpecific_name(String specific_name) {
        this.specific_name = specific_name;
    }

    public String getRoutine_definition() {
        return routine_definition;
    }

    public void setRoutine_definition(String routine_definition) {
        this.routine_definition = routine_definition;
    }

    public String getParameter_mode() {
        return parameter_mode;
    }

    public void setParameter_mode(String parameter_mode) {
        this.parameter_mode = parameter_mode;
    }

    public String getParameter_name() {
        return parameter_name;
    }

    public void setParameter_name(String parameter_name) {
        this.parameter_name = parameter_name;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public Integer getCharacter_maximum_length() {
        return character_maximum_length;
    }

    public void setCharacter_maximum_length(Integer character_maximum_length) {
        this.character_maximum_length = character_maximum_length;
    }

    public Integer getNumeric_precision() {
        return numeric_precision;
    }

    public void setNumeric_precision(Integer numeric_precision) {
        this.numeric_precision = numeric_precision;
    }

    public Integer getNumeric_scale() {
        return numeric_scale;
    }

    public void setNumeric_scale(Integer numeric_scale) {
        this.numeric_scale = numeric_scale;
    }

    public String getDatetime_precision() {
        return datetime_precision;
    }

    public void setDatetime_precision(String datetime_precision) {
        this.datetime_precision = datetime_precision;
    }

    public String getDtd_identifier() {
        return dtd_identifier;
    }

    public void setDtd_identifier(String dtd_identifier) {
        this.dtd_identifier = dtd_identifier;
    }

    public String getCharacter_set_name() {
        return character_set_name;
    }

    public void setCharacter_set_name(String character_set_name) {
        this.character_set_name = character_set_name;
    }

    public String getCollation_name() {
        return collation_name;
    }

    public void setCollation_name(String collation_name) {
        this.collation_name = collation_name;
    }
}
