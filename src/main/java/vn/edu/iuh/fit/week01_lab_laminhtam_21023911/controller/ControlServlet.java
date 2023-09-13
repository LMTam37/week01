package vn.edu.iuh.fit.week01_lab_laminhtam_21023911.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        PrintWriter printWriter = resp.getWriter();
        String action = req.getParameter("action");

        switch (action) {
            case "login":
                String username = req.getParameter("username");
                String password = req.getParameter("password");
                Optional<Account> account = accountRepository.findByEmail(username);
                if (account.isEmpty()) {
                    printWriter.println("Wrong email");
                } else {
                    if (!validatePassword(password, account.get())) {
                        printWriter.println("Wrong password");
                    } else {
                        req.setAttribute("account", account.get());
                        RequestDispatcher rd = req.getRequestDispatcher("dashboard.jsp");
                        rd.forward(req, resp);
                    }
                }
                break;
        }
    }

    private boolean validatePassword(String inputPassword, Account account) {
        if (inputPassword.equals(account.getPassword())) {
            return true;
        }
        return false;
    }


}
