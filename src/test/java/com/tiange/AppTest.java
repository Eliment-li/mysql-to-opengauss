package com.tiange;

import com.tiange.core.entity.mysql.database.MysqlDatabase;
import com.tiange.core.entity.mysql.database.MysqlDatabaseService;
import com.tiange.core.entity.mysql.table.MysqlTable;
import com.tiange.core.utils.database.jdbc.OpenGaussDbUtil;
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


            //新建 数据库元数据对象
            MysqlDatabase mysqlDatabase = new MysqlDatabase("mysqltest");

            //填充元数据 包裹数据库表等
            new MysqlDatabaseService(mysqlDatabase).ReadMetaData();
            for (MysqlTable mysqlTable : mysqlDatabase.getMysqlTables()) {
                System.out.println(mysqlTable.toOpenGaussTable().toCreateSql());
                OpenGaussDbUtil.execute(mysqlTable.toOpenGaussTable().toCreateSql().toString());
            }
            //输出建表语句
            // MysqlTable mysqlTable = mysqlDatabase.getMysqlTables().get(0);

            //  System.out.println(mysqlTable.toOpenGaussTable().toCreateSql());


            //todo  执行语句
        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
