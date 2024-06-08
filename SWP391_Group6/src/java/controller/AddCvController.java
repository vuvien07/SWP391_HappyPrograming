/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.MentorDAO;
import dal.MentorDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Mentor;
import model.Mentor_Skill;

/**
 *
 * @author Admin
 */
public class AddCvController extends HttpServlet {
    private MentorDAO mentorDAO;

    @Override
    public void init() {
        mentorDAO = new MentorDAO() {
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
        };
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addcv.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
        int mentorId = Integer.parseInt(request.getParameter("mentorId"));
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
        java.util.Date dateOfBirth = null;
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
        if (dateOfBirth != null) {
            mentor.setDateOfBirth(new java.sql.Date(dateOfBirth.getTime()));
        }
        mentor.setAva(ava);
        mentor.setJob(job);
        mentor.setIntro(intro);
        mentor.setAchievement(achievement);
        mentor.setExperience(experience);
        mentor.setCertificate(certificate);
        
        Account account = new Account();
        account.setEmail(email);
        mentor.setAccount(account);
        
        List<Mentor_Skill> mentorSkills = new ArrayList<>();
        String[] skills = request.getParameterValues("skills");
        if (skills != null) {
            for (String skillName : skills) {
                Mentor_Skill mentorSkill = new Mentor_Skill();
                // Assuming SkillDAO has a method to find skill by name
                int skillId = mentorDAO.findSkillIdByName(skillName.trim());
                mentorSkill.setSkillId(skillId);
                mentorSkills.add(mentorSkill);
            }
        }
        mentor.setMentorSkills(mentorSkills);
        
        boolean isInserted = mentorDAO.insertMentor(mentor);
        if (isInserted) {
            response.sendRedirect("cv.jsp");
        } else {
            request.getRequestDispatcher("addcv.jsp").forward(request, response);
        }
    } catch (SQLException ex) {
            Logger.getLogger(AddCvController.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
        

