/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.SkillDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Skill;
import util.Util;

/**
 *
 * @author Admin
 */
public class ManageSkillController extends HttpServlet {

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
            out.println("<title>Servlet ManageSkillController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageSkillController at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SkillDBContext skillDAO = new SkillDBContext();
        ArrayList<Skill> skills = skillDAO.listAllForAdmin();
        int size = skills.size(), numPerPage = 6, page;
        String xPage = request.getParameter("page");
        if (xPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xPage);
        }
        int num = (size % numPerPage == 0 ? (size / numPerPage) : ((size / numPerPage) + 1));
        List<Object> pagedSkills = Util.listByPage((List<Object>) (List<?>) skills, xPage, numPerPage);
        request.setAttribute("num", num);
        request.setAttribute("page", page);
        request.setAttribute("pagedSkills", pagedSkills);
        request.getRequestDispatcher("WEB-INF/view/admin/skills.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SkillDBContext skillDBContext = new SkillDBContext();
        String txtSearch = request.getParameter("valueSearch");
        List<Skill> skills = skillDBContext.getSkillsSearch(txtSearch);
        int size = skills.size(), numPerPage = 6, page;
        String xPage = request.getParameter("page");
        if (xPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xPage);
        }
        int num = (size % numPerPage == 0 ? (size / numPerPage) : ((size / numPerPage) + 1));
        List<Object> pagedSkills = Util.listByPage((List<Object>) (List<?>) skills, xPage, numPerPage);
        request.setAttribute("num", num);
        request.setAttribute("page", page);
        request.setAttribute("pagedSkills", pagedSkills);
        request.getRequestDispatcher("WEB-INF/view/admin/skills.jsp").forward(request, response);
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
