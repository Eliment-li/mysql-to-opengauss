package com.tiange.core.entity.mysql;


import com.tiange.core.utils.others.StringUtils;

import java.util.Objects;

/**
 * 数据库表字段 JAVABEAN
 */
public class Column {

    public static final String FLAG_NOT_NULL = "NO";
    public static final String FLAG_DEFAULT_NULL = "YES";
    public static final String FLAG_AUTO_INCREMENT = "auto_increment";

    private String schema;
    private String tableName;
    private String name;
    private String defaultValue;
    private String nullable;
    private String type;
    private Long maxLength;
    private Long numericPrecision;
    private Long numericScale;
    private Long datetimePrecision;
    private String character;
    private String collation;
    private String columnType;
    private String columnKey;
    private String extra;
    private String comment;
    private String generationExpression;


    public String getDeleteSql() {
        return "ALTER TABLE `" + this.tableName + "` DROP COLUMN `" + this.name + "`;";
    }

    /**
     * @return return example: " 'id' varchar, "
     * @description 用于创建表
     */
    public String toCreateSqlFragment() {
        StringBuilder sb = new StringBuilder();
        sb.append("`").append(this.name).append("`").append(" ");
        sb.append(this.columnType).append(" ");
        if (!this.getTable().getDatabase().getInfo().getIgnoreCharacterCompare() && !StringUtils.isEmpty(this.collation)) {
            sb.append("COLLATE ").append(this.collation).append(" ");
        }
        //是否为NOT NULL
        if (FLAG_NOT_NULL.equals(this.nullable)) {
            sb.append("NOT NULL ");
        }
        if (!StringUtils.isEmpty(this.defaultValue)) {
            sb.append("DEFAULT ").append(this.defaultValue).append(" ");
        } else if (FLAG_DEFAULT_NULL.equals(this.nullable)) {
            sb.append("DEFAULT NULL ");
        }
        sb.append(this.extra).append(" ");
        if (!StringUtils.isEmpty(this.comment)) {
            sb.append("COMMENT '").append(this.comment).append("' ");
        }
        sb.append(",");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTableName(), getName(), getDefaultValue(), getNullable(), getType(), getMaxLength(), getNumericPrecision(), getNumericScale(), getDatetimePrecision(), getCharacter(), getCollation(), getColumnType(), getExtra(), getComment(), getGenerationExpression());
    }
    //getter&setter


    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Long maxLength) {
        this.maxLength = maxLength;
    }

    public Long getNumericPrecision() {
        return numericPrecision;
    }

    public void setNumericPrecision(Long numericPrecision) {
        this.numericPrecision = numericPrecision;
    }

    public Long getNumericScale() {
        return numericScale;
    }

    public void setNumericScale(Long numericScale) {
        this.numericScale = numericScale;
    }

    public Long getDatetimePrecision() {
        return datetimePrecision;
    }

    public void setDatetimePrecision(Long datetimePrecision) {
        this.datetimePrecision = datetimePrecision;
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

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getGenerationExpression() {
        return generationExpression;
    }

    public void setGenerationExpression(String generationExpression) {
        this.generationExpression = generationExpression;
    }
}
