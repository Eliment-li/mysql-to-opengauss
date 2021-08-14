package com.tiange.core.opengauss;

import com.tiange.core.opengauss.column.ColumnGroupEnum;
import com.tiange.core.opengauss.column.GaussColumn;
import com.tiange.core.opengauss.table.GaussTable;
import com.tiange.core.utils.others.FileUtils;
import com.tiange.core.utils.others.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataMigrateService {

    static String INSERT_SQL = FileUtils.getStringByClasspath("opengauss/data/insert.sql");

    /**
     * 将数据集转换成可以插入数据库的 sql 语句
     *
     * @param Data 数据集
     * @return 将数据插入数据库中的 sql语句
     */
    public static List<String> convertDataToInsertSql(List<Map<String, Object>> Data, GaussTable gaussTable) {

        List<String> sqlList = new ArrayList<>();

        String databaseName = gaussTable.getGaussDatabase().getName();
        String TableName = gaussTable.getTable_name();

        List<GaussColumn> gaussColumns = gaussTable.getGaussColumns();

        for (Map<String, Object> data : Data) {

            StringBuilder sql = new StringBuilder(INSERT_SQL);

            StringUtils.replace("${databaseName}", databaseName, sql);
            StringUtils.replace("${tableName}", TableName, sql);

            StringBuilder valueSql = new StringBuilder(34);

            for (GaussColumn column : gaussColumns) {

                ColumnGroupEnum groupEnum = column.getGroupEnum();

                Object object = data.get(column.getColumn_name());
                if (object == null) {
                    valueSql.append(" null");
                    valueSql.append(",");
                    continue;
                }


                //数字类型的值不加引号
                if (groupEnum.equals(ColumnGroupEnum.FLOAT) ||
                        groupEnum.equals(ColumnGroupEnum.INT) ||
                        groupEnum.equals(ColumnGroupEnum.NUMERIC)) {
                    valueSql.append(data.get(column.getColumn_name()));
                    valueSql.append(",");
                } else {
                    valueSql.append("'" + data.get(column.getColumn_name()) + "'");
                    valueSql.append(",");
                }

            }// end gaussColumns loop
            valueSql = new StringBuilder(valueSql.substring(0, valueSql.length() - 1));

            StringUtils.replace("${VALUE}", valueSql, sql);


            sqlList.add(sql.toString());
        }// end Data loop

        return sqlList;
    }
}
