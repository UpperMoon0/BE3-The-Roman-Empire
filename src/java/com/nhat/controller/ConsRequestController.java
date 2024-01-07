package com.nhat.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.nhat.dao.RequestDAO;
import model.Request;

public class ConsRequestController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the selected request ID from the form submission
        String selectedRequestId = request.getParameter("selectedRequest");

        // If a request ID is selected, fetch the details from the database
        if (selectedRequestId != null && !selectedRequestId.isEmpty()) {
            try {
                int requestId = Integer.parseInt(selectedRequestId);

                // Use your DAO class to retrieve the selected request details
                RequestDAO requestDAO = new RequestDAO();
                Request selectedRequest = requestDAO.getRequestById(requestId);

                // Set the selected request as an attribute in the request
                request.setAttribute("selectedRequest", selectedRequest);

                // Store the selected request in the session
                HttpSession session = request.getSession();
                session.setAttribute("selectedRequest", selectedRequest.getId());
            } catch (NumberFormatException e) {
                // Handle the case where the selectedRequestId is not a valid integer
                e.printStackTrace(); // You might want to log this instead of printing to console
            }
        }

        // Forward to the JSP for rendering with the updated data
        RequestDispatcher rd = request.getRequestDispatcher("consRequestAndReport.jsp");
        rd.forward(request, response);
    }
}
