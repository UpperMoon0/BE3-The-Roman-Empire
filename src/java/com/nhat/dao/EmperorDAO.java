package com.nhat.dao;

import com.nhat.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is a DAO (Data Access Object) class that interacts with the database
 * and performs operations on the Emperor table. It uses the DatabaseUtil class
 * to get a connection to the database and the Emperor class to store and manipulate
 * data about emperors.
 */
public class EmperorDAO {

    /**
     * This method checks if a given username and password are valid for logging in
     * as an emperor. It queries the Emperor table and returns true if there is
     * a matching record, false otherwise.
     *
     * @param username the username of the emperor
     * @param password the password of the emperor
     * @return true if the login is successful, false otherwise
     */
    public boolean checkLogin(String username, String password) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Emperor WHERE username = ? AND password = ?")) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }

        } catch (SQLException e) {
            // If any exception occurs, print the stack trace
            e.printStackTrace();
        }

        // If no record is found or an exception occurs, return false
        return false;
    }
    
    /**
     * Retrieves the emperor ID based on the given username.
     *
     * @param username the username of the emperor
     * @return the emperor ID if found, or -1 if not found
     */
    public int getEmperorIdByUsername(String username) {
        int emperorId = -1; // Initialize the emperor ID to -1 (default if not found)

        // Get a connection to the database
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM Emperor WHERE username = ?")) {

            // Set the username parameter in the prepared statement
            preparedStatement.setString(1, username);

            // Execute the query
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                // Check if an emperor was found
                if (resultSet.next()) {
                    // Retrieve emperor ID from the result set
                    emperorId = resultSet.getInt("id");
                }
            }

        } catch (SQLException e) {
            // If any exception occurs, print the stack trace
            e.printStackTrace();
        }

        // Return the emperor ID (-1 if not found)
        return emperorId;
    }
}
