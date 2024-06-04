/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Mentor;

/**
 *
 * @author Admin
 */
public class MentorDAO extends DBContext<Mentor> {

    // Existing getCvMentorByAccid method
    public List<Mentor> getCvMentorByAccid() {
        List<Mentor> listFound = new ArrayList<>();
             String sql = "SELECT " +
                     "m.id AS mentor_id, m.name AS mentor_name, m.gender, m.phone, m.address, " +
                     "m.dateofbirth, m.ava AS mentor_avatar, m.job, m.intro, m.achivement, " +
                     "m.experience, m.certificate, m.status, m.accid, " +
                     "s.id AS skill_id, s.skillname, s.status AS skill_status, " +
                     "s.description AS skill_description, s.ava AS skill_avatar " +
                     "FROM Mentor m " +
                     "JOIN Mentor_Skill ms ON m.id = ms.mentorid " +
                     "JOIN Skill s ON ms.skillid = s.id " +
                     "WHERE m.id = ?";
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int accid = resultSet.getInt("accid");
                String mentorUsername = resultSet.getString("mentorUsername").trim();
                String job = resultSet.getString("job").trim();
                String intro = resultSet.getString("intro").trim();
                String achievement = resultSet.getString("achievement").trim();
                String experience = resultSet.getString("experience").trim();
                String certificate = resultSet.getString("certificate").trim();
                boolean status = resultSet.getBoolean("status");
                String ava = resultSet.getString("ava").trim();
                String email = resultSet.getString("email").trim();
                String accountUsername = resultSet.getString("accountUsername").trim();
                String name = resultSet.getString("name").trim();
                boolean gender = resultSet.getBoolean("gender");
                String phone = resultSet.getString("phone").trim();
                String address = resultSet.getString("address").trim();
                Date dateOfBirth = resultSet.getDate("dateofbirth");
                String skill = resultSet.getString("skill").trim();

                Account account = new Account(accid, accountUsername, email);
                Mentor mentor = new Mentor();
                mentor.setId(resultSet.getInt("mentor_id"));
                mentor.setName(resultSet.getString("mentor_name"));
                mentor.setGender(resultSet.getBoolean("gender"));
                mentor.setPhone(resultSet.getString("phone"));
                mentor.setAddress(resultSet.getString("address"));
                mentor.setDateOfBirth(resultSet.getDate("dateofbirth"));
                mentor.setAva(resultSet.getString("mentor_avatar"));
                mentor.setJob(resultSet.getString("job"));
                mentor.setIntro(resultSet.getString("intro"));
                mentor.setAchievement(resultSet.getString("achivement"));
                mentor.setExperience(resultSet.getString("experience"));
                mentor.setCertificate(resultSet.getString("certificate"));
                mentor.setStatus(resultSet.getBoolean("status"));
                
                listFound.add(mentor);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return listFound;
    }
    

//    public boolean createCv(Mentor mentor) {
//        String sql = "INSERT INTO dbo.Mentor (accid, username, job, skill, intro, achievement, experience, certificate, status, ava, name, gender, phone, address, dateofbirth) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql);
//            statement.setInt(1, mentor.getId());
//            statement.setString(2, mentor.getAccount().getUsername());
//            statement.setString(3, mentor.getJob());
//            statement.setString(4, mentor.getSkill());
//            statement.setString(5, mentor.getIntro());
//            statement.setString(6, mentor.getAchievement());
//            statement.setString(7, mentor.getExperience());
//            statement.setString(8, mentor.getCertificate());
//            statement.setBoolean(9, mentor.isStatus());
//            statement.setString(10, mentor.getAva());
//            statement.setString(11, mentor.getName());
//            statement.setBoolean(12, mentor.isGender());
//            statement.setString(13, mentor.getPhone());
//            statement.setString(14, mentor.getAddress());
//            statement.setDate(15, new java.sql.Date(mentor.getDateOfBirth().getTime()));
//            int rowsInserted = statement.executeUpdate();
//            return rowsInserted > 0;
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            return false;
//        } finally {
//            try {
//                if (statement != null) statement.close();
//                if (connection != null) connection.close();
//            } catch (SQLException e) {
//                System.err.println(e.getMessage());
//            }
//        }
//    }
//
//    // Modified update method
//    public boolean updateCv(Mentor mentor) {
//        String sql = "UPDATE dbo.Mentor SET username = ?, job = ?, skill = ?, intro = ?, achievement = ?, experience = ?, certificate = ?, status = ?, ava = ?, name = ?, gender = ?, phone = ?, address = ?, dateofbirth = ? WHERE accid = ?";
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql);
//            statement.setString(1, mentor.getAccount().getUsername());
//            statement.setString(2, mentor.getJob());
//            statement.setString(3, mentor.getSkill());
//            statement.setString(4, mentor.getIntro());
//            statement.setString(5, mentor.getAchievement());
//            statement.setString(6, mentor.getExperience());
//            statement.setString(7, mentor.getCertificate());
//            statement.setBoolean(8, mentor.isStatus());
//            statement.setString(9, mentor.getAva());
//            statement.setString(10, mentor.getName());
//            statement.setBoolean(11, mentor.isGender());
//            statement.setString(12, mentor.getPhone());
//            statement.setString(13, mentor.getAddress());
//            statement.setDate(14, new java.sql.Date(mentor.getDateOfBirth().getTime()));
//            statement.setInt(15, mentor.getId());
//            int rowsUpdated = statement.executeUpdate();
//            return rowsUpdated > 0;
//        } catch (SQLException e) {
//            System.err.println(e.getMessage());
//            return false;
//        } finally {
//            try {
//                if (statement != null) statement.close();
//                if (connection != null) connection.close();
//            } catch (SQLException e) {
//                System.err.println(e.getMessage());
//            }
//        }
//    }

    @Override
    public ArrayList<Mentor> listAll() {
        return null;
    }

    @Override
    public void insert(Mentor entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Mentor entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Mentor entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
