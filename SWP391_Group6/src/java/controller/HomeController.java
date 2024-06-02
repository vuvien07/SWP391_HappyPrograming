
package controller;

import dal.SkillDBContext;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Skill;

public class HomeController extends HttpServlet {
   
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        SkillDBContext sdc = new SkillDBContext();
        ArrayList<Skill> skills = sdc.listAll();
        request.getSession().setAttribute("skills", skills);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    } 

  


}
