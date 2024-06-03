/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentor;

import controller.authorization.BaseAuthController;
import dao.SkillDAO;
import dao.SlotDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Account;
import model.Skill;
import model.Slot;
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
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        Account mentorAccount = (Account) request.getSession().getAttribute("account");
        SlotDAO slotDAO = new SlotDAO();
        SkillDAO skillDAO = new SkillDAO();
        ArrayList<Slot> slots = slotDAO.listAll();
        request.setAttribute("slots", slots);
        ArrayList<Skill> skills = skillDAO.listByMentor(mentorAccount.getId());
        request.setAttribute("skills", skills);
        request.getRequestDispatcher("WEB-INF/view/mentor/createschedule.jsp").forward(request, response);
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
        UserDataDetail udd = new UserDataDetail();
        String freeDate = request.getParameter("scheduleDate");
        String[] slots = request.getParameterValues("freeslot");
        String[] skills = request.getParameterValues("skill");
        
        udd.putAttribute("freeDate", freeDate);
        udd.putAttribute("freeSlot", slots);
        udd.putAttribute("skill", skills);
        
//        if (slots.length > 1 || skills.length > 1) {
//            request.getSession().setAttribute("err", "Slot or skill must be choose no more than 1");
//        }else{
//            
//            request.getSession().removeAttribute("err");
//        }
//        response.sendRedirect("schedule");
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
