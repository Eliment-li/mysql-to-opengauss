package com.tiange.core.opengauss;

import com.tiange.core.opengauss.column.ColumnGroupEnum;
import com.tiange.core.opengauss.column.GaussColumn;
import com.tiange.core.opengauss.table.GaussTable;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;
import com.tiange.core.utils.database.jdbc.Page;
import com.tiange.core.utils.others.FileUtils;
import com.tiange.core.utils.others.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
                //处理空值
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

    /**
     * 将表中的数据以分页的方式迁移
     *
     * @param gaussTable
     */
    public static void MigrateTableDataByPage(GaussTable gaussTable) throws Exception {
        String tableName = gaussTable.getTable_name();

        MySqlDbUtil utils = new MySqlDbUtil();
        try {
            //获取总记录
            long rowCount = getTableRowCount(tableName);

            int pageSize = 25;

            //pageCount=要分多少页
            int pageCount = (int) (rowCount / pageSize) + 1;

            String sql = "select * from " + tableName + " ";


            for (int i = 1; i <= pageCount; i++) {

                Page page = new Page();

                page.setPageNum(i);
                page.setPageSize(pageSize);

                utils.queryForPage(sql, page);

                //System.out.println("=====第 "+i+" 页=====");
                List<String> sqlList = convertDataToInsertSql(page.getPageContent(), gaussTable);

                OpenGaussDbUtil.executeSqlListAtomicity(sqlList);

                TimeUnit.MICROSECONDS.sleep(100);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        //分页传输


    }

    /**
     * 获取表中记录总数
     *
     * @param tableName
     */
    private static long getTableRowCount(String tableName) throws SQLException {
        MySqlDbUtil util = new MySqlDbUtil();

        Long count = util.QueryForScalar("select count(*) as count from " + tableName + ";");

        return count;

    }
}
