/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import controller.authorization.BaseAuthController;
import dao.SkillDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Account;
import model.Skill;

/**
 *
 * @author Admin
 */
public class UpdateSkillController extends BaseAuthController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String id_raw = request.getParameter("sid");
        SkillDAO sdb = new SkillDAO();

        int id = Integer.parseInt(id_raw);
        Skill s = sdb.getSkillById(id);

        request.setAttribute("detail", s);

        request.getRequestDispatcher("WEB-INF/view/admin/updateskill.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        String sid_raw = request.getParameter("id");
        String skillName = request.getParameter("skillname");
        String[] skillImage = request.getParameterValues("image");
        String status_raw = request.getParameter("status");
        String description = request.getParameter("description");

        SkillDAO sdb = new SkillDAO();
        int sid;
        boolean status;
        String image = "";

        try {
            sid = Integer.parseInt(sid_raw);
            status = Boolean.parseBoolean(status_raw);
            if (skillImage[0] != null && !skillImage[0].equals("")) {
                for (int i = 0; i < skillImage.length; i++) {
                    image += "assets/uploads/skill/" + skillImage[i] + ",";
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



