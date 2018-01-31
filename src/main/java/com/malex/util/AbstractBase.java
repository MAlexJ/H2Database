package com.malex.util;

import com.malex.exception.AppException;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author malex
 */
@Log4j
public abstract class AbstractBase {

    /**
     * JDBC driver name and database URL
     */
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:./db/app";

    /**
     * Database credentials
     */
    private static final String USER = "sa";
    private static final String PASS = "";

    @Getter
    private static Connection connection;

    static {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException ex) {
            throw new AppException("Error registration driver | message: " + ex.getMessage());
        } catch (SQLException ex) {
            throw new AppException("Error connection to DB | message: " + ex.getMessage());
        }
    }

    protected static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new AppException("Error close connection | message: " + ex.getMessage());
            }
        }
    }
}
