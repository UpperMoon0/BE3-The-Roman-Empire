<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.nhat.dao.ReportDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="com.nhat.dao.RequestDAO"%>
<%@page import="com.nhat.dao.RegionDAO"%>
<%@page import="com.nhat.dao.ConsultantDAO"%>
<%@page import="model.Reply"%> <!-- Import the Reply model -->
<%@page import="com.nhat.util.ViewUtil"%>
<%@page import="java.util.List"%>
<%@page import="model.Report"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Emperor Menu</title>
    <link rel="stylesheet" type="text/css" href="css/main_style.css">
</head>
<body>
    <div id="leftArea">
        <jsp:include page="empLeftArea.jsp" />
    </div>
    <div id="rightArea">
        <h2>Reports to me</h2>
        <form id="reportForm" action="EmpReport" method="post">
            <select name="selectedReport" size="5" onchange="this.form.submit()">
                <%
                    // Use the utility method to update the reports list
                    ViewUtil.updateReportsList(request);

                    // Retrieve the list of reports from the request
                    List<Report> reports = (List<Report>) request.getAttribute("reports");

                    if (reports != null) {
                        ConsultantDAO cDAO = new ConsultantDAO();
                        RequestDAO rDAO = new RequestDAO();
                        int displayCount = Math.min(5, reports.size()); // Display maximum 5 reports

                        for (int i = 0; i < displayCount; i++) {
                            Report report = reports.get(i);
                %>
                <option value="<%= report.getId() %>"><%= "Report by " + cDAO.getConsultantById(report.getConsultantId()).getName() %></option>
                <%
                        }
                    }
                %>
            </select>
        </form>

        <div id="selectedReportInfo">
            <%
                Report selectedReport = (Report) request.getAttribute("selectedReport");
                if (selectedReport != null) {
                    // Update the delivered date to today if it is null
                    if (selectedReport.getDeliveredDate() == null) {
                        ReportDAO reportDAO = new ReportDAO();
                        Date today = new Date();
                        java.sql.Date sqlToday = new java.sql.Date(today.getTime());
                        reportDAO.updateDeliveredDate(selectedReport.getId(), sqlToday);

                        // Refresh the selected report to reflect the updated delivered date
                        selectedReport.setDeliveredDate(sqlToday);
                    }
            %>
                    <!-- Display the selected report details with updated delivered date -->
                    <h3>Selected Report Info</h3>
                    <p>Written Date: <%= selectedReport.getWrittenDate() %></p>
                    <p>Delivered Date: <%= selectedReport.getDeliveredDate() %></p>
                    <p>Market Value: <%= selectedReport.getMarketValue() %></p>
                    <p>Growth Rate: <%= selectedReport.getGrowth() %></p>
                    <p>Revolt Rate: <%= selectedReport.getRevoltRate() %></p>
                    <p>Food Supply: <%= selectedReport.getFoodSupply() %></p>
                    <p>Description: <%= selectedReport.getDescription() %></p>

                    <!-- New section for reply -->
                    <div id="replySection">
                        <h3>Reply to this report</h3>
                        <form action="EmpReply" method="post">
                            <input type="hidden" name="reportId" value="<%= selectedReport.getId() %>">
                            <label for="replyContent">Reply Content:</label>
                            <textarea id="replyContent" name="replyContent" rows="4" cols="50"></textarea>
                            <br>
                            <button type="submit">Send Reply</button>
                        </form>
                    </div>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
