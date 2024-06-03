package controller;

import dal.SkillDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Skill;

public class EditSkillController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String sid_raw = request.getParameter("id");
        String skillName = request.getParameter("skillname");
        String[] skillImage = request.getParameterValues("image");
        String status_raw = request.getParameter("status");
        String description = request.getParameter("description");

        SkillDBContext sdb = new SkillDBContext();
        int sid;
        boolean status;
        String image = "";

        try {
            sid = Integer.parseInt(sid_raw);

            status = (status_raw != null);

            if (skillImage != null && skillImage.length > 0 && skillImage[0] != null && !skillImage[0].equals("")) {
                for (int i = 0; i < skillImage.length; i++) {
                    image += "" + skillImage[i] + ",";
                }
                if (image.endsWith(",")) {
                    image = image.substring(0, image.length() - 1);
                }
            }

            sdb.editSkill(sid, skillName, status, description, image);

            List<Skill> skillList = sdb.listAll();
            request.setAttribute("mess", "Edit successfully!");
            request.setAttribute("listByPage", skillList);
            response.sendRedirect("skills?mess=Edit+successfully+skill+id+" + sid);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }


    }

}
