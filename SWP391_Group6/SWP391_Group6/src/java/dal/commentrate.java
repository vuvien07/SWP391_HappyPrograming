/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import controller.feedback;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Feedback;
import model.Mentor;
import java.sql.*;
import model.User;


/**
 *
 * @author LENOVO
 */
public class commentrate  extends DBContext<Feedback>{

    @Override
    public ArrayList<Feedback> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Feedback entity) {
 try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Feedback] ([rate], [comment], [userid],[menid]) VALUES(?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getRate());
            ps.setString(2, entity.getComment());
            ps.setInt(3, entity.getMentor().getId());
            ps.setInt(4, entity.getUser().getId());
        
            
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e3) {
            }
        }    }

    @Override
    public void update(Feedback entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Feedback entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
public ArrayList<Feedback> listByFeedback() {
    ArrayList<Feedback> feedbacks = new ArrayList<>();
    try {
        String sql = "SELECT f.rate, f.comment, u.name AS user_name\n" +
                     "FROM Feedback f\n" +
                     "JOIN [User] u ON f.userid = u.userid\n" ; // Assuming you want to filter by user id
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            Feedback feedback = new Feedback();
            feedback.setRate(rs.getInt("rate"));
            feedback.setComment(rs.getString("comment"));
            User user = new User();
            user.setName(rs.getString("user_name"));
            feedback.setUser(user);
            
            feedbacks.add(feedback);
        }
    } catch (SQLException e) {
        // Handle SQLException
        e.printStackTrace();
    }
    return feedbacks;
}

    
}
