package com.nhat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.nhat.util.DatabaseUtil;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Report;

public class ReportDAO {

    /**
     * Inserts a new report into the database.
     *
     * @param report the Report object to be inserted
     */
    public void insertReport(Report report) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Report (written_date, delivered_date, market_value, growth, revolt_rate, " +
                             "food_supply, description, request_id, emperor_id, consultant_id) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

            // Set parameters for the PreparedStatement
            statement.setDate(1, report.getWrittenDate());
            statement.setDate(2, report.getDeliveredDate());
            statement.setLong(3, report.getMarketValue());
            statement.setShort(4, report.getGrowth());
            statement.setShort(5, report.getRevoltRate());
            statement.setInt(6, report.getFoodSupply());
            statement.setString(7, report.getDescription());
            statement.setInt(8, report.getRequestId());
            statement.setInt(9, report.getEmperorId());
            statement.setInt(10, report.getConsultantId());

            // Execute the query to insert the new report
            statement.executeUpdate();

        } catch (SQLException e) {
            // Handle or log the SQLException
            e.printStackTrace(); // You may want to log this instead of printing to console
        }
    }
    
    /**
     * Retrieves all reports from the database.
     *
     * @return List of Report objects representing all reports in the database
     */
    public List<Report> getAllReports() {
        List<Report> reports = new ArrayList<>();

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Report");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                java.sql.Date writtenDate = resultSet.getDate("written_date");
                java.sql.Date deliveredDate = resultSet.getDate("delivered_date");
                long marketValue = resultSet.getLong("market_value");
                short growth = resultSet.getShort("growth");
                short revoltRate = resultSet.getShort("revolt_rate");
                int foodSupply = resultSet.getInt("food_supply");
                String description = resultSet.getString("description");
                int requestId = resultSet.getInt("request_id");
                int emperorId = resultSet.getInt("emperor_id");
                int consultantId = resultSet.getInt("consultant_id");

                Report report = new Report(id, writtenDate, deliveredDate, marketValue, growth, revoltRate,
                                           foodSupply, description, requestId, emperorId, consultantId);
                reports.add(report);
            }

        } catch (SQLException e) {
            // Handle or log the SQLException
            e.printStackTrace(); // You may want to log this instead of printing to console
        }

        return reports;
    }
    
    /**
     * Retrieves a report from the database by its ID.
     *
     * @param reportId the ID of the report to retrieve
     * @return the Report object representing the retrieved report, or null if not found
     */
    public Report getReportById(int reportId) {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Report WHERE id = ?");
        ) {
            preparedStatement.setInt(1, reportId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    java.sql.Date writtenDate = resultSet.getDate("written_date");
                    java.sql.Date deliveredDate = resultSet.getDate("delivered_date");
                    long marketValue = resultSet.getLong("market_value");
                    short growth = resultSet.getShort("growth");
                    short revoltRate = resultSet.getShort("revolt_rate");
                    int foodSupply = resultSet.getInt("food_supply");
                    String description = resultSet.getString("description");
                    int requestId = resultSet.getInt("request_id");
                    int emperorId = resultSet.getInt("emperor_id");
                    int consultantId = resultSet.getInt("consultant_id");

                    return new Report(id, writtenDate, deliveredDate, marketValue, growth, revoltRate,
                            foodSupply, description, requestId, emperorId, consultantId);
                }
            }
        } catch (SQLException e) {
            // Handle or log the SQLException
            e.printStackTrace(); // You may want to log this instead of printing to console
        }

        // Return null if the report with the given ID is not found
        return null;
    }
    
    /**
    * Retrieves all reports by a specific consultant from the database.
    *
    * @param consultantId the ID of the consultant
    * @return List of Report objects representing all reports for the given consultant
    */
   public List<Report> getReportsByConsultantId(int consultantId) {
       List<Report> reports = new ArrayList<>();

       try (Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Report WHERE consultant_id = ?");
       ) {
           preparedStatement.setInt(1, consultantId);

           try (ResultSet resultSet = preparedStatement.executeQuery()) {
               while (resultSet.next()) {
                   int id = resultSet.getInt("id");
                   java.sql.Date writtenDate = resultSet.getDate("written_date");
                   java.sql.Date deliveredDate = resultSet.getDate("delivered_date");
                   long marketValue = resultSet.getLong("market_value");
                   short growth = resultSet.getShort("growth");
                   short revoltRate = resultSet.getShort("revolt_rate");
                   int foodSupply = resultSet.getInt("food_supply");
                   String description = resultSet.getString("description");
                   int requestId = resultSet.getInt("request_id");
                   int emperorId = resultSet.getInt("emperor_id");

                   Report report = new Report(id, writtenDate, deliveredDate, marketValue, growth, revoltRate,
                                              foodSupply, description, requestId, emperorId, consultantId);
                   reports.add(report);
               }
           }

       } catch (SQLException e) {
           // Handle or log the SQLException
           e.printStackTrace(); // You may want to log this instead of printing to console
       }

       return reports;
   }
   
   /**
    * Updates the delivered date of a report in the database.
    *
    * @param reportId       the ID of the report to update
    * @param newDeliveredDate the new delivered date for the report
    */
   public void updateDeliveredDate(int reportId, java.sql.Date newDeliveredDate) {
       try (Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE Report SET delivered_date = ? WHERE id = ?")) {

           // Set parameters for the PreparedStatement
           statement.setDate(1, newDeliveredDate);
           statement.setInt(2, reportId);

           // Execute the query to update the delivered date
           statement.executeUpdate();

       } catch (SQLException e) {
           // Handle or log the SQLException
           e.printStackTrace(); // You may want to log this instead of printing to console
       }
   }
}
