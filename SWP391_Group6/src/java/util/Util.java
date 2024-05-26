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
import java.util.Date;
import java.util.Properties;
import java.util.Random;

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
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
