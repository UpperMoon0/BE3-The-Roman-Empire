package com.nhat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EmpMenuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonClicked = request.getParameter("buttonClicked");

        if ("world".equals(buttonClicked)) {
            // Handle "World" button click
            response.sendRedirect("empWorld.jsp"); // Replace with the actual page
        } else if ("report".equals(buttonClicked)) {
            // Handle "View reports" button click
            response.sendRedirect("empReportAndReply.jsp"); // Replace with the actual page
        } else if ("consultant".equals(buttonClicked)) {
            // Handle "My consultants" button click
            response.sendRedirect("consultantList.jsp"); // Replace with the actual page
        } else if ("logout".equals(buttonClicked)) {
            // Handle "Logout" button click
            // Invalidate the session and redirect to the login page
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("login.jsp");
        }
    }
}
