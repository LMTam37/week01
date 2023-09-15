package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Account;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Grant_access;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Role;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository.AccountRepository;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository.Grant_accessRepository;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository.RoleRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/ControlServlet")
public class ControlServlet extends HttpServlet {
    AccountRepository accountRepository = new AccountRepository();
    Grant_accessRepository grantAccessRepository = new Grant_accessRepository();
    RoleRepository roleRepository = new RoleRepository();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");

            switch (action) {
                case "login":
                    handleLogin(req, resp);
                    break;
                case "add-account":
                    handleAddAccount(req, resp);
                    break;
                case "update-account":
                    handleUpdateAccount(req, resp);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Account extractAccountFromRequest(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("account_id"));
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        int status = Integer.parseInt(req.getParameter("status"));
        return new Account(id, fullName, password, email, phone, status);
    }

    private boolean handleUpdateAccount(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Account account = extractAccountFromRequest(req);

            String[] selectedRoles = req.getParameterValues("roles");

            updateGrantAccess(account.getAccount_id(), selectedRoles);

            accountRepository.updateAccount(account);

            RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void updateGrantAccess(Long accountId, String[] selectedRoles) throws Exception {
        List<Role> allRoles = roleRepository.findAll();

        for (Role role : allRoles) {
            Long roleId = role.getRole_id();
            boolean isCheck = selectedRoles != null && Arrays.asList(selectedRoles).contains(roleId.toString());

            Grant_access grantAccess = new Grant_access(accountId, roleId, isCheck, "");
            grantAccessRepository.updateIs_grant(grantAccess);
        }
    }

    private boolean handleAddAccount(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Account account = extractAccountFromRequest(req);

        String[] selectedRoles = req.getParameterValues("roles");
        addGrantAccess(account.getAccount_id(), selectedRoles);

        if (!validateNewAccount(account)) {
            resp.getWriter().println("Account already exists");
            return false;
        }
        accountRepository.add(account);
        RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
        rd.forward(req, resp);
        return true;
    }

    private void addGrantAccess(Long accountId, String[] selectedRoles) throws Exception {
        List<Role> allRoles = roleRepository.findAll();

        for (Role role : allRoles) {
            Long roleId = role.getRole_id();
            boolean isCheck = Arrays.asList(selectedRoles).contains(roleId.toString());

            Grant_access grantAccess = new Grant_access(accountId, roleId, isCheck, "");
            grantAccessRepository.add(grantAccess);
        }
    }

    private boolean validateNewAccount(Account account) throws Exception {
        if (accountRepository.findById(account.getAccount_id()).isPresent()) {
            return false;
        }
        return true;
    }

    private boolean handleLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Optional<Account> account = accountRepository.findByEmail(username);
        if (account.isEmpty()) {
            resp.getWriter().println("Wrong email");
            return false;
        }
        if (!validatePassword(password, account.get())) {
            resp.getWriter().println("Wrong password");
            return false;
        }
        HttpSession session = req.getSession();
        session.setAttribute("account", account.get());
        RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
        rd.forward(req, resp);
        return true;
    }

    private boolean validatePassword(String inputPassword, Account account) {
        if (inputPassword.equals(account.getPassword())) {
            return true;
        }
        return false;
    }


}
