package com.nhat.controller;

import com.nhat.dao.ConsultantDAO;
import com.nhat.util.ViewUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Consultant;

public class EmpConsultantListController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the selectedConsultantId from the request
        String selectedConsultantIdString = request.getParameter("selectedConsultantId");

        // Check if selectedConsultantIdString is not null or empty
        if (selectedConsultantIdString != null && !selectedConsultantIdString.isEmpty()) {
            // Convert the selectedConsultantIdString to an integer
            int selectedConsultantId = Integer.parseInt(selectedConsultantIdString);

            // Get the consultant from the DAO using the selectedConsultantId
            ConsultantDAO cDao = new ConsultantDAO();
            Consultant selectedConsultant = cDao.getConsultantById(selectedConsultantId);

            // Set the request attribute "selectedConsultant" with the consultant obtained from the DAO
            request.setAttribute("selectedConsultant", selectedConsultant);
        }

        // Use the utility method to update the list of consultants
        ViewUtil.updateConsultantsList(request);

        // Forward to the JSP page
        request.getRequestDispatcher("empConsultantList.jsp").forward(request, response);
    }
}

