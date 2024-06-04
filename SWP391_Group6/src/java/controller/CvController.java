/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.MentorDAO;
import dal.SkillDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Mentor;
import model.Skill;

/**
 *
 * @author Admin
 */
public class CvController extends HttpServlet {

    private MentorDAO mentorDAO;

    @Override
    public void init() throws ServletException {
        mentorDAO = new MentorDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Call the method to get mentors
            List<Mentor> mentor = mentorDAO.getCvMentorByAccid();

            // Get the session and set the attribute
            HttpSession session = request.getSession();
            session.setAttribute("mentor", mentor);

            // Redirect or forward to a JSP page to display the mentors
            request.getRequestDispatcher("cv.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle error and redirect to an error page
            response.sendRedirect("cv.jsp");
        }
    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        SkillDBContext skillDBContext = new  SkillDBContext();
//        String action = request.getParameter("action");
//        ArrayList<Skill> skill= skillDBContext.listAll();
//        switch (action) {
//            case "insert":
//                insertMentor(request, response, session);
//                break;
//            case "update":
//                updateMentor(request, response, session);
//                break;
//            case "list":
//                listMentors(request, response, session);
//                break;
//            default:
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
//                break;
//        }
//    }
//
//    private void insertMentor(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
//        try {
//            int accid = Integer.parseInt(request.getParameter("accid"));
//            String name = request.getParameter("name");
//            boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
//            String phone = request.getParameter("phone");
//            String address = request.getParameter("address");
//            Date dateOfBirth = java.sql.Date.valueOf(request.getParameter("dateOfBirth"));
//            String ava = request.getParameter("ava");
//            String job = request.getParameter("job");
//            String skill = request.getParameter("skill");
//            String intro = request.getParameter("intro");
//            String achievement = request.getParameter("achievement");
//            String experience = request.getParameter("experience");
//            String certificate = request.getParameter("certificate");
//            boolean status = Boolean.parseBoolean(request.getParameter("status"));
//            String accountUsername = request.getParameter("accountUsername");
//            String email = request.getParameter("email");
//
//            Account account = new Account(accid, accountUsername, email);
//            Mentor mentor = new Mentor();
//            
//            boolean success = mentorDAO.createCv(mentor);
//            session.setAttribute("message", success ? "Insert successful" : "Insert failed");
//            response.sendRedirect("cv.jsp");
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//    }
//
//    private void updateMentor(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
//        try {
//            int accid = Integer.parseInt(request.getParameter("accid"));
//            String name = request.getParameter("name");
//            boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
//            String phone = request.getParameter("phone");
//            String address = request.getParameter("address");
//            Date dateOfBirth = Date.valueOf(request.getParameter("dateOfBirth"));
//            String ava = request.getParameter("ava");
//            String job = request.getParameter("job");
//            String skill = request.getParameter("skill");
//            String intro = request.getParameter("intro");
//            String achievement = request.getParameter("achievement");
//            String experience = request.getParameter("experience");
//            String certificate = request.getParameter("certificate");
//            boolean status = Boolean.parseBoolean(request.getParameter("status"));
//            String accountUsername = request.getParameter("accountUsername");
//            String email = request.getParameter("email");
//
//            Account account = new Account(accid, accountUsername, email);
//            Mentor mentor = new Mentor(accid, name, gender, phone, address, dateOfBirth, ava, job, skill, intro, achievement, experience, certificate, status, account);
//
//            boolean success = mentorDAO.updateCv(mentor);
//            session.setAttribute("message", success ? "Update successful" : "Update failed");
//            response.sendRedirect("cv.jsp");
//        } catch (Exception e) {
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
//        }
//    }

    private void listMentors(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
        try {
            List<Mentor> mentors = mentorDAO.getCvMentorByAccid();
            session.setAttribute("mentors", mentors);
            response.sendRedirect("cv.jsp");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}

