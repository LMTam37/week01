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
    String status = "";
    switch (account.getStatus()) {
        case 1:
            status = "active";
            break;
        case 2:
            status = "deactive";
            break;
        case 3:
            status = "xóa";
            break;
    }
%>
<p>id: <%= account.getAccount_id() %>
</p>
<p>fullname: <%= account.getFullName() %>
</p>
<p>email: <%= account.getEmail() %>
</p>
<p>phone: <%= account.getPhone() %>
</p>
<p>status: <%= status %>
</p>
</body>
</html>
