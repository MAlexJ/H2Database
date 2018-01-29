package com.malex.util;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * User: malex
 */
public class DataBaseTest {

    @Test
    public void test() {
        String tableName = "test";
        String q1 = "PK INT";
        String q2 = "ID";

        DataBase.DataBaseBuilder builder = DataBase.builder()
                .dbName(tableName)
                .addSql(q1)
                .addSql(q2);

        assertEquals(tableName, builder.getDbName());
        assertEquals("CREATE TABLE IF NOT EXISTS test ID PK INT;", builder.getQuery());
    }
}
