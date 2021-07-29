package com.tiange;

import com.tiange.core.entity.mysql.Database;
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

        Database database = new Database();

        database.Init();

        try {

            database.ReadData();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
