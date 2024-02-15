package com.nhat.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Controller class for handling consultant menu actions.
 */
public class ConsMenuController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ConsMenuController.class.getName());

    /**
     * Handles HTTP GET requests.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String buttonClicked = request.getParameter("buttonClicked");

            if (null == buttonClicked) {
                // Handle unrecognized button click
                LOGGER.warning("Unrecognized button click: " + buttonClicked);
            } else switch (buttonClicked) {
                case "world":
                    response.sendRedirect("consWorld.jsp");
                    break;
                case "viewRequest":
                    response.sendRedirect("consRequestAndReport.jsp");
                    break;
                case "reportHistory":
                    response.sendRedirect("consReportHistory.jsp");
                    break;
                case "logout":
                    handleLogout(request, response);
                    break;
                default:
                    // Handle unrecognized button click
                    LOGGER.warning("Unrecognized button click: " + buttonClicked);
                    break;
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error processing request", e);
        }
    }

    /**
     * Handles the logout action.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws IOException if an I/O error occurs during redirection
     */
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Invalidate the session and redirect to the login page
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}
