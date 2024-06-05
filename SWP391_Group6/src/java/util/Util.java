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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.TreeMap;
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
    
    public static java.sql.Date convertUtilDateToSqlDate(java.util.Date utilDate) {
        if (utilDate != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(utilDate);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            return new java.sql.Date(cal.getTimeInMillis());
        } else {
            return null;
        }
    }

    public static Date addDaysToDate(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static ArrayList<java.sql.Date> getDatesBetween(java.sql.Date fromDate, java.sql.Date toDate) {
        if (fromDate == null || toDate == null) {
            throw new IllegalArgumentException("Both from date and to date can't be null");
        }
        if (fromDate.after(toDate)) {
            throw new IllegalArgumentException("From date can't be after as to date");
        }
        Date from = new Date(fromDate.getTime());
        Date to = new Date(toDate.getTime());
        Date temp = from;
        ArrayList<java.sql.Date> dates = new ArrayList<>();
        while (!temp.after(to)) {
            dates.add(convertUtilDateToSqlDate(temp));
            temp = addDaysToDate(temp, 1);
        }
        return dates;
    }

    public static Map<java.sql.Date, java.sql.Date> getWeeksByYear(int year) {
        int week = 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year); // Năm bạn muốn hiển thị
        calendar.set(Calendar.MONTH, Calendar.JANUARY); // Tháng 1 (index bắt đầu từ 0)
        calendar.set(Calendar.DAY_OF_MONTH, 1); // Ngày 1
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
        } else if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, 1);
        } else {
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                calendar.add(Calendar.DATE, -1);
            }
        }
        Map<java.sql.Date, java.sql.Date> weeks = new TreeMap<>();
        while (week != 53) {
            Date utilValue = calendar.getTime();
            java.sql.Date key = new java.sql.Date(utilValue.getTime());
            System.out.println("Tuần " + week + ": "
                    + calendar.get(Calendar.DATE) + " "
                    + calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, java.util.Locale.getDefault()) + " "
                    + calendar.get(Calendar.YEAR));
            calendar.add(Calendar.DATE, 6);
            utilValue = calendar.getTime();
            java.sql.Date value = new java.sql.Date(utilValue.getTime());
            weeks.put(key, value);
            calendar.add(Calendar.DATE, 1);
            utilValue = calendar.getTime();
            week++;
        }
        return weeks;
    }
}
