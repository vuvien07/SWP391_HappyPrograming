
package controller;

import dal.AccountDBContext;
import dal.MentorDBContext;
import dal.UserDBContext;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Account;
import model.Mentor;
import model.User;

public class SignUpController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/user/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String status = (String) request.getSession().getAttribute("status");
        AccountDBContext adc = new AccountDBContext();
        UserDBContext udc = new UserDBContext();
        MentorDBContext mdc = new MentorDBContext();
        try {
            if (status == null) {
                String fullname = request.getParameter("name");
                String email = request.getParameter("email");
                String username = request.getParameter("username");
                String password = request.getParameter("pass");
                String phone = request.getParameter("phone");
                String address = request.getParameter("add");
                String dob = request.getParameter("dob");
                String gender = request.getParameter("gender");
                String role = request.getParameter("role");
                Account a = adc.checkAccountExist(username, email);
                if (a != null) {
                    request.setAttribute("err", "Username or email are existed. Please try again!");
                    request.getRequestDispatcher("WEB-INF/view/user/signup.jsp").forward(request, response);
                    return;
                }
                session.setAttribute("name", fullname);
                session.setAttribute("username", username);
                session.setAttribute("pass", password);
                session.setAttribute("phone", phone);
                session.setAttribute("address", address);
                session.setAttribute("dob", dob);
                session.setAttribute("gender", gender);
                session.setAttribute("role", role);
                request.getSession().setAttribute("email", email);
                response.sendRedirect("verify");
            } else {
                String name = (String) session.getAttribute("name");
                String username = (String) session.getAttribute("username");
                String password = (String) session.getAttribute("pass");
                String phone = (String) session.getAttribute("phone");
                String addess = (String) session.getAttribute("address");
                String dob = (String) session.getAttribute("dob");
                String gender = (String) session.getAttribute("gender");
                String email = (String) session.getAttribute("email");
                String role = (String) session.getAttribute("role");
                Account newAcc = new Account();
                ArrayList<Account> accounts = adc.listAll();
                int accountSize = accounts.size() + 1;
                newAcc.setId(accountSize);
                newAcc.setUsername(username);
                newAcc.setEmail(email);
                newAcc.setPassword(password);
                newAcc.setStatus(true);
                if (role.equals("Mentee")) {
                    ArrayList<User> mentees = udc.listAll();
                    User newMen = new User();
                    int userSize = mentees.size() + 1;
                    newMen.setId(userSize);
                    newMen.setName(name);
                    newMen.setGender("Male".equals(gender));
                    newMen.setPhone(phone);
                    newMen.setAddress(addess);
                    newMen.setDateOfBirth(java.sql.Date.valueOf(dob));
                    newMen.setAccount(newAcc);
                    newAcc.setRole(role);
                    adc.insert(newAcc);
                    udc.insert(newMen);
                }else{
                    newAcc.setRole(role);
                    ArrayList<Mentor> mentors = mdc.listAll();
                    int mentorSize = mentors.size() + 1;
                    Mentor newMentor = new Mentor();
                    newMentor.setId(mentorSize);
                    newMentor.setName(name);
                    newMentor.setGender("Male".equals(gender));
                    newMentor.setPhone(phone);
                    newMentor.setAddress(addess);
                    newMentor.setDateOfBirth(java.sql.Date.valueOf(dob));
                    newMentor.setStatus(false);
                    newMentor.setAccount(newAcc);
                    adc.insert(newAcc);
                    mdc.insertDefault(newMentor);
                }
                session.removeAttribute("status");
                request.setAttribute("success", "Sign up sucessfully! You can login to our system");
                request.getRequestDispatcher("WEB-INF/view/user/login.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occured. Please try again later!");
        }

    }

}
