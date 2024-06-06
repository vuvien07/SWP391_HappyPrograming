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
import java.text.SimpleDateFormat;
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
//12321412341
    private MentorDAO mentorDAO = new MentorDAO() {
        @Override
        public ArrayList<Mentor> listAll() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void insert(Mentor entity) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void update(Mentor entity) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        @Override
        public void delete(Mentor entity) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    } ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        switch (action) {
            case "view":
                viewMentor(request, response);
                break;
            case "search":
                searchMentors(request, response);
                break;
            case "update":
                showUpdateForm(request, response);
                break;
            case "insert":
                showInsertForm(request, response);
                break;
            default:
                viewMentor(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (action == null) {
            action = "view";
        }

        switch (action) {
            case "update":
                updateMentor(request, response);
                break;
            case "insert":
                insertMentor(request, response);
                break;
            default:
                viewMentor(request, response);
                break;
        }
    }

    private void viewMentor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mentorId = Integer.parseInt(request.getParameter("id"));
        Mentor mentor = mentorDAO.viewMentorProfile(mentorId);
        request.setAttribute("mentor", mentor);
        request.getRequestDispatcher("cv.jsp").forward(request, response);
    }

    private void searchMentors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Mentor> mentors = mentorDAO.findByNameMentor(name);
        request.setAttribute("mentors", mentors);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int mentorId = Integer.parseInt(request.getParameter("id"));
        Mentor mentor = mentorDAO.viewMentorProfile(mentorId);
        request.setAttribute("mentor", mentor);
        request.getRequestDispatcher("updatecv.jsp").forward(request, response);
    }

private void updateMentor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int mentorId = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");
    String dateOfBirthStr = request.getParameter("dateOfBirth");
    String ava = request.getParameter("ava");
    String job = request.getParameter("job");
    String intro = request.getParameter("intro");
    String achievement = request.getParameter("achievement");
    String experience = request.getParameter("experience");
    String certificate = request.getParameter("certificate");
    String email = request.getParameter("email");

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date dateOfBirth = null;
    try {
        dateOfBirth = formatter.parse(dateOfBirthStr);
    } catch (Exception e) {
        e.printStackTrace();
    }

    Mentor mentor = new Mentor();
    mentor.setId(mentorId);
    mentor.setName(name);
    mentor.setGender(gender);
    mentor.setPhone(phone);
    mentor.setAddress(address);
    mentor.setDateOfBirth(dateOfBirth);
    mentor.setAva(ava);
    mentor.setJob(job);
    mentor.setIntro(intro);
    mentor.setAchievement(achievement);
    mentor.setExperience(experience);
    mentor.setCertificate(certificate);

    Account account = new Account();
    account.setEmail(email);
    mentor.setAccount(account);

    mentorDAO.updateMentorSkillAndEmail(mentor);
    response.sendRedirect("cv?action=view&id=" + mentorId);
}

    private void showInsertForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addcv.jsp").forward(request, response);
    }

   private void insertMentor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
    String phone = request.getParameter("phone");
    String address = request.getParameter("address");
    String dateOfBirthStr = request.getParameter("dateOfBirth");
    String ava = request.getParameter("ava");
    String job = request.getParameter("job");
    String intro = request.getParameter("intro");
    String achievement = request.getParameter("achievement");
    String experience = request.getParameter("experience");
    String certificate = request.getParameter("certificate");
    String email = request.getParameter("email");

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date dateOfBirth = null;
    try {
        dateOfBirth = formatter.parse(dateOfBirthStr);
    } catch (Exception e) {
        e.printStackTrace();
    }

    Mentor mentor = new Mentor();
    mentor.setName(name);
    mentor.setGender(gender);
    mentor.setPhone(phone);
    mentor.setAddress(address);
    mentor.setDateOfBirth(dateOfBirth);
    mentor.setAva(ava);
    mentor.setJob(job);
    mentor.setIntro(intro);
    mentor.setAchievement(achievement);
    mentor.setExperience(experience);
    mentor.setCertificate(certificate);

    Account account = new Account();
    account.setEmail(email);
    mentor.setAccount(account);

    mentorDAO.insertMentor(mentor);
    response.sendRedirect("cv?action=search");
}
}
