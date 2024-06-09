/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.mentor;

import dal.CVDBContext;
import dal.MentorDBContext;
import dal.SessionDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import model.Account;
import model.CV;
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
    private CVDBContext cvDBContext;

    public MentorService() {
        sessionDAO = new SessionDBContext();
        mentorDAO = new MentorDBContext();
        cvDBContext = new CVDBContext();
    }

    public void createSchedule(UserDataDetail userDataDetail, HttpServletRequest request, HttpServletResponse response) throws SQLException {
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
            } finally {
                request.getSession().setAttribute("success", "Create schedule successful!");
            }
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
}
