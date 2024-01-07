package com.nhat.dao;

import com.nhat.util.DatabaseUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Reply;

public class ReplyDAO {
    // Method to insert a new reply
    public void insertReply(Reply reply) {
        String query = "INSERT INTO reply (written_date, delivered_date, content, report_id, emperor_id, consultant_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setDate(1, reply.getWrittenDate());
            statement.setDate(2, reply.getDeliveredDate());
            statement.setString(3, reply.getContent());
            statement.setInt(4, reply.getReportId());
            statement.setInt(5, reply.getEmperorId());
            statement.setInt(6, reply.getConsultantId());

            statement.executeUpdate();
        } catch (SQLException ex) {
            handleSQLException(ex, "Error inserting reply");
        }
    }

    // Method to get a reply by ID
    public Reply getReplyById(int replyId) {
        String query = "SELECT * FROM reply WHERE id = ?";
        
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, replyId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractReplyFromResultSet(resultSet);
                }
            }
        } catch (SQLException ex) {
            handleSQLException(ex, "Error getting reply by ID");
        }

        return null; // Return null if no reply found with the given ID
    }

    // Method to get all replies with a given consultantId
    public List<Reply> getAllRepliesByConsultantId(int consultantId) {
        List<Reply> replies = new ArrayList<>();
        String query = "SELECT * FROM reply WHERE consultant_id = ?";
        
        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, consultantId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    replies.add(extractReplyFromResultSet(resultSet));
                }
            }
        } catch (SQLException ex) {
            handleSQLException(ex, "Error getting all replies by consultant ID");
        }

        return replies;
    }

    // Helper method to extract a Reply from a ResultSet
    private Reply extractReplyFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        Date writtenDate = resultSet.getDate("written_date");
        Date deliveredDate = resultSet.getDate("delivered_date");
        String content = resultSet.getString("content");
        int reportId = resultSet.getInt("report_id");
        int emperorId = resultSet.getInt("emperor_id");
        int consultantId = resultSet.getInt("consultant_id");

        return new Reply(id, writtenDate, deliveredDate, content, reportId, emperorId, consultantId);
    }

    // Helper method to handle SQLException
    private void handleSQLException(SQLException ex, String message) {
        Logger.getLogger(ReplyDAO.class.getName()).log(Level.SEVERE, message, ex);
        // You can throw a custom exception or handle it as needed
    }
    
    // Method to get a reply by Report ID
    public Reply getReplyByReportId(int reportId) {
        String query = "SELECT * FROM reply WHERE report_id = ?";

        try (
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, reportId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractReplyFromResultSet(resultSet);
                }
            }
        } catch (SQLException ex) {
            handleSQLException(ex, "Error getting reply by Report ID");
        }

        return null; // Return null if no reply found with the given Report ID
    }
}
