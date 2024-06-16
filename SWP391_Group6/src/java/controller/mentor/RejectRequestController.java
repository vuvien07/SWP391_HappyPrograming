/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentor;

import controller.authorization.BaseAuthController;
import dal.RequestDBContext;
import dal.SessionDBContext;
import dal.UserDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Mentor;
import model.Request;
import model.User;
import service.NotificationService;
import service.mentor.MentorService;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class RejectRequestController extends BaseAuthController {

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
            out.println("<title>Servlet RejectRequestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RejectRequestController at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        MentorService mentorService = new MentorService();
        UserDataDetail userDataDetail = new UserDataDetail();
        UserDBContext userDBContext = new UserDBContext();
        RequestDBContext requestDBContext = new RequestDBContext();
        String id = request.getParameter("id");
        String userid = request.getParameter("userid");
        String reason = request.getParameter("reason");
        String deadlineTime = request.getParameter("deadlineTime");

        try {
            Date deadineDate = sdf.parse(deadlineTime);
            if (date.getTime() > deadineDate.getTime()) {
                request.setAttribute("err", "Out of deadline time!");
                ArrayList<Request> requests = requestDBContext.listByMentorId(((Mentor) request.getSession().getAttribute("mentor")).getId());
                request.setAttribute("requests", requests);
                request.getRequestDispatcher("WEB-INF/view/mentor/listmenteerequest.jsp").forward(request, response);
                return;
            }
        } catch (ParseException ex) {
            Logger.getLogger(AcceptRequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        User user = userDBContext.getByUserId(Integer.parseInt(userid));

        userDataDetail.putAttribute("id", Integer.parseInt(id));
        userDataDetail.putAttribute("userid", Integer.parseInt(userid));
        userDataDetail.putAttribute("reason", reason);
        userDataDetail.putAttribute("user", user);
        userDataDetail.putAttribute("account", account);

        mentorService.processRejectUserRequest(userDataDetail);
        response.sendRedirect("mentee_request");
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
