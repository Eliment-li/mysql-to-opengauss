package com.tiange.core.mysql;


import com.alibaba.fastjson.JSONObject;
import com.tiange.core.mysql.table.MysqlTable;
import com.tiange.core.opengauss.column.ColumnGroupEnum;
import com.tiange.core.opengauss.column.GaussColumn;
import com.tiange.core.utils.others.FileUtils;

/**
 * 数据库表字段 实体类
 */
public class MysqlColumn {

    public static final String FLAG_NOT_NULL = "NO";
    public static final String FLAG_DEFAULT_NULL = "YES";
    public static final String FLAG_AUTO_INCREMENT = "auto_increment";

    private String table_schema;
    private String table_name;

    private String column_default;
    private String is_nullable;

    /*column_name is the type name only with no other information*/
    private String column_name;
    /*data_type contains the type name and possibly other information such as the precision or length.*/
    private String data_type;

    private String column_type;

    private Long character_maximum_length;

    /*对于数值类型，表示长度*/
    private Long numeric_precision;

    /*对于数值类型，表示小数点后的精度*/
    private Long numeric_scale;

    /*For temporal columns, the fractional seconds precision.*/
    private Long datetime_precision;

    private String character_set_name;

    private String collation_name;


    /*If COLUMN_KEY is empty, the column either is not indexed or is indexed only as a secondary column in a multiple-column, nonunique index.

     If COLUMN_KEY is PRI, the column is a PRIMARY KEY or is one of the columns in a multiple-column PRIMARY KEY.

     If COLUMN_KEY is UNI, the column is the first column of a UNIQUE index. (A UNIQUE index permits multiple NULL values, but you can tell whether the column permits NULL by checking the Null column.)

     If COLUMN_KEY is MUL, the column is the first column of a nonunique index in which multiple occurrences of a given value are permitted within the column.

     If more than one of the COLUMN_KEY values applies to a given column of a mysqlTable, COLUMN_KEY displays the one with the highest priority, in the order PRI, UNI, MUL.

     A UNIQUE index may be displayed as PRI if it cannot contain NULL values and there is no PRIMARY KEY in the mysqlTable. A UNIQUE index may display as MUL if several columns form a composite UNIQUE index; although the combination of the columns is unique, each column can still hold multiple occurrences of a given value
   * */
    private String column_key;

    /* Any additional information that is available about a given column. The value is nonempty in these cases:

        auto_increment for columns that have the AUTO_INCREMENT attribute.

        on update CURRENT_TIMESTAMP for TIMESTAMP or DATETIME columns that have the ON UPDATE CURRENT_TIMESTAMP attribute.

        STORED GENERATED or VIRTUAL GENERATED for generated columns.

        DEFAULT_GENERATED for columns that have an expression default value.
    * */
    private String extra;

    private String column_comment;
    private String generation_expression;

    private MysqlTable mysqlTable;


    /**
     * 将 MySQL 字段类型转换为 OpenGauss 的类型
     *
     * @return 转换后的类型
     */
    private String toOpenGaussType(String mysqlDataType) {

        String dataTypeJson = FileUtils.getStringByClasspath("mysql/json/dataTypeMap.json");

        JSONObject map = JSONObject.parseObject(dataTypeJson);

        return (String) map.get(mysqlDataType);

    }

    /**
     * @return return example: " 'id' varchar, "
     * @description 用于创建表
     */
    public StringBuilder toCreateTableSql() {

        return this.toGaussColumn().toCreateTableSql();
    }

    public GaussColumn toGaussColumn() {
        GaussColumn gaussColumn = new GaussColumn();

        String datetype = toOpenGaussType(this.getData_type());//字段类型


        //字段名
        gaussColumn.setColumn_name(this.column_name);
        //字段类型
        gaussColumn.setDatetype(datetype);
        //时间类型的精度
        gaussColumn.setDatetime_precision(this.datetime_precision);
        //数字类型的长度
        gaussColumn.setNumeric_precision(this.numeric_precision);
        //数字类型的精度
        gaussColumn.setNumeric_scale(this.numeric_scale);
        //注释
        gaussColumn.setComment(this.column_comment);
        //默认值
        gaussColumn.setColumn_default(this.column_default);
        //是否可以为null
        gaussColumn.setIs_nullable(this.is_nullable);
        //最大长度
        gaussColumn.setCharacter_maximum_length(this.character_maximum_length);
        //colunn_key
        gaussColumn.setColumn_key(this.column_key);

        gaussColumn.setExtra(extra);


        //类型组
        gaussColumn.setGroupEnum(getColunGroupEnum());

        //column key
        return gaussColumn;
    }


    /**
     * 获取数据类型的组名
     */
    private ColumnGroupEnum getColunGroupEnum() {
        ColumnGroupEnum groupEnum = ColumnGroupEnum.map.get(this.data_type);
        if (groupEnum == null) {
            System.out.println("未找到匹配的Group:  " + this.data_type);
        }
        return groupEnum;
    }






    /*转换器 结束  */
    /* getter & setter */

    public String getTable_schema() {
        return table_schema;
    }

    public void setTable_schema(String table_schema) {
        this.table_schema = table_schema;
    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getColumn_name() {
        return column_name;
    }

    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }

    public String getColumn_default() {
        return column_default;
    }

    public void setColumn_default(String column_default) {
        this.column_default = column_default;
    }

    public String getIs_nullable() {
        return is_nullable;
    }

    public void setIs_nullable(String is_nullable) {
        this.is_nullable = is_nullable;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public Long getCharacter_maximum_length() {
        return character_maximum_length;
    }

    public void setCharacter_maximum_length(Long character_maximum_length) {
        this.character_maximum_length = character_maximum_length;
    }

    public Long getNumeric_precision() {
        return numeric_precision;
    }

    public void setNumeric_precision(Long numeric_precision) {
        this.numeric_precision = numeric_precision;
    }

    public Long getNumeric_scale() {
        return numeric_scale;
    }

    public void setNumeric_scale(Long numeric_scale) {
        this.numeric_scale = numeric_scale;
    }

    public Long getDatetime_precision() {
        return datetime_precision;
    }

    public void setDatetime_precision(Long datetime_precision) {
        this.datetime_precision = datetime_precision;
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

    public String getColumn_type() {

        return column_type;

    }

    public void setColumn_type(String column_type) {
        this.column_type = column_type;
    }

    public String getColumn_key() {
        return column_key;
    }

    public void setColumn_key(String column_key) {
        this.column_key = column_key;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getColumn_comment() {
        return column_comment;
    }

    public void setColumn_comment(String column_comment) {
        this.column_comment = column_comment;
    }

    public String getGeneration_expression() {
        return generation_expression;
    }

    public void setGeneration_expression(String generation_expression) {
        this.generation_expression = generation_expression;
    }

    public MysqlTable getMysqlTable() {
        return mysqlTable;
    }

    public void setMysqlTable(MysqlTable mysqlTable) {
        this.mysqlTable = mysqlTable;
    }
}
