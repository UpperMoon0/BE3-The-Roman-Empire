package com.nhat.controller;

import com.nhat.dao.ReportDAO;
import com.nhat.util.ViewUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Report;

public class ConsReportHistoryController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the selected report ID from the request parameter
        String selectedReportIdString = request.getParameter("selectedReportId");
        System.out.println("selectedReportId " + selectedReportIdString);
        
        // Check if the selected report ID is not null and is a valid integer
        if (selectedReportIdString != null && selectedReportIdString.matches("\\d+")) {
            int selectedReportId = Integer.parseInt(selectedReportIdString);

            // Retrieve the selected report from the database
            ReportDAO reportDAO = new ReportDAO();
            Report selectedReport = reportDAO.getReportById(selectedReportId);
            System.out.println("selectedReportId " + selectedReportId + " selectedReport " + selectedReport);
            
            // Update the request attribute with the selected report
            request.setAttribute("selectedReport", selectedReport);
        }

        // Use the utility method to update the list of reports for the current consultant
        int consultantId = (int) request.getSession().getAttribute("id");
        ViewUtil.updateConsultantReportsList(request, consultantId);

        // Forward to the report history JSP page
        request.getRequestDispatcher("consReportHistory.jsp").forward(request, response);
    }
}
