<%@page import="com.nhat.dao.RegionDAO"%>
<%@page import="model.Consultant"%>
<%@page import="model.Region"%>
<%@page import="com.nhat.util.ViewUtil"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <h2>Region</h2>
        <jsp:include page="regionView.jsp" />

        <h3>Request a report of this region</h3>
        <form action="EmpRequest" method="post">
            <%
                // Retrieve the regionId from the session attribute
                Integer regionId = (Integer) session.getAttribute("selectedRegionId");
            %>
            <input type="hidden" name="selectedRegionId" value="<%= regionId %>">
            <select name="selectedConsultant" size="5"> <!-- Set the size attribute to limit the visible options -->
                <% 
                    // Use the utility method to update the consultants list
                    ViewUtil.updateConsultantsList(request);

                    // Retrieve the list of consultants from the request
                    List<Consultant> consultants = (List<Consultant>) request.getAttribute("consultants");

                    if (consultants != null) {
                        int displayCount = Math.min(5, consultants.size()); // Display maximum 5 consultants

                        for (int i = 0; i < displayCount; i++) {
                            Consultant consultant = consultants.get(i);
                %>
                            <option value="<%= consultant.getId() %>"><%= consultant.getName() %></option>
                <%
                        }
                    }
                %>
            </select>
            <br>
            <label for="period">Period (month): </label>
            <input type="number" id="period" name="period" required>
            <br>
            <button type="submit">Request</button>
        </form>
    </div>
</body>
</html>
