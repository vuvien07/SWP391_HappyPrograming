/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.admin;

import dal.SkillDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import model.Skill;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class AdminService {

    private SkillDBContext skillDBContext;

    public AdminService() {
        skillDBContext = new SkillDBContext();
    }

    public void handleUpdateSkill(UserDataDetail userDataDetail, HttpServletRequest request) throws IOException, ServletException {
        Skill skill = new Skill();
        skill.setId(Integer.parseInt((String) userDataDetail.getAttribute("id")));
        skill.setSkillname((String) userDataDetail.getAttribute("skillname"));
        skill.setDescription((String) userDataDetail.getAttribute("description"));
        skill.setStatus(((String) userDataDetail.getAttribute("status") != null));
        Part fPart = request.getPart("image");
        if (fPart != null) {
            String fileName = Paths.get(fPart.getSubmittedFileName()).getFileName().toString();
            String fileType = fPart.getContentType();
            if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
                String uploadDir = request.getServletContext().getRealPath("/assets/uploads/skill");
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String filePath = uploadDir + "\\" + fileName;
                fPart.write(filePath);
                skill.setAva(fileName);
            }
        }
        skillDBContext.editSkill(skill);
    }

    public void handleCreateSkill(UserDataDetail userDataDetail, HttpServletRequest request) throws IOException, ServletException {
        Skill skill = new Skill();
        skill.setSkillname((String) userDataDetail.getAttribute("skillName"));
        Part fPart = request.getPart("image");
        if (fPart != null) {
            String fileName = Paths.get(fPart.getSubmittedFileName()).getFileName().toString();
            String fileType = fPart.getContentType();
            if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
                String uploadDir = request.getServletContext().getRealPath("/assets/uploads/skill");
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String filePath = uploadDir + "\\" + fileName;
                fPart.write(filePath);
                skill.setAva(fileName);
            }
        }
        skill.setDescription((String) userDataDetail.getAttribute("description"));
        skillDBContext.insert(skill);
    }
}
