package com.malex.util;

import com.malex.exception.AppExceprion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author malex
 */
public class DataBaseUtils {

    /**
     * JDBC driver name and database URL
     */
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./db/";

    /**
     * Database credentials
     */
    private static final String USER = "sa";
    private static final String PASS = "";

    /**
     * @param schema   the name of schema
     * @param dataBase builder object
     */
    public static void createDataBase(String schema, DataBase.DataBaseBuilder dataBase) {
        getDriver();
        try (Connection conn = DriverManager.getConnection(DB_URL + schema, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(dataBase.getQuery());
        } catch (SQLException ex) {
            String errorMessage = "Error connection to DB | message: " + ex.getMessage();
            throw new AppExceprion(errorMessage);
        }
    }

    /**
     * @param schema the name of schema
     * @param table  the name of the table
     */
    public static void dropTable(String schema, String table) {
        getDriver();
        try (Connection conn = DriverManager.getConnection(DB_URL + schema, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(String.format("DROP TABLE IF EXISTS %s;", table));
        } catch (SQLException ex) {
            String errorMessage = "Error drop the DB | message: " + ex.getMessage();
            throw new AppExceprion(errorMessage);
        }
    }

    /**
     * @param schema the name of schema
     * @param query  SQL query
     */
    public static void insert(String schema, String query) {
        getDriver();
        try (Connection conn = DriverManager.getConnection(DB_URL + schema, USER, PASS);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            String errorMessage = "Error insert to DB | message: " + ex.getMessage();

            throw new AppExceprion(errorMessage);
        }
    }

    private static void getDriver() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            String errorMessage = "Error registration driver | message: " + ex.getMessage();
            throw new AppExceprion(errorMessage);
        }
    }
}
