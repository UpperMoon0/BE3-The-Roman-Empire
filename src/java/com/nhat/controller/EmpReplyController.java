package com.nhat.controller;

import com.nhat.dao.ReplyDAO;
import com.nhat.dao.ReportDAO;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Reply;
import model.Report;

/**
 * Servlet to handle replies to Empire Reports.
 *
 * @author Nhat
 */
public class EmpReplyController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
        HttpSession session = request.getSession();
        Integer selectedReportId = (Integer) session.getAttribute("selectedReport");

        // Check if a report ID is selected
        if (selectedReportId != null) {
            int reportId = selectedReportId;
            String replyContent = request.getParameter("replyContent");

            // You might want to perform validation on reportId and replyContent

            // Create a new Reply object
            Reply reply = new Reply();
            reply.setReportId(reportId);
            reply.setContent(replyContent);

            // Fill in other reply information
            Date today = new Date(System.currentTimeMillis());
            reply.setWrittenDate(today);
            reply.setDeliveredDate(null);

            // Get emperorId from the session (assuming it's stored in the session)
            int emperorId = (int) session.getAttribute("id");
            reply.setEmperorId(emperorId);

            // Get consultantId from the report using the reportId
            ReportDAO reportDAO = new ReportDAO();
            Report report = reportDAO.getReportById(reportId);

            if (report != null) {
                int consultantId = report.getConsultantId();
                reply.setConsultantId(consultantId);
            }

            // Use your DAO class to insert the reply into the database
            ReplyDAO replyDAO = new ReplyDAO();
            replyDAO.insertReply(reply);
        }


        // Forward to the JSP for rendering with the updated data
        // You might want to redirect to a different page or handle success messages differently
        request.getRequestDispatcher("empReportAndReply.jsp").forward(request, response);
    }
}
