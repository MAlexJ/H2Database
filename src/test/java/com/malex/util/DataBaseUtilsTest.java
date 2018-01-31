package com.malex.util;

import com.malex.entity.Table;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

import java.util.List;

/**
 * User: malex
 */
@Log4j
public class DataBaseUtilsTest {

    @Test
    public void test() {
        String tableName = "user";
        String q1 = "(ID INT PRIMARY KEY, NAME VARCHAR(255), DESCRIPTION VARCHAR(255))";

        DataBase.DataBaseBuilder builder = DataBase.builder()
                .dbName(tableName)
                .addSql(q1);

        // #1 create table
        DataBaseUtils.createDataBase(builder);

        // #2 insert values to table
        DataBaseUtils.insert("INSERT INTO user VALUES(1, 'Hello', 'DESCRIPTION');");

        // #3 select
        List<Table> tables = DataBaseUtils.select(tableName);
        log.debug(tables);

        // #4 drop th table
        DataBaseUtils.dropTable(tableName);

        // #5 close connection
        DataBaseUtils.closeConnection();
    }
}
