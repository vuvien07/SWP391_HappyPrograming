package controller;

import dal.SkillDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Skill;

public class ManageSkillController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        SkillDBContext sdb = new SkillDBContext();

        List<Skill> list = sdb.listAll();

        int page = 1, numPerPage = 6;
        int size = list.size();
        int numberpage = ((size % numPerPage == 0) ? (size / 6) : (size / 6) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * 6;
        end = Math.min(page * numPerPage, size);

        List<Skill> listByPage = sdb.getListByPage(list, start, end);

        request.setAttribute("page", page);
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        request.setAttribute("numberpage", numberpage);

        request.setAttribute("listByPage", listByPage);

        request.getRequestDispatcher("WEB-INF/view/admin/skills.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        SkillDBContext sdb = new SkillDBContext();
        String txtSearch = request.getParameter("valueSearch");

     
        List<Skill> listSkillsBySearch = sdb.getSkillsSearch(txtSearch);

        int page = 1, numPerPage = 6;
        int size = listSkillsBySearch.size();
        int numberpage = ((size % numPerPage == 0) ? (size / 6) : (size / 6) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * 6;
        end = Math.min(page * numPerPage, size);

        List<Skill> listByPage = sdb.getListByPage(listSkillsBySearch, start, end);

        request.setAttribute("page", page);
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        request.setAttribute("numberpage", numberpage);

        request.setAttribute("listByPage", listByPage);
        request.setAttribute("searchValue", txtSearch);

        request.getRequestDispatcher("WEB-INF/view/admin/skills.jsp").forward(request, response);

    }

}
