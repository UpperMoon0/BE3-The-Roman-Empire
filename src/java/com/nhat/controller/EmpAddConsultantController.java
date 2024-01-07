package com.nhat.controller;

import com.nhat.dao.ConsultantDAO;
import model.Consultant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmpAddConsultantController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(EmpAddConsultantController.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            handleConsultantCreation(request, response);
        } catch (IOException | ServletException e) {
            LOGGER.log(Level.SEVERE, "Error processing consultant creation", e);
        }

        // Forward the request to the JSP page for displaying the list of consultants
        request.getRequestDispatcher("empConsultantList.jsp").forward(request, response);
    }

    /**
     * Handles the creation of a new consultant.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException      if an I/O error occurs
     * @throws ServletException if a servlet-specific error occurs
     */
    private void handleConsultantCreation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Extract form parameters for creating a new consultant
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        int termNum = Integer.parseInt(request.getParameter("term_num"));
        int salary = Integer.parseInt(request.getParameter("salary"));
        boolean noble = request.getParameter("noble") != null; // Checkbox value will be null if not selected
        int regionId = 1; // Change this to the appropriate value

        // Create a new Consultant object
        Consultant newConsultant = new Consultant(username, password, name, age, address, termNum, salary, noble, regionId);

        // Insert the new consultant into the database
        ConsultantDAO consultantDAO = new ConsultantDAO();
        consultantDAO.insertConsultant(newConsultant);
    }
}
