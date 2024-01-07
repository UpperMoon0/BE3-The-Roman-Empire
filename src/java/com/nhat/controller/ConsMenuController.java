/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhat.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nhat
 */
public class ConsMenuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonClicked = request.getParameter("buttonClicked");

        if ("world".equals(buttonClicked)) {
            // Handle "World" button click
            response.sendRedirect("consWorld.jsp"); // Replace with the actual page
        } else if ("viewRequest".equals(buttonClicked)) {
            // Handle "View reports" button click
            response.sendRedirect("consRequestAndReport.jsp"); // Replace with the actual page
        } else if ("reportHistory".equals(buttonClicked)) {
            // Handle "My consultants" button click
            response.sendRedirect("consReportHistory.jsp"); // Replace with the actual page
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
