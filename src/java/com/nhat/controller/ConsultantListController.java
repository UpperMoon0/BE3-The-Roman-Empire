package com.nhat.controller;

import com.nhat.dao.ConsultantDAO;
import com.nhat.util.ViewUtil;
import model.Consultant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConsultantListController extends HttpServlet {
    private final ConsultantDAO consultantDAO = new ConsultantDAO();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {   
        RequestDispatcher rd = request.getRequestDispatcher("consultantList.jsp");
        rd.forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String address = request.getParameter("address");
        int termNum = Integer.parseInt(request.getParameter("term_num"));
        int salary = Integer.parseInt(request.getParameter("salary"));
        boolean noble = request.getParameter("noble") != null; // Checkbox value will be null if not selected
        int kingdomId = 1;
        
        // Create a new Consultant object
        Consultant newConsultant = new Consultant(username, password, name, age, address, termNum, salary, noble, kingdomId);
        
        // Insert the new consultant into the database
        consultantDAO.insertConsultant(newConsultant);
        // Update the list of consultants in the request
        ViewUtil.updateConsultantsList(request);

        // Forward the request to the JSP page
        RequestDispatcher rd = request.getRequestDispatcher("consultantList.jsp");
        rd.forward(request, response);
    }
}

