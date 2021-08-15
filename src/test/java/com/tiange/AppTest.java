package com.tiange;

import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.mysql.database.MysqlDatabaseService;
import com.tiange.core.opengauss.DataMigrateService;
import com.tiange.core.opengauss.database.GaussDatabase;
import com.tiange.core.opengauss.key.GaussKey;
import com.tiange.core.opengauss.table.GaussTable;
import com.tiange.core.utils.database.jdbc.MySqlDbUtil;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;

import java.util.List;
import java.util.Map;

public class AppTest
{

    public static void TableTest() {
        {
            try {

                /*mysqltest=mysql数据库名字*/
                MysqlDatabase mysqlDatabase = new MysqlDatabase("mysqltest");

                //读取MySQL元数据
                new MysqlDatabaseService(mysqlDatabase).ReadMetaData();

                /*jack=OpenGauss数据库名字*/
                GaussDatabase gaussDatabase = MysqlDatabaseService.Conert2GaussDatabase(mysqlDatabase, new GaussDatabase("jack"));

                for (GaussTable gaussTable : gaussDatabase.getTables()) {


                    //执行 sql 语句
                    //OpenGaussDbUtil.execute(gaussTable.toCreateSql().toString());
                    // DataMigrateTest(gaussTable);
                    DataMigrateService.MigrateTableDataByPage(gaussTable);
                }



            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void keyTest() throws Exception {

        /*mysqltest=mysql数据库名字*/
        MysqlDatabase mysqlDatabase = new MysqlDatabase("mysqltest");

        //读取MySQL元数据
        new MysqlDatabaseService(mysqlDatabase).ReadMetaData();

        /*jack=OpenGauss数据库名字*/
        GaussDatabase gaussDatabase = MysqlDatabaseService.Conert2GaussDatabase(mysqlDatabase, new GaussDatabase("jack"));
        for (GaussKey gaussKey : gaussDatabase.getKeys()) {
            OpenGaussDbUtil.execute(gaussKey.toCreateTableSql().toString());
            System.out.println(gaussKey.toCreateTableSql());

        }
    }

    /**
     * 数据传输测试
     *
     * @throws Exception
     */
    public static void DataMigrateTest(GaussTable gaussTable) throws Exception {

        MySqlDbUtil util = new MySqlDbUtil();

        List<Map<String, Object>> Data = util.queryForMapList("select * from " + gaussTable.getTable_name() + ";");

        List<String> sqlList = DataMigrateService.convertDataToInsertSql(Data, gaussTable);

        for (String sql : sqlList) {

            System.out.println(sql);

            OpenGaussDbUtil.execute(sql);

        }
    }


    public static void main(String[] args) throws Exception {
        TableTest();
        // keyTest();

    }
}
