package controller;

import dal.SkillDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InputSkillController extends HttpServlet {

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String skillName = request.getParameter("skillname");
    String[] skillImage = request.getParameterValues("image");
    String description = request.getParameter("description");

    SkillDBContext sdb = new SkillDBContext();
    String image = "";


    if (sdb.checkSkillExist(skillName)) {
     
        response.sendRedirect("skills?ale=Skill+already+exists");
    } else {
      
        if (skillImage != null && skillImage.length > 0 && skillImage[0] != null && !skillImage[0].equals("")) {
            for (int i = 0; i < skillImage.length; i++) {
                image += "" + skillImage[i] + ",";
            }
            if (image.endsWith(",")) {
                image = image.substring(0, image.length() - 1);
            }
        }

        
        sdb.addSkill(skillName, description, image);

      
        response.sendRedirect("skills?mess=Skill+created+successfully");
    }
}
}
