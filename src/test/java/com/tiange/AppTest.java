package com.tiange;

import com.tiange.core.migrate.DataMigrateService;
import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.mysql.database.MysqlDatabaseService;
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


                DataMigrateService.doMigrate(mysqlDatabase);

                /*jack=OpenGauss数据库名字*/
                GaussDatabase gaussDatabase = MysqlDatabaseService.Conert2GaussDatabase(mysqlDatabase, new GaussDatabase("jack"));

                for (GaussTable gaussTable : gaussDatabase.getTables()) {

                  /*  System.out.print("\n==============" + gaussTable.getTable_name() + "开始迁移输表结构=================");
                    OpenGaussDbUtil.execute(gaussTable.toCreateSql().toString());

                    System.out.print("\n 验证表  " + gaussTable.getTable_name() + " ");
                    boolean CheckResult = GaussTableService.migrateCheck(gaussTable);
                    System.out.println("验证结果: " + CheckResult);
                    System.out.println("==============" + gaussTable.getTable_name() + "表结构迁移完毕=================");
                    // DataMigrateTest(gaussTable);

                    //数据迁移
                    System.out.println("\n\n\n==============" + gaussTable.getTable_name() + "开始迁移表数据=================");
                    DataMigrateService.MigrateTableDataByPage(gaussTable);
                    System.out.println("==============" + gaussTable.getTable_name() + "表数据迁移完毕=================");*/

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


    /**
     * 功能测试主程序
     */
    public static void main(String[] args) throws Exception {

        /*
         *  数据库表结构迁移 & 数据迁移
         */
        TableTest();


        /*
         *  数据库表索引迁移
         */
        // keyTest();
    }


}
