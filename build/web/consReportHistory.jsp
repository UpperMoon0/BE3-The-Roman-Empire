<%@page import="com.nhat.dao.ReplyDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Report"%>
<%@page import="model.Reply"%>
<%@page import="com.nhat.util.ViewUtil"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reports History</title>
    <link rel="stylesheet" type="text/css" href="css/main_style.css">
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
                <select name="selectedReportId" size="5" onchange="this.form.submit()">
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
                // Retrieve the reply for the selected report
                Reply replyForReport = (Reply) request.getAttribute("replyForReport");

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

                <!-- Display reply details if a reply exists for the selected report -->
                <h3>Reply Details</h3>
                    <% 
                        if (replyForReport != null) {
                            // Check if deliveredDate is null, and if it is, set it to the current date
                            if (replyForReport.getDeliveredDate() == null) {
                                // Update deliveredDate using the DAO method
                                ReplyDAO replyDAO = new ReplyDAO();
                                replyDAO.updateDeliveredDate(replyForReport.getId(), new java.sql.Date(System.currentTimeMillis()));

                                // Refresh the replyForReport object to reflect the updated deliveredDate
                                replyForReport = replyDAO.getReplyById(replyForReport.getId());
                            }
                    %>
                        <p>Reply Written Date: <%= replyForReport.getWrittenDate() %></p>
                        <p>Reply Delivered Date: <%= replyForReport.getDeliveredDate() %></p>
                        <p>Reply Content: <%= replyForReport.getContent() %></p>
                    <% } else { %>
                        <p>No reply for this report</p>
                    <% } %>
                <%
                    }
                %>
        </div>
    </div>
</body>
</html>
