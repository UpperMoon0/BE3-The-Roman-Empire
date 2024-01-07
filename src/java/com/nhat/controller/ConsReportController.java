package com.nhat.controller;

import com.nhat.dao.ReportDAO;
import com.nhat.dao.RequestDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Report;
import model.Request;

/**
 *
 * @author Nhat
 */
public class ConsReportController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the JSP for initial rendering
        RequestDispatcher rd = request.getRequestDispatcher("consRequestAndReport.jsp");
        rd.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the selected request ID from the session
        HttpSession session = request.getSession();
        Integer selectedRequestId = (Integer) session.getAttribute("selectedRequest");

        // If a request ID is selected, fetch the details from the database
        if (selectedRequestId != null) {
            try {
                int requestId = selectedRequestId;

                // Use your DAO class to retrieve the selected request details
                RequestDAO requestDAO = new RequestDAO();
                Request selectedRequest = requestDAO.getRequestById(requestId);

                // Set the selected request as an attribute in the request
                request.setAttribute("selectedRequest", selectedRequest);

                // Retrieve report information from the form
                String marketValueStr = request.getParameter("marketValue");
                String growthStr = request.getParameter("growth");
                String revoltRateStr = request.getParameter("revoltRate");
                String foodSupplyStr = request.getParameter("foodSupply");
                String description = request.getParameter("description");

                // Validate and parse input values (you may need additional validation)
                long marketValue = Long.parseLong(marketValueStr);
                short growth = Short.parseShort(growthStr);
                short revoltRate = Short.parseShort(revoltRateStr);
                int foodSupply = Integer.parseInt(foodSupplyStr);

                // Create a new Report object
                Report report = new Report();
                report.setWrittenDate(new java.sql.Date(System.currentTimeMillis()));
                report.setDeliveredDate(null); // Delivered date is initially null
                report.setMarketValue(marketValue);
                report.setGrowth(growth);
                report.setRevoltRate(revoltRate);
                report.setFoodSupply(foodSupply);
                report.setDescription(description);
                report.setRequestId(selectedRequest.getId());
                report.setEmperorId(selectedRequest.getEmperorId());
                report.setConsultantId(selectedRequest.getConsultantId());

                // Use the DAO to insert the new report into the database
                ReportDAO reportDAO = new ReportDAO();
                reportDAO.insertReport(report);

            } catch (NumberFormatException e) {
                // Handle the case where the selectedRequestId or input values are not valid integers
                e.printStackTrace(); // You might want to log this instead of printing to console
            }
        }

        // Forward to the JSP for rendering with the updated data
        RequestDispatcher rd = request.getRequestDispatcher("consRequestAndReport.jsp");
        rd.forward(request, response);
    }
}
