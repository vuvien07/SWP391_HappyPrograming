/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.AccountDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author Admin
 */
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = "", password = "", rememberMe = null;
        if (cookies != null) {
            for (Cookie cooky : cookies) {
                if (cooky.getName().equals("username")) {
                    username = cooky.getValue();
                }
                if (cooky.getName().equals("password")) {
                    password = cooky.getValue();
                }
                if(cooky.getName().equals("rememberMe")){
                    rememberMe = cooky.getValue();
                }
            }
        }
        request.setAttribute("user", username);
        request.setAttribute("pass", password);
        request.setAttribute("rememberMe", rememberMe);
        request.getRequestDispatcher("WEB-INF/view/user/login.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get thong tin nguoi dung nhap
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("rememberMe");
        AccountDBContext dao = new AccountDBContext();
        //tim xem co acc phu hop username vs pass ng dung nhap k
        Account account = dao.getAccount(username, password);
        //acc==null ==> tk hoac mk sai ==> set ve loi==> login.jsp
        //nguoc lai dang nhap thanh cong ==> home.jsp
        if (account == null) {
            request.setAttribute("error", "Invalid username or password. Please try again");
            request.getRequestDispatcher("WEB-INF/view/user/login.jsp").forward(request, response);
        } else {
            Cookie c_user = new Cookie("username", username);
            Cookie c_pass = new Cookie("password", password);
            Cookie c_remember = new Cookie("rememberMe", rememberMe);
            if (rememberMe != null) {

                c_user.setMaxAge(3600 * 24 * 7);
                c_pass.setMaxAge(3600 * 24 * 7);
                c_remember.setMaxAge(3600 * 24 * 7);
            } else {
                c_user.setMaxAge(0);
                c_pass.setMaxAge(0);
                c_remember.setMaxAge(0);
            }
            response.addCookie(c_user);
            response.addCookie(c_pass);
            response.addCookie(c_remember);
            UserDBContext udc = new UserDBContext();
            request.getSession().setAttribute("user", udc.getUserById(account.getId()));
            request.getSession().setAttribute("account", account);
            response.sendRedirect("home");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
