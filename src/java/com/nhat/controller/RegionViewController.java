package com.nhat.controller;

import com.nhat.dao.RegionDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import model.Region;

/**
 * Controller for handling actions related to regions in the Emperor's World.
 * This class handles both GET and POST requests.
 * Responsible for updating selected region information and storing the selected region ID in the session.
 * Forwards the request to the appropriate JSP page.
 * 
 * @author Nhat
 */
public class RegionViewController extends HttpServlet {

    /**
     * Handles GET requests related to regions.
     * Forwards the request to the Emperor World JSP page.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If an error occurs during servlet execution.
     * @throws IOException      If an I/O error occurs during the forwarding process.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to the Emperor World JSP page
        RequestDispatcher rd = request.getRequestDispatcher("empWorld.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles POST requests related to regions.
     * Updates the selected region information and stores the selected region ID in the session.
     * Forwards the request to the Emperor World JSP page.
     *
     * @param request  The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @throws ServletException If an error occurs during servlet execution.
     * @throws IOException      If an I/O error occurs during the forwarding process.
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Logic for handling POST requests related to regions

        // Retrieve the selected region ID parameter from the request
        String selectedRegionId = request.getParameter("selectedRegionId");
        HttpSession session = request.getSession();

        if (selectedRegionId != null) {
            RegionDAO rDAO = new RegionDAO();
            int regionId = Integer.parseInt(selectedRegionId);
            Region selectedRegion = rDAO.getRegionById(regionId);
            request.setAttribute("selectedRegion", selectedRegion);
            
            session.setAttribute("selectedRegionId", regionId);
        }

        // Forward to the Emperor World JSP page
        RequestDispatcher rd;
        if ("emperor".equals(session.getAttribute("role"))) {
            rd = request.getRequestDispatcher("empWorld.jsp");
        } else {
            rd = request.getRequestDispatcher("consWorld.jsp");
        }
        
        rd.forward(request, response);
    }
}
