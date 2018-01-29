package com.malex.util;

import org.junit.Test;

/**
 * User: malex
 */
public class DataBaseUtilsTest {

    @Test
    public void test() {
        String schema = "customer";
        String tableName = "user";
        String q1 = "(ID INT PRIMARY KEY, NAME VARCHAR(255))";

        DataBase.DataBaseBuilder builder = DataBase.builder()
                .dbName(tableName)
                .addSql(q1);

        // #1 create table
        DataBaseUtils.createDataBase(schema, builder);

        // #2 insert values to table
        DataBaseUtils.insert(schema, "INSERT INTO user VALUES(1, 'Hello');");

        // #3 drop th table
        DataBaseUtils.dropTable(schema, tableName);
    }
}
