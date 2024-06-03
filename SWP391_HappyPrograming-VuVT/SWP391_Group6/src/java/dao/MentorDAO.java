/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Mentor;
import model.MentorSkill;
import model.Skill;

/**
 *
 * @author Admin
 */
public class MentorDAO extends DAO<Mentor>{

    @Override
    public ArrayList<Mentor> listAll() {
        ArrayList<Mentor> mentors = new ArrayList<>();
        try {
            String sql = "SELECT m.id, m.ava, m.job, m.skill, m.intro, m.achivement, m.feedbackid, m.experience, \n" +
"       m.certificate, m.profileid, m.status, m.accid, \n" +
"       u.name, u.phone, a.email\n" +
"FROM Mentor m\n" +
"JOIN [User] u ON m.accid = u.accid\n" +
"JOIN Account a ON u.accid = a.accid;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentor m = new Mentor();
                m.setId(rs.getInt("id"));
                 m.setName(rs.getString("name"));
                m.setPhone(rs.getString("phone"));
                m.setEmail(rs.getString("email"));
                m.setAva(rs.getString("ava"));
                m.setJob(rs.getString("job"));
                m.setSkill(rs.getString("skill"));
                m.setIntro(rs.getString("intro"));
                m.setAchievement(rs.getString("achivement"));
                m.setExperience(rs.getString("experience"));
                m.setCertificate(rs.getString("certificate"));
                m.setStatus(rs.getBoolean("status"));
                
                mentors.add(m);
            }
        } catch (SQLException e) {
        }
        return mentors;
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
    
    public void insertDefault(Mentor entity){
        try{
            connection.setAutoCommit(false);
            String sql = "INSERT INTO Mentor([name], gender, phone, [address], dateofbirth, [status], accid) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getName());
            ps.setBoolean(2, entity.isGender());
            ps.setString(3, entity.getPhone());
            ps.setString(4, entity.getAddress());
            ps.setDate(5, (Date) entity.getDateOfBirth());
            ps.setBoolean(6, entity.isStatus());
            ps.setInt(7, entity.getAccount().getId());
            ps.executeUpdate();
            connection.commit();
        }catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e3) {
            }
        }
    }
     public List<MentorSkill> getMentorSkills() {
        List<MentorSkill> list = new ArrayList<>();
        String query = "SELECT m.id AS MentorID, s.skillname AS SkillName " +
                       "FROM Mentor m " +
                       "JOIN Mentor_Skill ms ON m.id = ms.mentorid " +
                       "JOIN Skill s ON ms.skillid = s.id";
        
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                MentorSkill mentorSkill = new MentorSkill();
                mentorSkill.setMentorId(rs.getInt("MentorID"));
                mentorSkill.setSkillName(rs.getString("SkillName"));
                list.add(mentorSkill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }
    public ArrayList<Mentor> getMentorByFilter(String id) {
        ArrayList<Mentor> mentors = new ArrayList<>();
        String sql = "SELECT m.id, m.ava, m.job, m.skill, m.intro, m.achivement, m.feedbackid, m.experience, " +
                     "m.certificate, m.profileid, m.status, m.accid, " +
                     "u.name, u.phone, a.email " +
                     "FROM Mentor m " +
                     "JOIN [User] u ON m.accid = u.accid " +
                     "JOIN Account a ON u.accid = a.accid " +
                     "WHERE m.id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mentor m = new Mentor();
                    m.setId(rs.getInt("id"));
                    m.setName(rs.getString("name"));
                    m.setPhone(rs.getString("phone"));
                    m.setEmail(rs.getString("email"));
                    m.setAva(rs.getString("ava"));
                    m.setJob(rs.getString("job"));
                    m.setSkill(rs.getString("skill"));
                    m.setIntro(rs.getString("intro"));
                    m.setAchievement(rs.getString("achivement"));
                    m.setExperience(rs.getString("experience"));
                    m.setCertificate(rs.getString("certificate"));
                    m.setStatus(rs.getBoolean("status"));
                    mentors.add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentors;
    }
     public ArrayList<Mentor> getMentorBySearch(String txtSearch) {
        ArrayList<Mentor> mentors = new ArrayList<>();
        String sql = "SELECT \n" +
"    m.id, \n" +
"    m.ava, \n" +
"    m.job, \n" +
"    m.skill, \n" +
"    m.intro, \n" +
"    m.achivement, \n" +
"    m.feedbackid, \n" +
"    m.experience, \n" +
"    m.certificate, \n" +
"    m.profileid, \n" +
"    m.status, \n" +
"    m.accid, \n" +
"    u.name, \n" +
"    u.phone, \n" +
"    a.email \n" +
"FROM \n" +
"    Mentor m \n" +
"JOIN \n" +
"    [User] u ON m.accid = u.accid \n" +
"JOIN \n" +
"    Account a ON u.accid = a.accid \n" +
"WHERE \n" +
"    u.name LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%"+txtSearch+"%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Mentor m = new Mentor();
                    m.setId(rs.getInt("id"));
                    m.setName(rs.getString("name"));
                    m.setPhone(rs.getString("phone"));
                    m.setEmail(rs.getString("email"));
                    m.setAva(rs.getString("ava"));
                    m.setJob(rs.getString("job"));
                    m.setSkill(rs.getString("skill"));
                    m.setIntro(rs.getString("intro"));
                    m.setAchievement(rs.getString("achivement"));
                    m.setExperience(rs.getString("experience"));
                    m.setCertificate(rs.getString("certificate"));
                    m.setStatus(rs.getBoolean("status"));
                    mentors.add(m);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mentors;
    }
         

    public static void main(String[] args) {
        MentorDAO mDao = new MentorDAO();
        String id = "John Doe"; // Ensure id is a string
        ArrayList<Mentor> mentors = mDao.getMentorBySearch(id);

        for (Mentor o : mentors) {
            System.out.println(o);
        }
    }

    
}
