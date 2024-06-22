
package controller.manager;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DashBoardMnController extends HttpServlet {
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       request.getRequestDispatcher("WEB-INF/view/manager/dashboardmn.jsp").forward(request, response);
    } 

  

}
