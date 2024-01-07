<%@page import="com.nhat.util.ViewUtil"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Consultant" %>
<%@ page import="java.util.Iterator" %>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Manage Consultants</title>
    <link rel="stylesheet" type="text/css" href="css/main_style.css">
</head>
<body>
    <div id="leftArea">
        <jsp:include page="empLeftArea.jsp" />
    </div>
    <div id="rightArea">
        <h1>Consultants list</h1>

        <!-- Create a form to collect the user's data -->
        <form method="post" action="ConsultantList">
            <!-- Create a list box with options from the server -->
            <select name="consultant" size="5">
                <% 
                    ViewUtil.updateConsultantsList(request);
                    List<Consultant> consultants = (List<Consultant>) request.getAttribute("consultants");
                    if (consultants != null) {
                        Iterator<Consultant> iterator = consultants.iterator();
                        while (iterator.hasNext()) {
                            Consultant consultant = iterator.next();
                %>
                            <option value="<%= consultant.getId() %>"><%= consultant.getName() %></option>
                <%
                        }
                    }
                %>
            </select>

            <!-- Create a label with the text "Add a consultant" -->
            <label>Add a consultant</label>
            <!-- Create input fields for the user to enter data -->
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="text" name="name" placeholder="Name" required>
            <input type="number" name="age" placeholder="Age (optional)">
            <input type="text" name="address" placeholder="Address (optional)">
            <input type="number" name="term_num" placeholder="Number of term" required>
            <input type="number" name="salary" placeholder="Salary" required>
            <!-- Create a true/false selector for the noble field -->
            <label>Is noble</label>
            <input type="checkbox" name="noble" value="true">
            <!-- Create a submit button to send the data -->
            <button type="submit">Add</button>
        </form>
    </div> 
</body>
</html>


