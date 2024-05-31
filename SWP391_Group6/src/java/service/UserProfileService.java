/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.AccountDAO;
import dao.UserDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import model.Account;
import model.User;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class UserProfileService {

    private AccountDAO accountDAO;
    private UserDAO userDAO;

    public UserProfileService() {
        accountDAO = new AccountDAO();
        userDAO = new UserDAO();
    }

    public void updateProfile(HttpServletRequest request, HttpServletResponse response, UserDataDetail userDataDetail) throws ServletException, IOException {
        Part fPart = request.getPart("avatar");
        Account updateAcc = new Account();
        updateAcc.setUsername((String) userDataDetail.getAttribute("username"));
        Account account = (Account) userDataDetail.getAttribute("account");
        accountDAO.updateById(updateAcc, account.getId());
        User user = (User) userDataDetail.getAttribute("user");
        User updateUser = new User();
        if (fPart != null) {
            String fileName = Paths.get(fPart.getSubmittedFileName()).getFileName().toString();
            String fileType = fPart.getContentType();
            if (fileType.equals("image/jpeg") || fileType.equals("image/png")) {
                String uploadDir = request.getServletContext().getRealPath("/resources/uploads");
                File uploadDirFile = new File(uploadDir);
                if (!uploadDirFile.exists()) {
                    uploadDirFile.mkdirs();
                }
                String filePath = uploadDir + "\\" + fileName;
                fPart.write(filePath);
                updateUser.setAva(fileName);
            } else {
                String image = request.getParameter("image-initiate");
                updateUser.setAva(image);
            }
        }

        updateUser.setName((String) userDataDetail.getAttribute("fullname"));
        updateUser.setGender(((String)userDataDetail.getAttribute("gender")).equals("Male"));
        updateUser.setDateOfBirth(java.sql.Date.valueOf((String) userDataDetail.getAttribute("dob")));
        updateUser.setAddress((String) userDataDetail.getAttribute("address"));
        updateUser.setAccount(account);
        userDAO.updateById(updateUser, user.getId());
        request.getSession().setAttribute("user", updateUser);
        request.getRequestDispatcher("WEB-INF/view/user/profile.jsp").forward(request, response);

    }

}
