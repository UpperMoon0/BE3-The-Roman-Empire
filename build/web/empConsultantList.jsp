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
        <form method="post" action="EmpConsultantList">
            <!-- Create a list box with options from the server -->
            <select name="selectedConsultantId" size="5" onchange="this.form.submit()">
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
        </form>

        <!-- Display consultant info section -->
        <div>
            <h2>Consultant Information</h2>
            <%
                // Retrieve the selected consultant from the request
                Consultant selectedConsultant = (Consultant) request.getAttribute("selectedConsultant");

                // Display the consultant information
                if (selectedConsultant != null) {
            %>
                    <p>Name: <%= selectedConsultant.getName() %></p>
                    <p>Age: <%= selectedConsultant.getAge() %></p>
                    <p>Address: <%= selectedConsultant.getAddress() %></p>
                    <p>Number of Terms: <%= selectedConsultant.getTermNum() %></p>
                    <p>Salary: <%= selectedConsultant.getSalary() %></p>
                    <p>Noble: <%= selectedConsultant.isNoble() ? "Yes" : "No" %></p>
            <%
                }
            %>
        </div>

        <!-- Add a new consultant section -->
        <div>
            <h2>Add a New Consultant</h2>
            <form method="post" action="EmpAddConsultantController">
                <!-- Input fields for adding a new consultant -->
                <label for="addUsername">Username:</label>
                <input type="text" id="addUsername" name="username" placeholder="Username" required>
                <br>
                <label for="addPassword">Password:</label>
                <input type="password" id="addPassword" name="password" placeholder="Password" required>
                <br>
                <label for="addName">Name:</label>
                <input type="text" id="addName" name="name" placeholder="Name" required>
                <br>
                <label for="addAge">Age:</label>
                <input type="number" id="addAge" name="age" placeholder="Age" required>
                <br>
                <label for="addAddress">Address:</label>
                <input type="text" id="addAddress" name="address" placeholder="Address">
                <br>
                <label for="addTermNum">Number of Terms:</label>
                <input type="number" id="addTermNum" name="term_num" placeholder="Number of terms" required>
                <br>
                <label for="addSalary">Salary:</label>
                <input type="number" id="addSalary" name="salary" placeholder="Salary" required>
                <br>
                <label>Is Noble:</label>
                <input type="checkbox" name="noble" value="true">
                <br>
                <button type="submit">Add Consultant</button>
            </form>
        </div>
    </div> 
</body>
</html>
