/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.user;

import controller.authorization.BaseAuthController;
import dal.MentorDBContext;
import dal.SessionDBContext;
import dal.SlotDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Mentor;
import model.Session;
import model.Slot;
import model.User;
import service.ViewScheduleService;
import util.Util;

/**
 *
 * @author Admin
 */
public class BookMentorController extends BaseAuthController {

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
            out.println("<title>Servlet BookMentorController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookMentorController at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        String menid = request.getParameter("menid");
        MentorDBContext mentorDAO = new MentorDBContext();
        SessionDBContext sessionDAO = new SessionDBContext();
        SlotDBContext slotDAO = new SlotDBContext();
        LocalDate localDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        Map<java.sql.Date, java.sql.Date> weeksOnYear = Util.getWeeksByYear(localDate.getYear());
        ArrayList<Slot> slots = slotDAO.listAll();
        ArrayList<java.sql.Date> dates = null;
        Set<java.sql.Date> keySet = weeksOnYear.keySet();
        for (java.sql.Date object : keySet) {
            if (localDate.get(weekFields.weekOfYear()) == object.toLocalDate().get(weekFields.weekOfYear())) {
                dates = Util.getDatesBetween(object, weeksOnYear.get(object));
                break;
            }
        }
        ArrayList<Session> sessions = sessionDAO.listByMentorId(Integer.parseInt(menid));
        Mentor mentor = mentorDAO.getByMentorid(Integer.parseInt(menid));
        request.getSession().setAttribute("mentor", mentor);
        request.getSession().setAttribute("dates", dates);
        request.getSession().setAttribute("sessions", sessions);
        request.setAttribute("currentWeek", localDate.get(weekFields.weekOfYear()));
        request.getSession().setAttribute("slots", slots);
        request.getSession().setAttribute("weeks_on_year", weeksOnYear);
        request.getSession().setAttribute("year", localDate.getYear());
        request.getRequestDispatcher("WEB-INF/view/user/bookmentor.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        String sessionid = request.getParameter("sesid");
        ViewScheduleService vss = new ViewScheduleService();
        if (!sessionid.equals("")) {
            UserDBContext userDAO = new UserDBContext();
            Account userAccount = (Account) request.getSession().getAttribute("account");
            User user = userDAO.getUserById(userAccount.getId());
            SessionDBContext sessionDAO = new SessionDBContext();
            try {
                if (sessionDAO.isDuplicatedSessionByUserId(Integer.parseInt(sessionid), user.getId())) {
                    request.setAttribute("err", "This slot is registered by another user. Please book again");
                    request.getRequestDispatcher("WEB-INF/view/user/bookmentor.jsp").forward(request, response);
                } else {
                    sessionDAO.updateById(user.getId(), Integer.parseInt(sessionid));
                    request.setAttribute("success", "Book successful!");
                    request.getRequestDispatcher("WEB-INF/view/user/bookmentor.jsp").forward(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(BookMentorController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            vss.viewScheduleByChange(request);
            request.getRequestDispatcher("WEB-INF/view/user/bookmentor.jsp").forward(request, response);
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
