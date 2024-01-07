package com.nhat.controller;

import com.nhat.dao.ReportDAO;
import com.nhat.dao.ReplyDAO;
import com.nhat.util.ViewUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Report;
import model.Reply;

public class ConsReportHistoryController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ConsReportHistoryController.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            handleReportHistoryRequest(request, response);
        } catch (IOException | ServletException e) {
            LOGGER.log(Level.SEVERE, "Error processing report history request", e);
            // You might want to redirect to an error page here
        }

        // Forward to the report history JSP page
        request.getRequestDispatcher("consReportHistory.jsp").forward(request, response);
    }

    /**
     * Handles the request for the consultant's report history.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException      if an I/O error occurs
     * @throws ServletException if a servlet-specific error occurs
     */
    private void handleReportHistoryRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Retrieve the selected report ID from the request parameter
        String selectedReportIdString = request.getParameter("selectedReportId");

        // Check if the selected report ID is not null and is a valid integer
        if (selectedReportIdString != null && selectedReportIdString.matches("\\d+")) {
            int selectedReportId = Integer.parseInt(selectedReportIdString);

            // Retrieve the selected report from the database
            ReportDAO reportDAO = new ReportDAO();
            Report selectedReport = reportDAO.getReportById(selectedReportId);

            // Retrieve the reply for the selected report from the database
            ReplyDAO replyDAO = new ReplyDAO();
            Reply replyForReport = replyDAO.getReplyByReportId(selectedReportId);

            // Update the request attributes with the selected report and reply
            request.setAttribute("selectedReport", selectedReport);
            request.setAttribute("replyForReport", replyForReport);
        }
    }
}
