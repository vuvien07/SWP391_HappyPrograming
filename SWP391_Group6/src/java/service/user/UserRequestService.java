/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.user;

import dal.RequestDBContext;
import dal.SessionDBContext;
import dal.UserDBContext;
import jakarta.servlet.http.HttpServletRequest;
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

    private final RequestDBContext requestDAO;
    private final UserDBContext userDAO;
    private final SessionDBContext sessionDAO;

    public UserRequestService() {
        requestDAO = new RequestDBContext();
        userDAO = new UserDBContext();
        sessionDAO = new SessionDBContext();
    }

    public void processCreateRequest(UserDataDetail userDataDetail, HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        Request newRequest = new Request();
        newRequest.setTitle((String) userDataDetail.getAttribute("title"));
        String deadlineDate = (String) userDataDetail.getAttribute("deadlineDate");
        String deadlineTime = (String) userDataDetail.getAttribute("deadlineTime");
        newRequest.setDeadlineTime(deadlineTime);
        String[] skills = (String[]) userDataDetail.getAttribute("skills");
        if (skills.length > 1) {
            request.setAttribute("err", "You must choose no more than 1 skill");
        } else {
            newRequest.setContent((String) userDataDetail.getAttribute("content"));
            newRequest.setSkill(skills[0]);
            Mentor mm = (Mentor) request.getSession().getAttribute("mentor");
            Mentor mentor = new Mentor();
            mentor.setId(mm.getId());
            newRequest.setMentor(mentor);
            User user = userDAO.getUserById(account.getId());
            newRequest.setUser(user);
            requestDAO.insert(newRequest);
            Request topRequest = requestDAO.selectTopRequest();
            String selectedSchedule = (String) request.getSession().getAttribute("selectedSchedule");
            String selectedSchedules[] = selectedSchedule.split(" ");
            try {
                for (String selectedSchedule1 : selectedSchedules) {
                    sessionDAO.updateByTempuser(topRequest.getId(), user.getId(), Integer.parseInt(selectedSchedule1));
                }
            } catch (Exception e) {
            }
            request.setAttribute("success", "Create request successfully");
        }
    }

    public void processUpdateRequest(UserDataDetail userDataDetail, HttpServletRequest request) {
        String requestId_raw = (String) request.getSession().getAttribute("requestid");
        int requestId = Integer.parseInt(requestId_raw);
        Account account = (Account) request.getSession().getAttribute("account");
        Request newRequest = new Request();
        newRequest.setId(requestId);
        newRequest.setTitle((String) userDataDetail.getAttribute("title"));
        String deadlineTime = (String) userDataDetail.getAttribute("deadlineTime");
        newRequest.setDeadlineTime(deadlineTime);
        newRequest.setSkill((String) userDataDetail.getAttribute("updateSkill"));
        newRequest.setContent((String) userDataDetail.getAttribute("content"));
            requestDAO.updateRequestById(newRequest);
            request.getSession().setAttribute("success", "Update request with id " + requestId + " successfully");
        
    }

}
