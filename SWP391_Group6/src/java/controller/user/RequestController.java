/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import controller.authorization.BaseAuthController;
import dal.NotificationDBContext;
import dal.SkillDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Mentor;
import model.MentorNotification;
import model.Skill;
import service.user.UserRequestService;
import util.UserDataDetail;
import util.Util;

/**
 *
 * @author Admin
 */
public class RequestController extends BaseAuthController {

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
            out.println("<title>Servlet RequestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RequestController at " + request.getContextPath() + "</h1>");
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
     * @param account
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        //            if (!Util.checkValidTimeInDay()) {
//                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Out of work time");
//            } else {
        SkillDBContext skillDAO = new SkillDBContext();
        Mentor mentor = (Mentor) request.getSession().getAttribute("mentor");
        List<Skill> skills = skillDAO.listByMentor(mentor.getId());
        request.getSession().setAttribute("menSkills", skills);
        request.getRequestDispatcher("WEB-INF/view/user/request.jsp").forward(request, response);
//            }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @param account
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        UserDataDetail udd = new UserDataDetail();
        UserRequestService userRequestService = new UserRequestService();
        String title = request.getParameter("title");
        String deadlineTime = request.getParameter("deadlineTime");
        deadlineTime += ":00";
        String content = request.getParameter("content");
        String[] skills = request.getParameterValues("skills");
        udd.putAttribute("title", title);
        udd.putAttribute("deadlineTime", deadlineTime);
        udd.putAttribute("content", content);
        udd.putAttribute("skills", skills);
        try {
            userRequestService.processCreateRequest(udd, request);
        } catch (ParseException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("WEB-INF/view/user/request.jsp").forward(request, response);
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
