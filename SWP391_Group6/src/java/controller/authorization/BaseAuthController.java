/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.authorization;

import dal.RoleDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Account;

/**
 *
 * @author Admin
 */
public abstract class BaseAuthController extends HttpServlet {
    
    private Account getAuthentication(HttpServletRequest req) {
        Account account = (Account) req.getSession().getAttribute("account");
        return account;
    }

    private boolean isAuthenticated(HttpServletRequest req) {
        Account account = getAuthentication(req);
        RoleDBContext rdc = new RoleDBContext();
        return account != null && rdc.getRoleByUsernameAndUrl(account.getUsername(), req.getServletPath()) != null;
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, Account account) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            req.getSession().setMaxInactiveInterval(10000);
            doGet(req, resp, getAuthentication(req));
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthenticated(req)) {
            req.getSession().setMaxInactiveInterval(10000);
            doPost(req, resp, getAuthentication(req));
        } else {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this page");
        }
    }
}
