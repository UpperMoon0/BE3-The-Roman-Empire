package com.nhat.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.http.HttpServlet;

/**
 * Base controller for consultant-related actions.
 */
public abstract class BaseConsController extends HttpServlet {
    protected void redirectToLoginIfNotConsultant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession(false);

        // Check if the session exists and the role is not "consultant"
        if (session == null || !"consultant".equals(session.getAttribute("role"))) {
            // Invalidate the session
            if (session != null) {
                session.invalidate();
            }

            // Redirect to login.jsp
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
