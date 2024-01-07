<%@page import="com.nhat.dao.RequestDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Request"%>
<%@page import="com.nhat.util.ViewUtil"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>World</title>
    <link rel="stylesheet" type="text/css" href="css/main_style.css.css">
</head>
<body>
    <div id="leftArea">
        <jsp:include page="consLeftArea.jsp" />
    </div>
    <div id="rightArea">
        <h2>Region</h2>
        <jsp:include page="regionView.jsp" />
    </div>
</body>
</html>