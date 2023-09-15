<%@ page import="vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Account" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository.AccountRepository" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
</head>
<body>
<%!
    public String getStatusString(int statusCode) {
        String status = "";
        switch (statusCode) {
            case 1:
                status = "active";
                break;
            case 0:
                status = "deactive";
                break;
            case -1:
                status = "xóa";
                break;
        }
        return status;
    }
%>

<%
    Account account = (Account) request.getAttribute("account");
    PrintWriter printWriter = response.getWriter();
    AccountRepository accountRepository = new AccountRepository();
    List<Account> accounts = accountRepository.findAll();
%>
<p>id: <%= account.getAccount_id() %>
</p>
<p>fullName: <%= account.getFullName() %>
</p>
<p>email: <%= account.getEmail() %>
</p>
<p>phone: <%= account.getPhone() %>
</p>
<p>status: <%= getStatusString(account.getStatus()) %>
</p>
<table class="table">
    <thead>
    <th>#</th>
    <th>id</th>
    <th>fullName</th>
    <th>email</th>
    <th>phone</th>
    <th>status</th>
    </thead>
    <tbody>
    <%
        int length = accounts.size();
        for (int i = 0; i < length; i++) {
            Account curAccount = accounts.get(i);
    %>
    <tr>
        <td>
            <%= i + 1%>
        </td>
        <td>
            <%= curAccount.getAccount_id() %>
        </td>
        <td>
            <%= curAccount.getFullName() %>
        </td>
        <td>
            <%= curAccount.getEmail() %>
        </td>
        <td>
            <%= curAccount.getPhone() %>
        </td>
        <td>
            <%= getStatusString(curAccount.getStatus())%>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>
