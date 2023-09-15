package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.model.Account;
import vn.edu.iuh.fit.week01_lab_laminhtam_21023911.repository.AccountRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet(urlPatterns = "/ControlServlet")
public class ControlServlet extends HttpServlet {
    AccountRepository accountRepository = new AccountRepository();

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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean handleAddAccount(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long id = Long.valueOf(req.getParameter("account_id"));
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        Account account = new Account(id, fullName, password, email, phone, 1);
        if (!validateNewAccount(account)) {
            resp.getWriter().println("Account already exists");
            return false;
        }
        accountRepository.add(account);
        RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
        rd.forward(req, resp);
        return true;
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
