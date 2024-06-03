/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import model.Skill;

/**
 *
 * @author Admin
 */
public class Util {

    public static String generatePassword(String str, int numOfChars) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < numOfChars; i++) {
            sb.append(str.charAt(rand.nextInt(str.length() - 1)));
        }
        return sb.toString();
    }
    
    public static void sendEmail(String to, String content) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //smtp host
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        final String user = Message.HOST_EMAIL;
        final String password = Message.HOST_PASSWORD;

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.setFrom(new InternetAddress(user));
            msg.setRecipients(jakarta.mail.Message.RecipientType.TO, InternetAddress.parse(to, false));
            msg.setSubject("Try to send email");
            msg.setSentDate(new Date());
            msg.setText(content, "UTF-8");
            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getRequestValue(HttpServletRequest request, String key){
        return request.getParameter(key);
    }
    
    public static void setRequestAttribute(HttpServletRequest request, String key, Object value){
        request.setAttribute(key, value);
    }
    
    public static Object getSessionValue(HttpServletRequest request, String key){
        return request.getSession().getAttribute(key);
    }
    
    public static void setSessionAttribute(HttpServletRequest request, String key, Object value){
        request.getSession().setAttribute(key, value);
    }
    
    public static List<Object> listByPage(List<Object> objects, String xPage, int numPerPage) {
        List<Object> results = new ArrayList<>();
        int size = objects.size(), page;
        int num = (size % numPerPage == 0) ? (size / numPerPage) : ((size / (numPerPage + 1)) + 1);
        if (xPage == null) {
            page = 1;
        }else{
            page = Integer.parseInt(xPage);
        }
        int start, end = 0;
        start = (page - 1) * numPerPage;
        end = Math.min(page * numPerPage, size);
        for (int i = start; i < end; i++) {
            results.add(objects.get(i));
        }
        return results;
    }
}
