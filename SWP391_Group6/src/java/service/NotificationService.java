/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import controller.user.RequestController;
import dal.NotificationDBContext;
import jakarta.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Mentor;
import model.MentorNotification;
import model.User;
import model.UserNotification;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class NotificationService {
    private NotificationDBContext notificationDBContext;

    public NotificationService() {
        notificationDBContext = new NotificationDBContext();
    }
    
    public ArrayList<UserNotification> getNotificationForUser(int userid){
        return notificationDBContext.listByUserId(userid);
    }
    
    public void sendRequestNotificationForMentor(HttpServletRequest request, String content){
        Account account = (Account) request.getSession().getAttribute("account");
        MentorNotification mentorNotification = new MentorNotification();
        mentorNotification.setTitle("You have new request from user " + account.getUsername());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateTime = (new StringBuilder().append(new java.sql.Date(System.currentTimeMillis())).append(" ").append(new java.sql.Time(System.currentTimeMillis()))).toString();
        try {
            mentorNotification.setCreatedAt(sdf.parse(dateTime));
        } catch (ParseException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mentorNotification.setContent(content);
        mentorNotification.setIsRead(false);
        Mentor mentor = (Mentor) request.getSession().getAttribute("mentor");
        mentorNotification.setMentor(mentor);
        notificationDBContext.createMentorNotification(mentorNotification);
    }
    
    public void sendRequestNotificationForUser(UserDataDetail udd, String title, String content){
        User user = (User)udd.getAttribute("user");
        UserNotification userNotification = new UserNotification();
        userNotification.setTitle(title);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateTime = (new StringBuilder().append(new java.sql.Date(System.currentTimeMillis())).append(" ").append(new java.sql.Time(System.currentTimeMillis()))).toString();
        try {
            userNotification.setCreatedAt(sdf.parse(dateTime));
        } catch (ParseException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        userNotification.setContent(content);
        userNotification.setIsRead(false);
        userNotification.setUser(user);
        notificationDBContext.createUserNotification(userNotification);
    }
}
