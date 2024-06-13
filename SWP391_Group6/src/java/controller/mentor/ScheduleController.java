/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentor;

import controller.authorization.BaseAuthController;
import dal.MentorDBContext;
import dal.SessionDBContext;
import dal.SkillDBContext;
import dal.SlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Mentor;
import model.Session;
import model.Skill;
import model.Slot;
import service.ViewScheduleService;
import service.mentor.MentorService;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class ScheduleController extends BaseAuthController {

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
            out.println("<title>Servlet ScheduleController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ScheduleController at " + request.getContextPath() + "</h1>");
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
        SessionDBContext sessionDBContext = new SessionDBContext();
        ViewScheduleService viewScheduleService = new ViewScheduleService();
        Account mentorAccount = (Account) request.getSession().getAttribute("account");
        MentorDBContext menDBContext = new MentorDBContext();
        Mentor mentor = menDBContext.getByAccountId(mentorAccount.getId());
        if (!mentor.isStatus()) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "You cannot perform this function due to your CV checking is processing or rejected");
            return;
        }
        ArrayList<Session> sessions = sessionDBContext.listByMentorId(mentor.getId());
        SlotDBContext slotDAO = new SlotDBContext();
        ArrayList<Slot> slots = slotDAO.listAll();
        viewScheduleService.viewSchedule(request);
        request.getSession().setAttribute("slots", slots);
        request.setAttribute("mentor", mentor);
        request.getSession().setAttribute("sessions", sessions);
        request.getRequestDispatcher("WEB-INF/view/mentor/createschedule.jsp").forward(request, response);
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
        MentorService mentorService = new MentorService();
        UserDataDetail udd = new UserDataDetail();
        Account menAccount = (Account) request.getSession().getAttribute("account");
        String freeDate = request.getParameter("scheduleDate");
        String slot = request.getParameter("freeSlot");
        String menid = request.getParameter("menid");
        String action = request.getParameter("remove");
        String changeWeek = request.getParameter("changeweek");
        
        udd.putAttribute("freeDate", freeDate);
        udd.putAttribute("account", menAccount);
        udd.putAttribute("freeSlot", slot);
        udd.putAttribute("menid", menid);
        try {
            if(!changeWeek.equals("")){
                ViewScheduleService viewScheduleService = new ViewScheduleService();
                viewScheduleService.viewScheduleByChange(request);
                request.getRequestDispatcher("WEB-INF/view/mentor/createschedule.jsp").forward(request, response);
                return;
            }
            if (action.equals("")) {
                mentorService.createSchedule(udd, request, response);
            } else {
                String sesid = request.getParameter("sesid");
                mentorService.removeSchedule(Integer.parseInt(sesid), request);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("schedule");
    }
}
