<%@ page import="vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Account" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Account account = (Account) request.getAttribute("account");
        PrintWriter printWriter = response.getWriter();
    %>
</body>
</html>
