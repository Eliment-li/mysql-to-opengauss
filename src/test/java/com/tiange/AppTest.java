package com.tiange;

import com.tiange.core.entity.mysql.database.MysqlDatabase;
import com.tiange.core.entity.mysql.database.MysqlDatabaseService;
import com.tiange.core.entity.opengauss.database.GaussDatabase;
import com.tiange.core.entity.opengauss.table.GaussTable;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {

        try {
            //元数据迁移

            //新建 数据库元数据对象
            MysqlDatabase mysqlDatabase = new MysqlDatabase("mysqltest");


            //填充元数据 包括数据库表等
            new MysqlDatabaseService(mysqlDatabase).ReadMetaData();

            GaussDatabase gaussDatabase = MysqlDatabaseService.Conert2GaussDatabase(mysqlDatabase, new GaussDatabase("jack"));

            for (GaussTable gaussTable : gaussDatabase.getTables()) {

                System.out.println(gaussTable.toCreateSql());

                //OpenGaussDbUtil.execute(gaussTable.toCreateSql().toString());
            }

          /*  GaussTable gaussTable = gaussDatabase.getTables().get(0);
            System.out.println(gaussTable.toCreateSql());*/


        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
