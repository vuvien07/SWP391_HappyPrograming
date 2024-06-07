/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.AccountDAO;
import dao.MentorDAO;
import dao.UserDAO;
import model.Account;
import model.Mentor;
import model.User;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class SignUpService {

    private AccountDAO accountDAO;
    private UserDAO userDAO;
    private MentorDAO mentorDAO;

    public SignUpService() {
        accountDAO = new AccountDAO();
        userDAO = new UserDAO();
        mentorDAO = new MentorDAO();
    }

    public AccountDAO getAccountDAO() {
        return accountDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public MentorDAO getMentorDAO() {
        return mentorDAO;
    }

    public void registerUser(UserDataDetail userDataDetail) {
        Account newAcc = new Account();
        newAcc.setUsername((String) userDataDetail.getAttribute("username"));
        newAcc.setEmail((String) userDataDetail.getAttribute("email"));
        newAcc.setPassword((String) userDataDetail.getAttribute("pass"));
        newAcc.setStatus(true);
        if (userDataDetail.getAttribute("role").equals("Mentee")) {
            newAcc.setRoleid(2);
            accountDAO.insert(newAcc);
            User newMen = new User();
            newMen.setName((String) userDataDetail.getAttribute("name"));
            newMen.setGender("Male".equals(userDataDetail.getAttribute("gender")));
            newMen.setPhone((String) userDataDetail.getAttribute("phone"));
            newMen.setAddress((String) userDataDetail.getAttribute("address"));
            newMen.setDateOfBirth(java.sql.Date.valueOf((String) userDataDetail.getAttribute("dob")));
            Account account = accountDAO.getAccount((String) userDataDetail.getAttribute("username"), (String) userDataDetail.getAttribute("pass"));
            if (account != null) {
                newMen.setAccount(account);
                userDAO.insert(newMen);
            }
        } else {
            newAcc.setRoleid(1);
            accountDAO.insert(newAcc);
            Mentor newMentor = new Mentor();
            newMentor.setName((String) userDataDetail.getAttribute("name"));
            newMentor.setGender("Male".equals(userDataDetail.getAttribute("gender")));
            newMentor.setPhone((String) userDataDetail.getAttribute("phone"));
            newMentor.setAddress((String) userDataDetail.getAttribute("address"));
            newMentor.setDateOfBirth(java.sql.Date.valueOf((String) userDataDetail.getAttribute("dob")));
            newMentor.setStatus(false);
            Account account = accountDAO.getAccount((String) userDataDetail.getAttribute("username"), (String) userDataDetail.getAttribute("pass"));
            if (account != null) {
                newMentor.setAccount(account);
                mentorDAO.insertDefault(newMentor);
            }
        }
    }
}
