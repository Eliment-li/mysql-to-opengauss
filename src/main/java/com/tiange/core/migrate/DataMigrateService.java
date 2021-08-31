package com.tiange.core.migrate;

import com.tiange.core.migrate.Bucket.Bucket;
import com.tiange.core.migrate.Bucket.BucketConsumerThread;
import com.tiange.core.migrate.Bucket.BucketProducerThread;
import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.opengauss.column.ColumnGroupEnum;
import com.tiange.core.opengauss.column.GaussColumn;
import com.tiange.core.opengauss.table.GaussTable;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;
import com.tiange.core.utils.database.jdbc.Page;
import com.tiange.core.utils.others.FileUtils;
import com.tiange.core.utils.others.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Exchanger;

public class DataMigrateService {

    //插入语句模板
    private static String INSERT_SQL = FileUtils.getStringByClasspath("opengauss/data/insert.sql");

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
     * 该方法工作方式为单线程
     * @param gaussTable 目标数据库表
     */
    @Deprecated
    public static void MigrateTableDataByPage(GaussTable gaussTable) {
        String tableName = gaussTable.getTable_name();

        MySqlDbUtil utils = new MySqlDbUtil();
            //总记录数量
            long rowCount = getTableRowCount(tableName);

            //每个页的大小
            int pageSize = 25;

            //pageCount=要分多少页
            int pageCount = (int) (rowCount / pageSize) + 1;

            String sql = "select * from " + tableName + " ";


            for (int i = 1; i <= pageCount; i++) {

                Page page = new Page();

                page.setPageNum(i);
                page.setPageSize(pageSize);

                MySqlDbUtil.queryForPage(sql, page);

                List<String> sqlList = convertDataToInsertSql(page.getPageContent(), gaussTable);

                OpenGaussDbUtil.executeSqlListAtomicity(sqlList);


            }
    }


    /**
     * 获取表中记录总数
     *
     * @param tableName 表名
     */
    private static long getTableRowCount(String tableName) {
        MySqlDbUtil util = new MySqlDbUtil();

        Long count = util.QueryForScalar("select count(*) as count from " + tableName + ";");

        return count;

    }

    /* 多线程迁移数据模块 开始 */
    public static void doMigrate(MysqlDatabase mysqlDatabase) {

        Exchanger<Bucket> exchanger = new Exchanger<>();
        Bucket bucket1 = new Bucket();
        Bucket bucket2 = new Bucket();

        ChannelManager channelManager = new ChannelManager();

        new BucketProducerThread(exchanger, bucket1, mysqlDatabase).start();
        new BucketConsumerThread(exchanger, bucket2, channelManager).start();
    }
}
