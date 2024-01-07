package com.nhat.controller;

import com.nhat.dao.RequestDAO;
import com.nhat.util.ViewUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

import model.Request;

/**
 * Controller for handling actions related to requests in the Emperor's World.
 * This class handles POST requests.
 * Responsible for creating a new request, saving it to the database,
 * updating the selected region information, and forwarding the request to the emperor.jsp page.
 * 
 * @author Nhat
 */
public class EmpWorldController extends HttpServlet {
    
    /**
     * Handles POST requests related to requests in the Emperor's World.
     * Creates a new request, saves it to the database, updates the selected region information,
     * and forwards the request to the emperor.jsp page.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If an error occurs during servlet execution.
     * @throws IOException      If an I/O error occurs during the forwarding process.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the selected region ID from the session
        HttpSession session = request.getSession();
        Integer selectedRegionId = (Integer) session.getAttribute("selectedRegionId");

        if (selectedRegionId != null) {
            // Retrieve parameters from the request
            String selectedConsultantId = request.getParameter("selectedConsultant");
            String period = request.getParameter("period");

            // Retrieve the emperor ID from the session
            int emperorId = (int) session.getAttribute("id");

            // Create a new request with the provided information
            Request newRequest = new Request();
            newRequest.setWrittenDate(new Date(System.currentTimeMillis())); // Current date
            newRequest.setDeliveredDate(null); // Delivered date initially set to null
            newRequest.setPeriod(Integer.parseInt(period));
            newRequest.setRegionId(selectedRegionId);
            newRequest.setEmperorId(emperorId);
            newRequest.setConsultantId(Integer.parseInt(selectedConsultantId));

            // Save the new request to the database
            RequestDAO requestDAO = new RequestDAO();
            requestDAO.insertRequest(newRequest);

            // Update the selected region information
            ViewUtil.updateSelectedRegionInfo(request, selectedRegionId);
        }

        // Forward to the Emperor.jsp
        RequestDispatcher rd = request.getRequestDispatcher("empWorld.jsp");
        rd.forward(request, response);
    }
}
