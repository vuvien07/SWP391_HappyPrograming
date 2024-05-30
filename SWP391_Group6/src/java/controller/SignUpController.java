/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.AccountDAO;
import dao.MentorDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Account;
import model.Mentor;
import model.User;

/**
 *
 * @author Admin
 */
public class SignUpController extends HttpServlet {

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
            out.println("<title>Servlet SignUpController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignUpController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("WEB-INF/view/user/signup.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String status = (String) request.getSession().getAttribute("status");
        AccountDAO adc = new AccountDAO();
        UserDAO udc = new UserDAO();
        MentorDAO mdc = new MentorDAO();
        try {
            if (status == null) {
                String fullname = request.getParameter("name");
                String email = request.getParameter("email");
                String username = request.getParameter("username");
                String password = request.getParameter("pass");
                String phone = request.getParameter("phone");
                String address = request.getParameter("add");
                String dob = request.getParameter("dob");
                String gender = request.getParameter("gender");
                String role = request.getParameter("role");
                Account a = adc.checkAccountExist(username, email);
                if (a != null) {
                    request.setAttribute("err", "Username or email are existed. Please try again!");
                    request.getRequestDispatcher("WEB-INF/view/user/signup.jsp").forward(request, response);
                    return;
                }
                session.setAttribute("name", fullname);
                session.setAttribute("username", username);
                session.setAttribute("pass", password);
                session.setAttribute("phone", phone);
                session.setAttribute("address", address);
                session.setAttribute("dob", dob);
                session.setAttribute("gender", gender);
                session.setAttribute("role", role);
                request.getSession().setAttribute("email", email);
                response.sendRedirect("verify");
            } else {
                String name = (String) session.getAttribute("name");
                String username = (String) session.getAttribute("username");
                String password = (String) session.getAttribute("pass");
                String phone = (String) session.getAttribute("phone");
                String addess = (String) session.getAttribute("address");
                String dob = (String) session.getAttribute("dob");
                String gender = (String) session.getAttribute("gender");
                String email = (String) session.getAttribute("email");
                String role = (String) session.getAttribute("role");
                Account newAcc = new Account();
                newAcc.setUsername(username);
                newAcc.setEmail(email);
                newAcc.setPassword(password);
                newAcc.setStatus(true);
                if (role.equals("Mentee")) {
                    newAcc.setRoleid(2);
                    adc.insert(newAcc);
                    User newMen = new User();
                    newMen.setName(name);
                    newMen.setGender("Male".equals(gender));
                    newMen.setPhone(phone);
                    newMen.setAddress(addess);
                    newMen.setDateOfBirth(java.sql.Date.valueOf(dob));
                    Account account = adc.getAccount(username, password);
                    if (account != null) {
                        newMen.setAccount(account);
                        udc.insert(newMen);
                    }
                } else {
                    newAcc.setRoleid(1);
                    adc.insert(newAcc);
                    Mentor newMentor = new Mentor();
                    newMentor.setName(name);
                    newMentor.setGender("Male".equals(gender));
                    newMentor.setPhone(phone);
                    newMentor.setAddress(addess);
                    newMentor.setDateOfBirth(java.sql.Date.valueOf(dob));
                    newMentor.setStatus(false);
                    Account account = adc.getAccount(username, password);
                    if (account != null) {
                       newMentor.setAccount(account);
                       mdc.insertDefault(newMentor);
                    }
                }
                session.removeAttribute("status");
                request.setAttribute("success", "Sign up sucessfully! You can login to our system");
                request.getRequestDispatcher("WEB-INF/view/user/login.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occured. Please try again later!");
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
