package com.nhat.controller;

import com.nhat.dao.ReportDAO;
import com.nhat.util.ViewUtil;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Report;

/**
 * Servlet to handle requests related to Empire Reports.
 *
 * @author Nhat
 */
public class EmpReportController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve the selected report ID from the form submission
            String selectedReportId = request.getParameter("selectedReport");

            // If a report ID is selected, fetch the details from the database
            if (selectedReportId != null && !selectedReportId.isEmpty()) {
                int reportId = Integer.parseInt(selectedReportId);

                // Use your DAO class to retrieve the selected report details
                ReportDAO reportDAO = new ReportDAO();
                Report selectedReport = reportDAO.getReportById(reportId);

                // Set the selected report as an attribute in the request
                request.setAttribute("selectedReport", selectedReport);
                
                // Store the selected request in the session
                HttpSession session = request.getSession();
                session.setAttribute("selectedReport", selectedReport.getId());
            }
        } catch (NumberFormatException e) {
            // Handle the case where the selectedReportId is not a valid integer
            String errorMessage = "Invalid report ID. Please select a valid report.";
            request.setAttribute("error", errorMessage);
            // Log the error or print to console if needed: e.printStackTrace();
        }

        // Use the utility method to update the report list
        ViewUtil.updateReportsList(request);

        // Forward to the JSP for rendering with the updated data
        RequestDispatcher rd = request.getRequestDispatcher("empReportAndReply.jsp");
        rd.forward(request, response);
    }
}
