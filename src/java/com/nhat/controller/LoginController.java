package com.nhat.controller;

import com.nhat.dao.ConsultantDAO;
import com.nhat.dao.EmperorDAO; // Import the EmperorDAO class
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nhat
 */
public class LoginController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Create an instance of ConsultantDAO
        ConsultantDAO consultantDAO = new ConsultantDAO();

        // Create an instance of EmperorDAO
        EmperorDAO emperorDAO = new EmperorDAO();  

        // Get the session from the request or create a new one if it doesn't exist
        HttpSession session = request.getSession();

        if ("consultant".equals(role) && consultantDAO.checkLogin(username, password)) {
            // Set session attributes for the consultant
            session.setAttribute("id", consultantDAO.getConsultantIdByUsername(username));
            session.setAttribute("role", "consultant");

            response.sendRedirect("consWorld.jsp");
        } else if ("emperor".equals(role) && emperorDAO.checkLogin(username, password)) { 
            session.setAttribute("id", emperorDAO.getEmperorIdByUsername(username));
            session.setAttribute("role", "emperor");

            response.sendRedirect("empWorld.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }
}
