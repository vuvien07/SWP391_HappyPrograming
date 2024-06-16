/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.mentor;

import dal.CVDBContext;
import dal.MentorDBContext;
import dal.RequestDBContext;
import dal.SessionDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import model.Account;
import model.CV;
import model.Mentor;
import model.Request;
import model.Session;
import model.Slot;
import service.NotificationService;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class MentorService {

    private RequestDBContext requestDBContext;
    private SessionDBContext sessionDBContext;
    private MentorDBContext mentorDAO;
    private CVDBContext cvDBContext;

    public MentorService() {
        sessionDBContext = new SessionDBContext();
        mentorDAO = new MentorDBContext();
        cvDBContext = new CVDBContext();
        requestDBContext = new RequestDBContext();
    }

    public void createSchedule(UserDataDetail userDataDetail, HttpServletRequest request) throws SQLException, ParseException {
        Account menAccount = (Account) userDataDetail.getAttribute("account");
        Slot slot = new Slot();
        slot.setId(Integer.parseInt((String) userDataDetail.getAttribute("freeSlot")));
        Mentor mentor = mentorDAO.getByAccountId(menAccount.getId());
        mentor.setId(mentor.getId());
        
        String freeDate = userDataDetail.getStringAttribute("freeDate");
        String timeSlotFrom = userDataDetail.getStringAttribute("slotFrom");
        
        java.util.Date date = new java.util.Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date checkDate = sdf.parse((new StringBuilder().append(freeDate).append(" ").append(timeSlotFrom)).toString());
        if(checkDate.getTime() < date.getTime()){
            request.setAttribute("dateErr", "Schedule is before compared to current date! Please choose again");
            return;
        }
        Session session = new Session();
        session.setDate(Date.valueOf((String) userDataDetail.getAttribute("freeDate")));
        session.setSlot(slot);
        session.setMentor(mentor);
        session.setSkill((String) userDataDetail.getAttribute("skill"));
        try {
            sessionDBContext.insert(session);
        } catch (Exception e) {
        } finally {
            request.getSession().setAttribute("success", "Create schedule successful!");
        }
    }

    public void removeSchedule(int id, HttpServletRequest request) throws SQLException {
        try {
            sessionDBContext.removeEmptyScheduleById(id);
        } catch (Exception e) {
        } finally {
            request.getSession().setAttribute("success", "Remove schedule successful!");
        }
    }

    public void processCreateCV(UserDataDetail userDataDetail, HttpServletRequest request) throws IOException, ServletException {
        Part fPart = request.getPart("avatar");
        String firstSkill = (String) userDataDetail.getAttribute("firstSkill");
        String secondSkill = (String) userDataDetail.getAttribute("secondSkill");
        String thirdSkill = (String) userDataDetail.getAttribute("thirdSkill");
        if (firstSkill.equals("none") && secondSkill.equals("none") && thirdSkill.equals("none")) {
            request.getSession().setAttribute("err", "Skills must not be none!");
        } else if (firstSkill.equals(secondSkill) || secondSkill.equals(thirdSkill) || firstSkill.equals(thirdSkill)) {
            request.getSession().setAttribute("err", "Skills must not be duplicated");
        } else {
            CV cv = new CV();
            StringBuilder sb = new StringBuilder();
            sb.append("1").append(".").append(firstSkill).append("\n");
            sb.append("2").append(".").append(secondSkill).append("\n");
            sb.append("3").append(".").append(thirdSkill).append("\n");

            if (fPart != null) {
                String fileName = Paths.get(fPart.getSubmittedFileName()).getFileName().toString();
                String fileType = fPart.getContentType();
                if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
                    String uploadDir = request.getServletContext().getRealPath("/assets/uploads/cv");
                    File uploadDirFile = new File(uploadDir);
                    if (!uploadDirFile.exists()) {
                        uploadDirFile.mkdirs();
                    }
                    String filePath = uploadDir + "\\" + fileName;
                    fPart.write(filePath);
                    cv.setAvatar(fileName);
                } else {
                    String image = request.getParameter("image-initiate");
                    cv.setAvatar(image);
                }
            }

            cv.setIntro((String) userDataDetail.getAttribute("intro"));
            cv.setAchievement((String) userDataDetail.getAttribute("achivement"));
            cv.setExperience((String) userDataDetail.getAttribute("experience"));
            cv.setCertificate((String) userDataDetail.getAttribute("certificate"));
            cv.setSkills(sb.toString());
            cv.setStatus(false);

            Mentor mentor = (Mentor) request.getSession().getAttribute("mentor");
            cv.setMentor(mentor);
            try {
                cvDBContext.insert(cv);
            } catch (Exception e) {
            }
            request.setAttribute("success", "Create CV successfully!");
        }

    }

    public void processUpdateCV(UserDataDetail userDataDetail, HttpServletRequest request) throws IOException, ServletException {
        Part fPart = request.getPart("avatar");
        String firstSkill = (String) userDataDetail.getAttribute("firstSkill");
        String secondSkill = (String) userDataDetail.getAttribute("secondSkill");
        String thirdSkill = (String) userDataDetail.getAttribute("thirdSkill");
        if (firstSkill.equals("none") && secondSkill.equals("none") && thirdSkill.equals("none")) {
            request.getSession().setAttribute("err", "Skills must not be none!");
        } else if (firstSkill.equals(secondSkill) || secondSkill.equals(thirdSkill) || firstSkill.equals(thirdSkill)) {
            request.getSession().setAttribute("err", "Skills must not be duplicated");
        } else {
            CV cv = new CV();
            StringBuilder sb = new StringBuilder();
            sb.append("1").append(".").append(firstSkill).append("\n");
            sb.append("2").append(".").append(secondSkill).append("\n");
            sb.append("3").append(".").append(thirdSkill).append("\n");

            if (fPart != null) {
                String fileName = Paths.get(fPart.getSubmittedFileName()).getFileName().toString();
                String fileType = fPart.getContentType();
                if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
                    String uploadDir = request.getServletContext().getRealPath("/assets/uploads/cv");
                    File uploadDirFile = new File(uploadDir);
                    if (!uploadDirFile.exists()) {
                        uploadDirFile.mkdirs();
                    }
                    String filePath = uploadDir + "\\" + fileName;
                    fPart.write(filePath);
                    cv.setAvatar(fileName);
                } else {
                    String image = request.getParameter("image-initiate");
                    cv.setAvatar(image);
                }
            }

            cv.setIntro((String) userDataDetail.getAttribute("intro"));
            cv.setAchievement((String) userDataDetail.getAttribute("achivement"));
            cv.setExperience((String) userDataDetail.getAttribute("experience"));
            cv.setCertificate((String) userDataDetail.getAttribute("certificate"));
            cv.setSkills(sb.toString());
            cv.setStatus(false);

            Mentor mentor = (Mentor) request.getSession().getAttribute("mentor");
            CV menCV = cvDBContext.getCVByMentor(mentor.getId());
            cv.setId(menCV.getId());
            try {
                cvDBContext.updateCV(cv);
            } catch (Exception e) {
            }
            request.getSession().setAttribute("update_success", "Update CV successfully!");
        }

    }

    public void processRejectUserRequest(UserDataDetail userDataDetail) {
        NotificationService notificationService = new NotificationService();
        Account account = (Account) userDataDetail.getAttribute("account");
        StringBuilder content = new StringBuilder();
        String title = "Your request has been rejected by mentor " + account.getUsername();
        String reason = userDataDetail.getStringAttribute("reason");
        requestDBContext.rejectRequest(userDataDetail.getIntegerAttribute("id"));
        Request userRequest = requestDBContext.getById(userDataDetail.getIntegerAttribute("id"));
        content.append("Here is your request:<br>").append("Title:")
                .append(userRequest.getTitle()).append("<br>").append("Request content:")
                .append(userRequest.getContent().replaceAll("\n", "<br>"))
                .append("Your selected session(s):<br>");
        ArrayList<String> selectedSessions
                = sessionDBContext.listByRequestIdAndUserid(userDataDetail.getIntegerAttribute("id"), userDataDetail.getIntegerAttribute("userid"));
        for (String selectedSession : selectedSessions) {
            content.append("-").append(selectedSession).append("<br>");
        }
        content.append("Reason for reject:<br>");
        content.append(reason.replaceAll("\n", "<br>"));
        notificationService.sendRequestNotificationForUser(userDataDetail, title, content.toString());
    }

    public void processAcceptRequest(UserDataDetail userDataDetail) {
        NotificationService notificationService = new NotificationService();
        Account account = (Account) userDataDetail.getAttribute("account");
        StringBuilder content = new StringBuilder();
        String title = "Your request has been accepted by mentor " + account.getUsername();
        String reason = userDataDetail.getStringAttribute("reason");
        String skill = userDataDetail.getStringAttribute("skill");
        requestDBContext.acceptRequest(skill, userDataDetail.getIntegerAttribute("userId"), userDataDetail.getIntegerAttribute("requestId"));
        Request userRequest = requestDBContext.getById(userDataDetail.getIntegerAttribute("requestId"));
        content.append("Here is your request:<br>").append("Title:")
                .append(userRequest.getTitle()).append("<br>").append("Request content:<br>")
                .append(userRequest.getContent().replaceAll("\n", "<br>"))
                .append("Your selected session(s):<br>");
        ArrayList<String> selectedSessions
                = sessionDBContext.listByRequestIdAndUserid(userDataDetail.getIntegerAttribute("requestId"), userDataDetail.getIntegerAttribute("userId"));
        for (String selectedSession : selectedSessions) {
            content.append("-").append(selectedSession).append("<br>");
        }
        content.append("Your selected skill:").append(skill).append("<br>");
        content.append("Reason for accept:<br>");
        content.append(reason.replaceAll("\n", "<br>"));
        notificationService.sendRequestNotificationForUser(userDataDetail, title, content.toString());
    }
    
     public void processUncheckUserRequest(UserDataDetail userDataDetail) {
        NotificationService notificationService = new NotificationService();
        Account account = (Account) userDataDetail.getAttribute("account");
        StringBuilder content = new StringBuilder();
        String title = "Your request has been cancelled because mentor " + account.getUsername() + " uncheck and deadline time exceeded!";
        Request userRequest = requestDBContext.getById(userDataDetail.getIntegerAttribute("id"));
        content.append("Here is your request:<br>").append("Title:")
                .append(userRequest.getTitle()).append("<br>").append("Request content:")
                .append(userRequest.getContent().replaceAll("\n", "<br>")).append("<br>")
                .append("Your selected session(s):<br>");
        ArrayList<String> selectedSessions
                = sessionDBContext.listByRequestIdAndUserid(userDataDetail.getIntegerAttribute("id"), userDataDetail.getIntegerAttribute("userid"));
        for (String selectedSession : selectedSessions) {
            content.append("-").append(selectedSession).append("<br>");
        }
        content.append("Sorry for this incovinient!<br>");
        requestDBContext.rejectRequest(userDataDetail.getIntegerAttribute("id"));
        notificationService.sendRequestNotificationForUser(userDataDetail, title, content.toString());
    }

    public void checkSchedule(int id){
        sessionDBContext.checkScheduleById(id);
    }
}
