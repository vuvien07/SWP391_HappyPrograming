/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import util.Util;

/**
 *
 * @author Admin
 */
public class PaginationService {

    public void pagingList(HttpServletRequest request, ArrayList<Object> objects, String setListAttribute) {
        int size = objects.size(), numPerPage = 6, page;
        String xPage = request.getParameter("page");
        if (xPage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xPage);
        }
        int num = (size % numPerPage == 0 ? (size / numPerPage) : ((size / numPerPage) + 1));
        List<Object> objectLists = Util.listByPage(objects, xPage, numPerPage);
        request.setAttribute("num", num);
        request.setAttribute("page", page);
        request.getSession().setAttribute(setListAttribute, objectLists);
    }
}
