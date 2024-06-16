/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.mentor;

import controller.authorization.BaseAuthController;
import dal.RequestDBContext;
import dal.UserDBContext;
import jakarta.mail.internet.MailDateFormat;
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
import service.NotificationService;
import service.mentor.MentorService;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class ViewMenteeRequest extends BaseAuthController {

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
            out.println("<title>Servlet ViewMenteeRequest</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ViewMenteeRequest at " + request.getContextPath() + "</h1>");
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
        Mentor mentor = (Mentor) request.getSession().getAttribute("mentor");
        MentorService mentorService = new MentorService();
        Date date = new Date();
        if (!mentor.isStatus()) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "You cannot perform this function due to your CV checking is processing or rejected");
        } else {
            RequestDBContext requestDBContext = new RequestDBContext();
            ArrayList<Request> requests = requestDBContext.listByMentorId(mentor.getId());
            for (Request request1 : requests) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                try {
                    Date deadline = sdf.parse(request1.getDeadlineTime());
                    if(date.getTime() > deadline.getTime() && request1.getStatus().equals("Processing")){
                        UserDBContext userDBContext = new UserDBContext();
                        UserDataDetail userDataDetail = new UserDataDetail();
                        userDataDetail.putAttribute("account", account);
                        Request userRequest = requestDBContext.getById(request1.getId());
                        userDataDetail.putAttribute("id", userRequest.getId());
                        userDataDetail.putAttribute("userid", userRequest.getUser().getId());
                        userDataDetail.putAttribute("user", userDBContext.getByUserId(userRequest.getUser().getId()));
                        mentorService.processUncheckUserRequest(userDataDetail);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(ViewMenteeRequest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            request.setAttribute("requests", requests);
            request.getRequestDispatcher("WEB-INF/view/mentor/listmenteerequest.jsp").forward(request, response);
        }
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
        processRequest(request, response);
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
