/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dal.SlotDBContext;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import model.Slot;
import util.Util;

/**
 *
 * @author Admin
 */
public class ViewScheduleService {

    private final SlotDBContext slotDAO;

    public ViewScheduleService() {
        slotDAO = new SlotDBContext();
    }

    public void viewSchedule(HttpServletRequest request) {
        LocalDate localDate = LocalDate.now();
        Locale asiaLocale = new Locale("vi", "VN");
        WeekFields weekFields = WeekFields.of(asiaLocale);
        Map<java.sql.Date, java.sql.Date> weeksOnYear = Util.getWeeksByYear(localDate.getYear());
        ArrayList<Slot> slots = slotDAO.listAll();
        ArrayList<java.sql.Date> dates = null;
        Set<java.sql.Date> keySet = weeksOnYear.keySet();
        for (java.sql.Date object : keySet) {
            if (localDate.get(weekFields.weekOfYear()) == object.toLocalDate().get(weekFields.weekOfYear())) {
                dates = Util.getDatesBetween(object, weeksOnYear.get(object));
                break;
            }
        }
        request.getSession().setAttribute("dates", dates);
        request.setAttribute("currentWeek", localDate.get(weekFields.weekOfYear()));
        request.getSession().setAttribute("slots", slots);
        request.getSession().setAttribute("weeks_on_year", weeksOnYear);
        request.getSession().setAttribute("year", localDate.getYear());
    }

    public void viewScheduleByChange(HttpServletRequest request) {
        String week = request.getParameter("weeks");
        String weeks[] = week.split(" ");
        java.sql.Date from = java.sql.Date.valueOf(weeks[0]);
        java.sql.Date to = java.sql.Date.valueOf(weeks[1]);
        ArrayList<java.sql.Date> dates = Util.getDatesBetween(from, to);
        Locale asiaLocale = new Locale("vi", "VN");
        WeekFields weekFields = WeekFields.of(asiaLocale);
        request.getSession().setAttribute("dates", dates);
        request.setAttribute("changeWeek", from.toLocalDate().get(weekFields.weekOfYear()));
    }

}
