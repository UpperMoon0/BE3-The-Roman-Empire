<%@page import="model.Reply"%> <!-- Import the Reply model -->
<%@page import="com.nhat.util.ViewUtil"%>
<%@page import="java.util.List"%>
<%@page import="model.Report"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Emperor Menu</title>
    <link rel="stylesheet" type="text/css" href="css/main_style.css.css">
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
                        int displayCount = Math.min(5, reports.size()); // Display maximum 5 reports

                        for (int i = 0; i < displayCount; i++) {
                            Report report = reports.get(i);
                %>
                            <option value="<%= report.getId() %>"><%= report.getDescription() %></option>
                <%
                        }
                    }
                %>
            </select>
        </form>

        <div id="selectedReportInfo">
            <%
                // Retrieve the selected report from the request
                Report selectedReport = (Report) request.getAttribute("selectedReport");
                if (selectedReport != null) {
            %>
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
