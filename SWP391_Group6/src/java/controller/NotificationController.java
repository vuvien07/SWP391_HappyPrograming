/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.NotificationDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Mentor;
import model.MentorNotification;
import model.User;
import model.UserNotification;
import service.PaginationService;

/**
 *
 * @author Admin
 */
public class NotificationController extends HttpServlet {
   
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
            out.println("<title>Servlet NotificationController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NotificationController at " + request.getContextPath () + "</h1>");
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
        PaginationService paginationService = new PaginationService();
        Account account = (Account) request.getSession().getAttribute("account");
        NotificationDBContext notificationDBContext = new NotificationDBContext();
        if(account.getRoleid() == 1){
           Mentor mentor = (Mentor) request.getSession().getAttribute("mentor");
            ArrayList<MentorNotification> notifications = notificationDBContext.listByMenId(mentor.getId());
            int numUnread = notificationDBContext.countMentorUnreadNotificationById(mentor.getId());
            request.setAttribute("numUnread", numUnread);
            paginationService.pagingList(request, (ArrayList<Object>)(ArrayList<?>)notifications, "notifications");
        }
        if(account.getRoleid() == 2){
            User user = (User) request.getSession().getAttribute("user");
            ArrayList<UserNotification> notifications = notificationDBContext.listByUserId(user.getId());
            int numUnread = notificationDBContext.countUserUnreadNotificationByUserId(user.getId());
            request.setAttribute("numUnread", numUnread);
           paginationService.pagingList(request, (ArrayList<Object>)(ArrayList<?>)notifications, "notifications");
        }
        
        request.getRequestDispatcher("WEB-INF/view/notification.jsp").forward(request, response);
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
