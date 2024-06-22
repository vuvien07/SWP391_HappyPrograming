/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.commentrate;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Feedback;
import model.Mentor;
import model.User;

/**
 *
 * @author LENOVO
 */
public class feedback extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private double calculateAverageRating(ArrayList<Feedback> feedbacks) {
    if (feedbacks == null || feedbacks.isEmpty()) {
        return 0.0;
    }

    int totalRatings = 0;
    for (Feedback feedback : feedbacks) {
        totalRatings += feedback.getRate();
        
    }

    return (double) totalRatings / feedbacks.size();
}
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    try {
        commentrate commentDAO = new commentrate(); // hoặc CommentRateDAO nếu bạn đổi tên
        String action = request.getParameter("action");

        if ("postComment".equals(action)) {
            // Xử lý khi người dùng submit form để đăng comment
            int rate = Integer.parseInt(request.getParameter("rating"));
            String comment = request.getParameter("msg");

            // Lấy userId từ session
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("id"); // Giả sử userId đã được lưu vào session khi đăng nhập

            // Lấy mentorId từ session hoặc từ cơ sở dữ liệu tùy vào thiết kế của bạn
            int mentorId = (int) session.getAttribute("id"); // Giả sử mentorId đã được lưu vào session khi đăng nhập

            Feedback feedback = new Feedback();
            feedback.setRate(rate);
            feedback.setComment(comment);
            feedback.setUser(new User(userId));
            feedback.setMentor(new Mentor(userId));

            commentDAO.insert(feedback);
        }

        // Sau khi insert hoặc không phải là action postComment, tiếp tục lấy danh sách feedback và tính averageRating
        ArrayList<Feedback> feedbacks = commentDAO.listByFeedback(); // Sửa thành phương thức listByFeedback trong class commentrate hoặc CommentRateDAO
        double averageRating = calculateAverageRating(feedbacks); // Sửa thành phương thức tính average rating

        request.setAttribute("feedbacks", feedbacks);
        request.setAttribute("averageRating", averageRating);
        request.getRequestDispatcher("WEB-INF/feedback.jsp").forward(request, response);
    } catch (NumberFormatException | ServletException | IOException e) {
        e.printStackTrace(); // Xử lý các exception xảy ra
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
