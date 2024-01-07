<%@page import="java.util.List"%>
<%@page import="model.Report"%>
<%@page import="com.nhat.util.ViewUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reports History</title>
    <link rel="stylesheet" type="text/css" href="css/main_style.css.css">
</head>
<body>
    <div id="leftArea">
        <jsp:include page="consLeftArea.jsp" />
    </div>
    <div id="rightArea">
        <h2>Reports History</h2>

        <form action="ConsReportHistory" method="post">
            <!-- Display List of Reports -->
            <div>
                <h3>Your Reports</h3>
                <select name="selectedReportId" size="5" onchange="submitForm()">
                    <% 
                        int consultantId = (int) session.getAttribute("id");
                        ViewUtil.updateConsultantReportsList(request, consultantId);

                        // Retrieve the list of reports from the request
                        List<Report> consultantReports = (List<Report>) request.getAttribute("consultantReports");

                        if (consultantReports != null) {
                            for (Report report : consultantReports) {
                    %>
                                <option value="<%= report.getId() %>"><%= report.getWrittenDate().toString() %></option>
                    <%
                            }
                        }
                    %>
                </select>

                <!-- Hidden submit button -->
                <input type="submit" id="submitBtn" style="display:none;">
            </div>
        </form>

        <!-- Display Report Details (if a report is selected) -->
        <div>
            <h3>Report Details</h3>
            <%
                // Retrieve the selected report from the request
                Report selectedReport = (Report) request.getAttribute("selectedReport");
                System.out.println("selectedReport" + selectedReport);
                // Display the report details if a report is selected
                if (selectedReport != null) {
            %>
                <!-- Existing code for displaying report details -->
                <p>Written Date: <%= selectedReport.getWrittenDate() %></p>
                <p>Delivered Date: <%= selectedReport.getDeliveredDate() %></p>
                <p>Market Value: <%= selectedReport.getMarketValue() %></p>
                <p>Growth: <%= selectedReport.getGrowth() %></p>
                <p>Revolt Rate: <%= selectedReport.getRevoltRate() %></p>
                <p>Food Supply: <%= selectedReport.getFoodSupply() %></p>
                <p>Description: <%= selectedReport.getDescription() %></p>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
