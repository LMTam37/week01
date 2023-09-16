<%@ page import="vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Account" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository.AccountRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Grant_access" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository.Grant_accessRepository" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Role" %>
<%@ page import="vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository.RoleRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
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

<%!
    public String generateAccountDetailsModal(Account curAccount, List<Role> allRoles) {
        StringBuilder modalHtml = new StringBuilder();
        modalHtml.append("<div class=\"modal fade\" id=\"modal").append(curAccount.getAccount_id()).append("\" tabindex=\"-1\" aria-labelledby=\"modalLabel\" aria-hidden=\"true\">")
                .append("<div class=\"modal-dialog\">")
                .append("<div class=\"modal-content\">")
                .append("<form action=\"ControlServlet\" method=\"post\">")
                .append("<input type=\"hidden\" name=\"action\" value=\"update-account\">")

                .append("<div class=\"modal-header\">")
                .append("<h5 class=\"modal-title\" id=\"modalLabel\">Account Details</h5>")
                .append("<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"modal\" aria-label=\"Close\"></button>")
                .append("</div>")

                .append("<div class=\"modal-body\">")
                .append("<div class=\"mb-3\">")
                .append("<label for=\"idInput\" class=\"form-label\">ID</label>")
                .append("<input class=\"form-control\" id=\"idInput\" name=\"account_id\" type=\"text\" value=\"").append(curAccount.getAccount_id()).append("\" readonly>")
                .append("</div>")
                .append("<div class=\"mb-3\">")
                .append("<label for=\"fullNameInput\" class=\"form-label\">Full Name</label>")
                .append("<input class=\"form-control\" id=\"fullNameInput\" name=\"fullName\" type=\"text\" value=\"").append(curAccount.getFullName()).append("\">")
                .append("</div>")
                .append("<div class=\"mb-3\">")
                .append("<label for=\"passwordInput\" class=\"form-label\">Password</label>")
                .append("<input class=\"form-control\" id=\"passwordInput\" name=\"password\" type=\"text\" value=\"").append(curAccount.getPassword()).append("\">")
                .append("</div>")
                .append("<div class=\"mb-3\">")
                .append("<label for=\"emailInput\" class=\"form-label\">Email</label>")
                .append("<input class=\"form-control\" id=\"emailInput\" name=\"email\" type=\"email\" value=\"").append(curAccount.getEmail()).append("\">")
                .append("</div>")
                .append("<div class=\"mb-3\">")
                .append("<label for=\"phoneInput\" class=\"form-label\">Phone</label>")
                .append("<input class=\"form-control\" id=\"phoneInput\" name=\"phone\" type=\"text\" value=\"").append(curAccount.getPhone()).append("\">")
                .append("</div>")
                .append("<p>Status:</p>")
                .append("<div class=\"form-check form-check-inline\">")
                .append("<input class=\"form-check-input\" type=\"radio\" id=\"activeRadio").append(curAccount.getAccount_id()).append("\" name=\"status\" value=\"1\" ")
                .append(curAccount.getStatus() == 1 ? "checked" : "")
                .append(">")
                .append("<label class=\"form-check-label\" for=\"activeRadio").append(curAccount.getAccount_id()).append("\">Active</label>")
                .append("</div>")
                .append("<div class=\"form-check form-check-inline\">")
                .append("<input class=\"form-check-input\" type=\"radio\" id=\"deactiveRadio").append(curAccount.getAccount_id()).append("\" name=\"status\" value=\"0\" ")
                .append(curAccount.getStatus() == 0 ? "checked" : "")
                .append(">")
                .append("<label class=\"form-check-label\" for=\"deactiveRadio").append(curAccount.getAccount_id()).append("\">Deactive</label>")
                .append("</div>")
                .append("<div class=\"form-check form-check-inline\">")
                .append("<input class=\"form-check-input\" type=\"radio\" id=\"xoaRadio").append(curAccount.getAccount_id()).append("\" name=\"status\" value=\"-1\" ")
                .append(curAccount.getStatus() == -1 ? "checked" : "")
                .append(">")
                .append("<label class=\"form-check-label\" for=\"xoaRadio").append(curAccount.getAccount_id()).append("\">Xóa</label>")
                .append("</div>");

        modalHtml.append("<p>Roles:</p>");

        List<Role> accountRoles = roleRepository.findRolesByAccountId(curAccount.getAccount_id());

        for (Role role : allRoles) {
            modalHtml.append("<div class=\"form-check form-check-inline\">")
                    .append("<input class=\"form-check-input\" id=\"account").append(curAccount.getAccount_id()).append("Role").append(role.getRole_id()).append("\" type=\"checkbox\" name=\"roles\" value=\"").append(role.getRole_id()).append("\"");

            if (accountRoles.contains(role)) {
                modalHtml.append(" checked");
            }

            modalHtml.append(">")
                    .append("<label class=\"form-check-label\" for=\"account").append(curAccount.getAccount_id()).append("Role").append(role.getRole_id()).append("\">")
                    .append(role.getRole_name())
                    .append("</label>")
                    .append("</div>");
        }

        modalHtml.append("</div>")
                .append("<div class=\"modal-footer\">")
                .append("<button type=\"button\" class=\"btn btn-secondary\" data-bs-dismiss=\"modal\">Close</button>")
                .append("<input type=\"submit\" class=\"btn btn-primary\" value=\"update account\">")
                .append("</div>")

                .append("</form>")
                .append("</div>")
                .append("</div>")
                .append("</div>");

        return modalHtml.toString();
    }
%>


<%!
    public List<Long> getGrantedRoleIdsForAccount(Long accountId) {
        List<Long> grantedRoles = new ArrayList<>();
        for (Grant_access grantAccess : allGrantAccesses) {
            if (grantAccess.getAccount_id().equals(accountId) && grantAccess.isIs_grant()) {
                grantedRoles.add(grantAccess.getRole_id());
            }
        }
        return grantedRoles;
    }
%>

<%!
    public String getRoleNameById(Long roleId) {
        for (Role role : allRoles) {
            if (role.getRole_id().equals(roleId)) {
                return role.getRole_name();
            }
        }
        return "";
    }
%>

<%!
    public String getRoleNamesForAccount(Long accountId) {
        List<String> roleNames = new ArrayList<>();
        List<Long> grantedRoleIds = getGrantedRoleIdsForAccount(accountId);
        for (Long roleId : grantedRoleIds) {
            String roleName = getRoleNameById(roleId);
            if (!roleName.isEmpty()) {
                roleNames.add(roleName);
            }
        }
        return String.join(",", roleNames);
    }
%>

<%!
    AccountRepository accountRepository = new AccountRepository();
    Grant_accessRepository grantAccessRepository = new Grant_accessRepository();
    RoleRepository roleRepository = new RoleRepository();
    List<Account> allAccount = new ArrayList<>();
    List<Grant_access> allGrantAccesses = new ArrayList<>();
    List<Role> allRoles = new ArrayList<>();
%>

<%
    allAccount = accountRepository.findAll();
    allGrantAccesses = grantAccessRepository.findAll();
    allRoles = roleRepository.findAll();
    Account account = (Account) session.getAttribute("account");
    PrintWriter printWriter = response.getWriter();
%>

<%
    Account loggedInAccount = (Account) session.getAttribute("account");
    List<Role> accountRoles = roleRepository.findRolesByAccountId(loggedInAccount.getAccount_id());

    if (loggedInAccount != null && accountRoles.contains(new Role(1l, "", "", 1))) {
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

<%
    }
%>

<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#modal">
    add account
</button>
<!-- Modal -->
<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="ControlServlet" method="post">
                <input type="hidden" name="action" value="add-account">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="modalTitle">Add New Account</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="account_idInput" class="form-label">Id</label>
                        <input type="text" class="form-control" name="account_id" id="account_idInput"
                               value="<%= allAccount.size() + 1 %>"
                        >
                        <label for="emailInput" class="form-label">Email address</label>
                        <input type="email" class="form-control" name="email" id="emailInput"
                               placeholder="name@example.com">
                        <label for="fullNameInput" class="form-label">FullName</label>
                        <input class="form-control" name="fullName" id="fullNameInput">
                        <label for="passwordInput" class="form-label">Password</label>
                        <input class="form-control" name="password" id="passwordInput">
                        <label for="phoneInput" class="form-label">Phone</label>
                        <input class="form-control" name="phone" id="phoneInput">
                        <div class="mb-3">
                            <label class="form-label">Status</label>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="status" value="1" id="activeRadio"
                                       checked>
                                <label class="form-check-label" for="activeRadio">
                                    Active
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="status" value="0" id="deactiveRadio">
                                <label class="form-check-label" for="deactiveRadio">
                                    Inactive
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="status" value="-1" id="deletedRadio">
                                <label class="form-check-label" for="deletedRadio">
                                    Deleted
                                </label>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Roles</label>
                            <% for (Role role : allRoles) { %>
                            <div class="form-check">
                                <input class="form-check-input" type="checkbox" name="roles"
                                       value="<%= role.getRole_id() %>" id="role<%= role.getRole_id() %>Checkbox">
                                <label class="form-check-label" for="role<%= role.getRole_id() %>Checkbox">
                                    <%= role.getRole_name() %>
                                </label>
                            </div>
                            <% } %>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <input type="submit" class="btn btn-primary" value="add account">
                </div>
            </form>
        </div>
    </div>
</div>

<table class="table">
    <thead>
    <th>#</th>
    <th>id</th>
    <th>fullName</th>
    <th>email</th>
    <th>phone</th>
    <th>status</th>
    <th>role</th>
    <th>action</th>
    </thead>
    <tbody>
    <%
        int length = allAccount.size();
        for (int i = 0; i < length; i++) {
            Account curAccount = allAccount.get(i);
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
        <td>
            <%= getRoleNamesForAccount(curAccount.getAccount_id()) %>
        </td>
        <td>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                    data-bs-target="#modal<%=curAccount.getAccount_id()%>">
                update
            </button>
        </td>
        <%= generateAccountDetailsModal(curAccount, allRoles)%>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

<form action="ControlServlet" method="post">
    <input type="hidden" name="action" value="logout">
    <input type="submit" class="btn btn-danger" value="logout">
</form>
</body>
</html>
