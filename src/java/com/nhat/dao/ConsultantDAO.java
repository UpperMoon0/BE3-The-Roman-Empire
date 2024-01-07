package com.nhat.dao;

import com.nhat.util.DatabaseUtil;
import model.Consultant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a DAO (Data Access Object) class that interacts with the database
 * and performs operations on the Consultant table. It uses the DatabaseUtil class
 * to get a connection to the database and the Consultant class to store and manipulate
 * data about consultants.
 * @author Nhat
 */
public class ConsultantDAO {

    /**
    * This method checks if a given username and password are valid for logging in
    * as a consultant. It queries the Consultant table and returns true if there is
    * a matching record, false otherwise.
    *
    * @param username the username of the consultant
    * @param password the password of the consultant
    * @return true if the login is successful, false otherwise
    */
   public boolean checkLogin(String username, String password) {
       try (Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Consultant WHERE username = ? AND password = ?")) {

           preparedStatement.setString(1, username);
           preparedStatement.setString(2, password);

           try (ResultSet resultSet = preparedStatement.executeQuery()) {
               return resultSet.next();
           }

       } catch (SQLException e) {
           // If any exception occurs, print the stack trace
           e.printStackTrace();
       }

       return false;
   }

   /**
     * This method inserts a new consultant into the Consultant table. It takes a Consultant
     * object as a parameter and sets its fields as values for the query. It executes the query
     * and prints a message if the insertion is successful.
     *
     * @param consultant the Consultant object to be inserted
     */
    public void insertConsultant(Consultant consultant) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Consultant (username, password, name, age, address, term_num, salary, noble, region_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, consultant.getUsername());
            preparedStatement.setString(2, consultant.getPassword());
            preparedStatement.setString(3, consultant.getName());
            preparedStatement.setInt(4, consultant.getAge());
            preparedStatement.setString(5, consultant.getAddress());
            preparedStatement.setInt(6, consultant.getTermNum());
            preparedStatement.setInt(7, consultant.getSalary());
            preparedStatement.setBoolean(8, consultant.isNoble());
            preparedStatement.setInt(9, consultant.getRegionId());

            int rows = preparedStatement.executeUpdate();

            if (rows > 0) {
                System.out.println("A new consultant was inserted successfully!");
            }

        } catch (SQLException e) {
            // If any exception occurs, print the stack trace
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all consultants from the Consultant table and returns a list of Consultant objects.
     *
     * @return List of Consultant objects
     */
    public List<Consultant> getAllConsultants() {
        List<Consultant> consultants = new ArrayList<>();

        // Get a connection to the database
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Consultant");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            // Iterate through the result set and create Consultant objects
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                int termNum = resultSet.getInt("term_num");
                int salary = resultSet.getInt("salary");
                boolean noble = resultSet.getBoolean("noble");
                int regionId = resultSet.getInt("region_id");

                // Create a new Consultant object and add it to the list
                Consultant consultant = new Consultant(id, username, password, name, age, address, termNum, salary, noble, regionId);
                consultants.add(consultant);
            }

        } catch (SQLException e) {
            // If any exception occurs, print the stack trace
            e.printStackTrace();
        }

        // Return the list of consultants
        return consultants;
    }
    
    /**
    * Retrieves the consultant ID based on the given username.
    *
    * @param username the username of the consultant
    * @return the consultant ID if found, or -1 if not found
    */
   public int getConsultantIdByUsername(String username) {
       int consultantId = -1;

       try (Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM Consultant WHERE username = ?")) {

           preparedStatement.setString(1, username);

           try (ResultSet resultSet = preparedStatement.executeQuery()) {
               if (resultSet.next()) {
                   consultantId = resultSet.getInt("id");
               }
           }

       } catch (SQLException e) {
           // If any exception occurs, print the stack trace
           e.printStackTrace();
       }

       return consultantId;
   }
   
   /**
    * Retrieves a consultant based on the given ID.
    *
    * @param consultantId the ID of the consultant
    * @return the Consultant object if found, or null if not found
    */
   public Consultant getConsultantById(int consultantId) {
       Consultant consultant = null;

       try (Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Consultant WHERE id = ?")) {

           preparedStatement.setInt(1, consultantId);

           try (ResultSet resultSet = preparedStatement.executeQuery()) {
               if (resultSet.next()) {
                   String username = resultSet.getString("username");
                   String password = resultSet.getString("password");
                   String name = resultSet.getString("name");
                   int age = resultSet.getInt("age");
                   String address = resultSet.getString("address");
                   int termNum = resultSet.getInt("term_num");
                   int salary = resultSet.getInt("salary");
                   boolean noble = resultSet.getBoolean("noble");
                   int regionId = resultSet.getInt("region_id");

                   // Create a new Consultant object
                   consultant = new Consultant(consultantId, username, password, name, age, address, termNum, salary, noble, regionId);
               }
           }

       } catch (SQLException e) {
           // If any exception occurs, print the stack trace
           e.printStackTrace();
       }

       return consultant;
   }
}
