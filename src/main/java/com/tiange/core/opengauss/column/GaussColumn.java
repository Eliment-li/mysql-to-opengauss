package com.tiange.core.opengauss.column;

import com.tiange.core.opengauss.table.GaussTable;
import com.tiange.core.utils.others.StringUtils;

/**
 * openGauss 表字段实体类
 */
public class GaussColumn {

    private String table_schema;
    private String table_name;
    private String column_name;
    private String oridinal_position;
    private String column_default;
    private String is_nullable;
    private String datetype;
    private Long character_maximum_length;
    private String character_octet_length;
    private String comment;
    private String column_key;
    private String extra;
    private ColumnGroupEnum groupEnum;

    //引用数据库
    private GaussTable gaussTable;

    /*
    If data_type identifies a numeric type, this column contains the (declared or implicit) precision of the type for this column.
    The precision indicates the number of significant digits. It can be expressed in decimal (base 10) or binary (base 2) terms,
     as specified in the column numeric_precision_radix. For all other data types, this column is null.
     在opengauss中，该字段的值是固定的 例如 int4 的 numeric_precision 固定为 32 ( base 2)
     */
    private Long numeric_precision;

    /*
    if data_type identifies a numeric type, this column indicates in which base the values in the columns numeric_precision
    and numeric_scale are expressed. The value is either 2 or 10. For all other data types, this column is null.
    */
    private int numeric_precision_radix;

    /*
     If data_type identifies an exact numeric type, this column contains the (declared or implicit) scale of the type
     for this column. The scale indicates the number of significant digits to the right of the decimal point. It can
     be expressed in decimal (base 10) or binary (base 2) terms, as specified in the column numeric_precision_radix.

     For all other data types, this column is null.
     */
    private Long numeric_scale;

    /*
     If data_type identifies a date, time, timestamp, or interval type, this column contains the (declared or implicit)
     fractional seconds precision of the type for this column, that is, the number of decimal digits maintained following
     the decimal point in the seconds value.

     For all other data types, this column is null.
     */
    private Long datetime_precision;

    /*
    Name of the database that the column data type (the underlying type of the domain, if applicable) is defined
    in (always the current database)
    */
    private String udt_catalog;

    /*
    Name of the schema that the column data type (the underlying type of the domain, if applicable) is defined in
    */
    private String udt_schema;

    /*Name of the column data type (the underlying type of the domain, if applicable)*/
    private String udt_name;

    /*
    An identifier of the data type descriptor of the column, unique among the data type descriptors pertaining to the
    table. This is mainly useful for joining with other instances of such identifiers. (The specific format of the
    identifier is not defined and not guaranteed to remain the same in future versions.)
    */
    private String dtd_identifier;


    private static String QUOTE = "\"";
    private static String SINGLE_QUOTE = "'";
    private static String YES = "YES";
    private static String NO = "NO";
    private static String COLUMN_COMMENT_TEMPLATE = "COMMENT ON COLUMN \"${databaseName}\".\"${tableName}\".\"${columnName}\" IS '${comment}' ";

    /**
     * @return 建表语句片段
     */
    public StringBuilder toCreateTableSql() {
        StringBuilder createTableSql = new StringBuilder(16);

        if (this.datetype.equals("float")) System.out.println(this.groupEnum);
        /*不同类型的数据，使用不同的转换器 */
        //跳过没有指定组的字段
        if (this.groupEnum == null) {
            //todo alert
            createTableSql = new StringBuilder(" ");
            return createTableSql;
        }
        switch (this.groupEnum) {
            case INT:
                createTableSql = toIntTypeSql();
                break;
            case NUMERIC:
                createTableSql = toNumericTypeSql();
                break;
            case FLOAT:
                createTableSql = toFloatTypeSql();
                break;
            case DATE_AND_TIME:
                createTableSql = toTimeAndTimeTypeSql();
                break;
            case CHARS:
                createTableSql = toCharsTypeSql();
                break;
            case TEXT:
                createTableSql = toTextTypeSql();
                break;
            default:
                createTableSql = new StringBuilder(" ");
                break;
        }
        //not null
        if (is_nullable.equals(NO)) {
            createTableSql.append(" NOT NULL ");
        }

        return createTableSql;
    }


    /**
     * @return 生成注释的 sql 语句
     */
    public StringBuilder getCommentSql() {
        StringBuilder sql = new StringBuilder(COLUMN_COMMENT_TEMPLATE);

        String databaseName = this.getGaussTable().getGaussDatabase().getName();
        String tableName = this.getGaussTable().getTable_name();

        StringUtils.replace("${databaseName}", databaseName, sql);
        StringUtils.replace("${tableName}", tableName, sql);
        StringUtils.replace("${columnName}", this.column_name, sql);
        StringUtils.replace("${comment}", this.comment, sql);

        sql.append(";");
        sql.append("\n");


        return sql;
    }

    /**
     * key 包括 主键 和 唯一索引
     * PRI=主键
     * UNI=唯一索引
     *
     * @return 生成 key  的 sql 语句
     */
    public StringBuilder getColumnKeySql() {
        //todo
        StringBuilder sql = new StringBuilder(34);


        return sql;
    }

    /**
     * 适用于 int 类型
     *
     * @return 建表语句
     * @see ColumnGroupEnum
     */
    public StringBuilder toIntTypeSql() {
        // 自增


        StringBuilder sql = new StringBuilder(16);
        sql.append(QUOTE + this.column_name + QUOTE);
        sql.append(" ");

        if (this.extra.equals("auto_increment")) {
            //用序列实现自增
            sql.append(" SERIAL ");
        } else {

            sql.append(this.datetype);
            sql.append(" DEFAULT ");
            sql.append(this.column_default + " ");
        }

        return sql;
    }

    /**
     * 适用于 固定长度的小数类型
     * @return 建表语句
     * @see ColumnGroupEnum
     */
    public StringBuilder toFloatTypeSql() {
        {

            StringBuilder sql = new StringBuilder(16);
            sql.append(QUOTE + this.column_name + QUOTE);
            sql.append(" ");
            sql.append(this.datetype);

            sql.append(" DEFAULT ");
            sql.append(this.column_default + " ");

            return sql;
        }

    }

    /**
     * 适用于 非固定长度的小数类型
     * @return 建表语句
     * @see ColumnGroupEnum
     */
    public StringBuilder toNumericTypeSql() {

        StringBuilder sql = new StringBuilder(16);
        sql.append(QUOTE + this.column_name + QUOTE);
        sql.append(" ");
        sql.append(this.datetype);

        if (numeric_precision != 0 && numeric_scale != 0)

            sql.append("(" + this.numeric_precision + "," + numeric_scale + ")");

        else if (numeric_precision != 0)

            sql.append("(" + this.numeric_precision + ")");

        //设置默认值
        sql.append(" DEFAULT ");

        sql.append(this.column_default);

        return sql;
    }

    /**
     * 适用于 时间类型
     *
     * @return 建表语句
     * @see ColumnGroupEnum
     */
    private StringBuilder toTimeAndTimeTypeSql() {
        // todo 解決 mysql 日期中出现0 的问题
        StringBuilder sql = new StringBuilder(16);
        sql.append(QUOTE + this.column_name + QUOTE);
        sql.append(" ");
        sql.append(this.datetype);

        if (this.datetime_precision != null)
            sql.append("(" + this.datetime_precision + ")");

        //设置默认值
        sql.append(" DEFAULT ");
        if (this.column_default == null) {
            //如果为NULL 左右两侧不用加单引号
            sql.append(" NULL ");
        } else {
            sql.append(SINGLE_QUOTE + this.column_default + SINGLE_QUOTE);
        }

        return sql;
    }


    /**
     * 适用于字符串类型
     *
     * @return 建表语句
     * @see ColumnGroupEnum
     */
    private StringBuilder toCharsTypeSql() {
        StringBuilder sql = new StringBuilder(16);
        sql.append(QUOTE + this.column_name + QUOTE);
        sql.append(" ");
        sql.append(this.datetype);
        sql.append("(" + this.character_maximum_length + ")");

        //设置默认值
        sql.append(" DEFAULT ");
        if (this.column_default == null) {
            //如果为NULL 左右两侧不用加单引号
            sql.append(" NULL ");
        } else {
            sql.append(SINGLE_QUOTE + this.column_default + SINGLE_QUOTE);
        }

        return sql;
    }

    private StringBuilder toTextTypeSql() {

        StringBuilder sql = new StringBuilder(16);
        sql.append(QUOTE + this.column_name + QUOTE);
        sql.append(" ");
        sql.append(this.datetype);

        //设置默认值
        sql.append(" DEFAULT ");
        if (this.column_default == null) {
            //如果为NULL 左右两侧不用加单引号
            sql.append(" NULL ");
        } else {
            sql.append(SINGLE_QUOTE + this.column_default + SINGLE_QUOTE);
        }

        return sql;
    }

    private String spatialTypeConvertor() {

        return "";
    }

    //todo  实现各种类型的数据生成sql语句

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

    public String getOridinal_position() {
        return oridinal_position;
    }

    public void setOridinal_position(String oridinal_position) {
        this.oridinal_position = oridinal_position;
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

    public String getDatetype() {
        return datetype;
    }

    public void setDatetype(String datetype) {
        this.datetype = datetype;
    }

    public Long getCharacter_maximum_length() {
        return character_maximum_length;
    }

    public void setCharacter_maximum_length(Long character_maximum_length) {
        this.character_maximum_length = character_maximum_length;
    }

    public String getCharacter_octet_length() {
        return character_octet_length;
    }

    public void setCharacter_octet_length(String character_octet_length) {
        this.character_octet_length = character_octet_length;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ColumnGroupEnum getGroupEnum() {
        return groupEnum;
    }

    public void setGroupEnum(ColumnGroupEnum groupEnum) {
        this.groupEnum = groupEnum;
    }

    public Long getNumeric_precision() {
        return numeric_precision;
    }

    public void setNumeric_precision(Long numeric_precision) {
        this.numeric_precision = numeric_precision;
    }

    public int getNumeric_precision_radix() {
        return numeric_precision_radix;
    }

    public void setNumeric_precision_radix(int numeric_precision_radix) {
        this.numeric_precision_radix = numeric_precision_radix;
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

    public String getUdt_catalog() {
        return udt_catalog;
    }

    public void setUdt_catalog(String udt_catalog) {
        this.udt_catalog = udt_catalog;
    }

    public String getUdt_schema() {
        return udt_schema;
    }

    public void setUdt_schema(String udt_schema) {
        this.udt_schema = udt_schema;
    }

    public String getUdt_name() {
        return udt_name;
    }

    public void setUdt_name(String udt_name) {
        this.udt_name = udt_name;
    }

    public String getDtd_identifier() {
        return dtd_identifier;
    }

    public void setDtd_identifier(String dtd_identifier) {
        this.dtd_identifier = dtd_identifier;
    }

    public String getColumn_key() {
        return column_key;
    }

    public void setColumn_key(String column_key) {
        this.column_key = column_key;
    }

    public GaussTable getGaussTable() {
        return gaussTable;
    }

    public void setGaussTable(GaussTable gaussTable) {
        this.gaussTable = gaussTable;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
