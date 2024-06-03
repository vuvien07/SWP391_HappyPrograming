/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.mentor;

import dao.SessionDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UserDataDetail;

/**
 *
 * @author Admin
 */
public class CreateScheduleService {
    private SessionDAO sessionDAO;

    public CreateScheduleService() {
        sessionDAO = new SessionDAO();
    }
    
    public void createSchedule(UserDataDetail userDataDetail, HttpServletRequest request, HttpServletResponse response){
        
    }
    
}
