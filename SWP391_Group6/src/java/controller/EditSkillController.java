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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        request.setCharacterEncoding("UTF-8");
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
            status = Boolean.parseBoolean(status_raw);
            if (skillImage[0] != null && !skillImage[0].equals("")) {
                for (int i = 0; i < skillImage.length; i++) {
                    image += "resources/images/" + skillImage[i] + ",";
                }
                if (image.endsWith(",")) {
                    image = image.substring(0, image.length() - 1);
                }
            }

            sdb.editSkill(sid, skillName, status, description, image);

            List<Skill> skillList = sdb.listAll();
            request.setAttribute("mess", "Edit successfully!");
            request.setAttribute("listByPage", skillList);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        response.sendRedirect("skills");
    }
}
