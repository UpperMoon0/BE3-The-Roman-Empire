package com.nhat.dao;

import com.nhat.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Request;

public class RequestDAO {

    /**
     * Inserts a new request into the database.
     *
     * @param request the Request object to be inserted
     */
    public void insertRequest(Request request) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Request (written_date, delivered_date, period, region_id, emperor_id, consultant_id) " +
                             "VALUES (?, ?, ?, ?, ?, ?)")) {

            // Set parameters for the PreparedStatement
            statement.setDate(1, request.getWrittenDate());
            statement.setDate(2, request.getDeliveredDate());
            statement.setInt(3, request.getPeriod());
            statement.setInt(4, request.getRegionId());
            statement.setInt(5, request.getEmperorId());
            statement.setInt(6, request.getConsultantId());

            // Execute the query to insert the new request
            statement.executeUpdate();

        } catch (SQLException e) {
            // Handle or log the SQLException
            e.printStackTrace(); // You may want to log this instead of printing to console
        }
    }
    
    /**
     * Retrieves all requests from the database for a specific consultant.
     *
     * @param consultantId the ID of the consultant
     * @return List of Request objects for the specified consultant
     */
    public List<Request> getRequestsByConsultantId(int consultantId) {
        List<Request> requests = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT * FROM Request WHERE consultant_id = ?")) {

            preparedStatement.setInt(1, consultantId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    Date writtenDate = resultSet.getDate("written_date");
                    Date deliveredDate = resultSet.getDate("delivered_date");
                    int period = resultSet.getInt("period");
                    int regionId = resultSet.getInt("region_id");
                    int emperorId = resultSet.getInt("emperor_id");
                    // consultantId is already known from the method parameter

                    Request request = new Request(id, writtenDate, deliveredDate, period, regionId, emperorId, consultantId);
                    requests.add(request);
                }
            }

        } catch (SQLException e) {
            // Handle or log the SQLException
            e.printStackTrace(); 
        }

        return requests;
    }
    
    /**
    * Retrieves a request from the database by its ID.
    *
    * @param requestId the ID of the request
    * @return Request object if found, null otherwise
    */
   public Request getRequestById(int requestId) {
       try (Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM Request WHERE id = ?")) {

           preparedStatement.setInt(1, requestId);

           try (ResultSet resultSet = preparedStatement.executeQuery()) {
               if (resultSet.next()) {
                   Date writtenDate = resultSet.getDate("written_date");
                   Date deliveredDate = resultSet.getDate("delivered_date");
                   int period = resultSet.getInt("period");
                   int regionId = resultSet.getInt("region_id");
                   int emperorId = resultSet.getInt("emperor_id");
                   int consultantId = resultSet.getInt("consultant_id");

                   return new Request(requestId, writtenDate, deliveredDate, period, regionId, emperorId, consultantId);
               }
           }

       } catch (SQLException e) {
           // Handle or log the SQLException
           e.printStackTrace(); 
       }

       return null; // Return null if no request is found with the given ID
   }
   
   /**
    * Updates the delivered date of a request in the database.
    *
    * @param requestId    the ID of the request to be updated
    * @param deliveredDate the new delivered date for the request
    */
   public void updateDeliveredDate(int requestId, Date deliveredDate) {
       try (Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Request SET delivered_date = ? WHERE id = ?")) {

           preparedStatement.setDate(1, deliveredDate);
           preparedStatement.setInt(2, requestId);

           preparedStatement.executeUpdate();

       } catch (SQLException e) {
           e.printStackTrace(); 
       }
   }
}
