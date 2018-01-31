package com.malex.util;

import com.malex.entity.Table;
import com.malex.exception.AppException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author malex
 */
public class DataBaseUtils extends AbstractBase {

    /**
     * @param dataBase builder object
     */
    public static void createDataBase(DataBase.DataBaseBuilder dataBase) {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(dataBase.getQuery());
        } catch (SQLException ex) {
            String errorMessage = "Error connection to DB | message: " + ex.getMessage();
            throw new AppException(errorMessage);
        }
    }

    /**
     * @param table the name of the table
     */
    public static void dropTable(String table) {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(String.format("DROP TABLE IF EXISTS %s;", table));
        } catch (SQLException ex) {
            String errorMessage = "Error drop the DB | message: " + ex.getMessage();
            throw new AppException(errorMessage);
        }
    }

    /**
     * @param query SQL query
     */
    public static void insert(String query) {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            String errorMessage = "Error insert to DB | message: " + ex.getMessage();
            throw new AppException(errorMessage);
        }
    }

    /**
     * @param table
     * @return
     */
    public static List<Table> select(String table) {
        try (Statement stmt = getConnection().createStatement()) {
            List<Table> result = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM %s;", table));
            while (rs.next()) {
                result.add(new Table(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
            return result;
        } catch (SQLException ex) {
            String errorMessage = "Error drop the DB | message: " + ex.getMessage();
            throw new AppException(errorMessage);
        }
    }

    public static ResultSet selectQuery(String query) {
        try (Statement stmt = getConnection().createStatement()) {
            return stmt.executeQuery(query);
        } catch (SQLException ex) {
            String errorMessage = "Error selectQuery(String query) | message: " + ex.getMessage();
            throw new AppException(errorMessage);
        }
    }

    public static int insertOrUpdate(String query){
        try (Statement stmt = getConnection().createStatement()) {
            return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            String errorMessage = "Error insertOrUpdate(String query) | message: " + ex.getMessage();
            throw new AppException(errorMessage);
        }
    }
}
