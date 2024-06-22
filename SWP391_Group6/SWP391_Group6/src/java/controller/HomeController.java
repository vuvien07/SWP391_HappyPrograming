/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.NotificationDBContext;
import dal.SkillDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Mentor;
import model.MentorNotification;
import model.Skill;
import model.User;
import model.UserNotification;
import util.Util;

/**
 *
 * @author Admin
 */
public class HomeController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet HomeController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        SkillDBContext sdc = new SkillDBContext();
        Account account = (Account) request.getSession().getAttribute("account");
        List<Skill> skills = sdc.listAll();
        int size = skills.size(), numPerPage = 6, page;
        String xPage = request.getParameter("page");
        if (xPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xPage);
        }
        int num = (size % numPerPage == 0 ? (size / numPerPage) : ((size / numPerPage) + 1));
        List<Object> pagedSkills = Util.listByPage((List<Object>)(List<?>)skills, xPage, numPerPage);
        if(account != null && account.getRoleid() == 2){
            NotificationDBContext notificationDBContext = new NotificationDBContext();
            User user = (User) request.getSession().getAttribute("user");
            ArrayList<UserNotification> notifications = notificationDBContext.listByUserId(user.getId());
            int numUnread = notificationDBContext.countUserUnreadNotification();
            request.setAttribute("numUnread", numUnread);
            request.setAttribute("notifications", notifications);
        }
        if(account != null && account.getRoleid() == 1){
            NotificationDBContext notificationDBContext = new NotificationDBContext();
            Mentor mentor = (Mentor) request.getSession().getAttribute("mentor");
            ArrayList<MentorNotification> notifications = notificationDBContext.listByMenId(mentor.getId());
            int numUnread = notificationDBContext.countMentorUnreadNotification();
            request.setAttribute("numUnread", numUnread);
            request.setAttribute("notifications", notifications);
        }
        request.setAttribute("num", num);
        request.setAttribute("page", page);
        request.getSession().setAttribute("pagedSkills", pagedSkills);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}