package com.nhat.util;

import com.nhat.dao.ConsultantDAO;
import com.nhat.dao.RegionDAO;
import com.nhat.dao.ReportDAO;
import com.nhat.dao.RequestDAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Consultant;
import model.Region;
import model.Report;
import model.Request;

/**
 * Utility class for updating views with data from DAOs.
 * 
 * This class provides static methods to update lists of consultants and regions
 * in the request attributes, making the data available for rendering in JSP pages.
 * 
 * @author Nhat
 */
public class ViewUtil {

    /**
     * Updates the list of consultants in the request attribute.
     *
     * @param request the HttpServletRequest
     */
    public static void updateConsultantsList(HttpServletRequest request) {
        ConsultantDAO consultantDAO = new ConsultantDAO();
        List<Consultant> consultants = consultantDAO.getAllConsultants();

        request.setAttribute("consultants", consultants);
    }

    /**
     * Updates the list of regions in the request attribute.
     *
     * @param request the HttpServletRequest
     */
    public static void updateRegionsList(HttpServletRequest request) {
        RegionDAO regionDAO = new RegionDAO();
        List<Region> regions = regionDAO.getAllRegions();

        request.setAttribute("regions", regions);
    }
    
    /**
     * Updates the selected region information in the request attribute.
     *
     * @param request the HttpServletRequest
     * @param selectedRegionId the ID of the selected region
     */
    public static void updateSelectedRegionInfo(HttpServletRequest request, int selectedRegionId) {
        RegionDAO regionDAO = new RegionDAO();
        Region selectedRegion = regionDAO.getRegionById(selectedRegionId);

        request.setAttribute("selectedRegion", selectedRegion);
    }
    
    /**
    * Updates the list of requests in the request attribute for the current consultant.
    *
    * @param request the HttpServletRequest
    */
    public static void updateRequestsList(HttpServletRequest request) {
        Object consultantIdObject = request.getSession().getAttribute("id");

        if (consultantIdObject != null && consultantIdObject instanceof Integer) {
            int consultantId = (Integer) consultantIdObject;

            RequestDAO requestDAO = new RequestDAO();
            List<Request> requests = requestDAO.getRequestsByConsultantId(consultantId);

            request.setAttribute("requests", requests);
        } else {
            System.out.println("Consultant ID not found in the session.");
        }
    }
   
    /**
     * Updates the list of reports in the request attribute.
     *
     * @param request the HttpServletRequest
     */
    public static void updateReportsList(HttpServletRequest request) {
        ReportDAO reportDAO = new ReportDAO();
        List<Report> reports = reportDAO.getAllReports();

        request.setAttribute("reports", reports);
    }
    
    /**
     * Updates the list of reports for a specific consultant in the request attribute.
     *
     * @param request the HttpServletRequest
     * @param consultantId the ID of the consultant
     */
    public static void updateConsultantReportsList(HttpServletRequest request, int consultantId) {
        ReportDAO reportDAO = new ReportDAO();
        List<Report> consultantReports = reportDAO.getReportsByConsultantId(consultantId);

        request.setAttribute("consultantReports", consultantReports);
    }
}
