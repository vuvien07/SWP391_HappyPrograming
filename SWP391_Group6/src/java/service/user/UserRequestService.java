/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.user;

import dao.RequestDAO;
import dao.UserDAO;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.sql.Date;
import model.Account;
import model.Mentor;
import model.Request;
import model.User;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class UserRequestService {

    private RequestDAO requestDAO;
    private UserDAO userDAO;

    public UserRequestService() {
        requestDAO = new RequestDAO();
        userDAO = new UserDAO();
    }

    public void processCreateRequest(UserDataDetail userDataDetail, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        Request newRequest = new Request();
        newRequest.setTitle((String) userDataDetail.getAttribute("title"));
        String deadlineDate = (String) userDataDetail.getAttribute("deadlineDate");
        String deadlineTime = (String) userDataDetail.getAttribute("deadlineTime");
        newRequest.setDeadlineTime(deadlineTime);
        String[] skills = (String[]) userDataDetail.getAttribute("skills");
        StringBuilder newSkill = new StringBuilder();
        for (String skill : skills) {
            newSkill.append(skill).append(" ");
        }
        newRequest.setSkill(newSkill.toString());
        newRequest.setContent((String) userDataDetail.getAttribute("content"));
        Mentor mentor = new Mentor();
        String mentorid = (String) userDataDetail.getAttribute("mentorid");
        mentor.setId(Integer.parseInt(mentorid));
        newRequest.setMentor(mentor);
        User user = userDAO.getUserById(account.getId());
        newRequest.setUser(user);
        requestDAO.insert(newRequest);
        request.setAttribute("success", "Create request successfully");
    }

}
