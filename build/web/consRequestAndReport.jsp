<%@page import="com.nhat.dao.RequestDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Request"%>
<%@page import="com.nhat.util.ViewUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Requests</title>
    <link rel="stylesheet" type="text/css" href="css/main_style.css.css">
</head>
<body>
    <div id="leftArea">
        <jsp:include page="consLeftArea.jsp" />
    </div>
    <div id="rightArea">
        <h2>View Requests</h2>

        <form action="ConsRequest" method="post">
            <select name="selectedRequest" size="5" onchange="this.form.submit()">
                <% 
                    ViewUtil.updateRequestsList(request);

                    List<Request> requests = (List<Request>) request.getAttribute("requests");

                    if (requests != null) {
                        int displayCount = Math.min(5, requests.size());

                        for (int i = 0; i < displayCount; i++) {
                            Request eRequest = requests.get(i);
                %>
                            <option value="<%= eRequest.getId() %>"><%= eRequest.getRegionId() + " " + eRequest.getPeriod() + " " + eRequest.getWrittenDate().toString()%></option>
                <%
                        }
                    }
                %>
            </select>
            <br>
        </form>

        <!-- Display Request Details -->
        <div>
            <%
                // Retrieve the selected request from the request
                Request selectedRequest = (Request) request.getAttribute("selectedRequest");

                // Check if delivered date is null and update if necessary
                if (selectedRequest != null && selectedRequest.getDeliveredDate() == null) {
                    // Use the method to update delivered date with the current date
                    selectedRequest.setDeliveredDate(new java.sql.Date(System.currentTimeMillis()));
                    int requestId = selectedRequest.getId();
                    RequestDAO rDAO = new RequestDAO();
                    rDAO.updateDeliveredDate(requestId, selectedRequest.getDeliveredDate());
                }
                
                // Display the request details
                if (selectedRequest != null) {
            %>
                    <h3>Request Details</h3>
                    <!-- Existing code for displaying request details -->
                    <p>Written Date: <%= selectedRequest.getWrittenDate() %></p>
                    <p>Delivered Date: <%= selectedRequest.getDeliveredDate() %></p>
                    <p>Region ID: <%= selectedRequest.getRegionId() %></p>
                    <p>Period: <%= selectedRequest.getPeriod() %></p>
            <%
                }
            %>
        </div>

        <!-- Create a report for this request -->
        <div>
            <%
                // Show the "Create a report for this request" section only if a request is selected
                if (selectedRequest != null) {
            %>
                    <h3>Create a report for this request</h3>
                    <form action="ConsReport" method="post">
                        <!-- Input fields for report information -->
                        <label for="marketValue">Market Value:</label>
                        <input type="text" id="marketValue" name="marketValue" required>
                        <br>
                        <label for="growth">Growth:</label>
                        <input type="text" id="growth" name="growth" required>
                        <br>
                        <label for="revoltRate">Revolt Rate:</label>
                        <input type="text" id="revoltRate" name="revoltRate" required>
                        <br>
                        <label for="foodSupply">Food Supply:</label>
                        <input type="text" id="foodSupply" name="foodSupply" required>
                        <br>
                        <label for="description">Description:</label>
                        <textarea id="description" name="description" rows="4" cols="50" required></textarea>
                        <br>
                        <button type="submit">Send Report</button>
                    </form>
            <%
                }
            %>
        </div>
    </div>
</body>
</html>
