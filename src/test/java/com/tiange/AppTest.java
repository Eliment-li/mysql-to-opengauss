package com.tiange;

import com.tiange.core.entity.mysql.database.MysqlDatabase;
import com.tiange.core.entity.mysql.database.MysqlDatabaseService;
import com.tiange.core.entity.mysql.table.MysqlTable;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
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


        //mysqlDatabase.Init();

        try {


            MysqlDatabase mysqlDatabase = new MysqlDatabase("mysqltest");
            new MysqlDatabaseService(mysqlDatabase).ReadMetaData();
            for (MysqlTable mysqlTable : mysqlDatabase.getMysqlTables()) {
                System.out.println(mysqlTable.toOpenGaussTable().toCreateSql());
            }

            //MysqlTable mysqlTable= mysqlDatabase.getMysqlTables().get(0);
            //输出建表语句
            // System.out.println(mysqlTable.toOpenGaussTable().toCreateSql());


        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
