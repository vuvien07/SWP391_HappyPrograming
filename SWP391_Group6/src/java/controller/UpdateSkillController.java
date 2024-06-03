
package controller;

import dal.SkillDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Skill;


public class UpdateSkillController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            response.setContentType("text/html;charset=UTF-8");
            String id_raw = request.getParameter("sid");
            SkillDBContext sdb = new SkillDBContext();

            int id = Integer.parseInt(id_raw);
            Skill s = sdb.getSkillByID(id);

            request.setAttribute("detail", s);
           

            request.getRequestDispatcher("WEB-INF/view/admin/updateskill.jsp").forward(request, response);
        
    }

 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
