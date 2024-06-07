/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.mentor;

import dal.MentorDBContext;
import dal.SessionDBContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import model.Account;
import model.Mentor;
import model.Session;
import model.Slot;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class MentorService {

    private SessionDBContext sessionDAO;
    private MentorDBContext mentorDAO;

    public MentorService() {
        sessionDAO = new SessionDBContext();
        mentorDAO = new MentorDBContext();
    }

    public void createSchedule(UserDataDetail userDataDetail, HttpServletRequest request, HttpServletResponse response) throws SQLException{
        Date freeDate = Date.valueOf((String) userDataDetail.getAttribute("freeDate"));
        int slotid = Integer.parseInt((String) userDataDetail.getAttribute("freeSlot"));
        int menid = Integer.parseInt((String) userDataDetail.getAttribute("menid"));
        if (sessionDAO.isDuplicatedSession(freeDate, slotid, menid)) {
            request.getSession().setAttribute("err", "Schedule is duplicated. Please try again!");
        } else {
            Account menAccount = (Account) userDataDetail.getAttribute("account");
            Slot slot = new Slot();
            slot.setId(Integer.parseInt((String) userDataDetail.getAttribute("freeSlot")));
            Mentor mentor = mentorDAO.getByAccountId(menAccount.getId());
            mentor.setId(mentor.getId());
            Session session = new Session();
            session.setDate(Date.valueOf((String) userDataDetail.getAttribute("freeDate")));
            session.setSlot(slot);
            session.setMentor(mentor);
            session.setSkill((String) userDataDetail.getAttribute("skill"));
            try {
                sessionDAO.insert(session);
            } catch (Exception e) {
            }finally{
                request.getSession().setAttribute("success", "Create schedule successful!");
            }
        }
    }

}
