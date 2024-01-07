package com.nhat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for managing database connections and resources.
 */
public class DatabaseUtil {

    /**
     * Retrieves a new database connection.
     *
     * @return the database connection
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        // Load SQL Server JDBC driver
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Corrected JDBC URL
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BE3;trustServerCertificate=true;";

        // Establish a connection and return it
        return DriverManager.getConnection(url, "sa", "123456789");
    }

    /**
     * Closes the provided Statement to release associated resources.
     *
     * @param statement the Statement to close
     */
    public static void closeResources(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Closes the provided ResultSet to release associated resources.
     *
     * @param resultSet the ResultSet to close
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Closes the provided database Connection to release associated resources.
     *
     * @param connection the Connection to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
