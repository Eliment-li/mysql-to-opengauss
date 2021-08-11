package com.tiange;

import com.tiange.core.entity.mysql.database.MysqlDatabaseService;
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

            new MysqlDatabaseService().ReadMetaData();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
