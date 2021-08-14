package com.tiange;

import com.tiange.core.mysql.database.MysqlDatabase;
import com.tiange.core.mysql.database.MysqlDatabaseService;
import com.tiange.core.opengauss.database.GaussDatabase;
import com.tiange.core.opengauss.table.GaussTable;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;

public class App
{
    public static void main( String[] args )
    {

        try {

            /*mysqltest=mysql数据库名字*/
            MysqlDatabase mysqlDatabase = new MysqlDatabase("mysqltest");

            //填充元数据 包括数据库表等
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
