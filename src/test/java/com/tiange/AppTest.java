package com.tiange;

import com.tiange.core.entity.mysql.database.MysqlDatabase;
import com.tiange.core.entity.mysql.database.MysqlDatabaseService;
import com.tiange.core.entity.opengauss.database.GaussDatabase;
import com.tiange.core.entity.opengauss.key.GaussKey;
import com.tiange.core.entity.opengauss.table.GaussTable;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;

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

                    //打印 sql 语句
                    System.out.println(gaussTable.toCreateSql());

                    //执行 sql 语句
                    OpenGaussDbUtil.execute(gaussTable.toCreateSql().toString());
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
            System.out.println(gaussKey.toCreateTableSql());
        }
    }

    public static void main(String[] args) throws Exception {
        TableTest();
        keyTest();

    }
}
