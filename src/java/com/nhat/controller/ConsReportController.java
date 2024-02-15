package com.nhat.controller;

import com.nhat.dao.ReportDAO;
import com.nhat.dao.RequestDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Report;
import model.Request;

/**
 * Controller class for handling consultant reports.
 */
public class ConsReportController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ConsReportController.class.getName());

    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Forward to the JSP for initial rendering
        RequestDispatcher rd = request.getRequestDispatcher("consRequestAndReport.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles HTTP POST requests to submit consultant reports.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            handleReportSubmission(request, response);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error processing report submission", e);
            // You might want to redirect to an error page here
        }

        // Forward to the JSP for rendering with the updated data
        RequestDispatcher rd = request.getRequestDispatcher("consRequestAndReport.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the submission of consultant reports.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an I/O error occurs
     */
    private void handleReportSubmission(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Retrieve the selected request ID from the session
        HttpSession session = request.getSession();
        Integer selectedRequestId = (Integer) session.getAttribute("selectedRequest");

        // If a request ID is selected, fetch the details from the database
        if (selectedRequestId != null) {
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

            try {
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
                // Handle the case where the input values are not valid numbers
                LOGGER.warning("Invalid numeric input for report: " + e.getMessage());
            }
        }
    }
}
