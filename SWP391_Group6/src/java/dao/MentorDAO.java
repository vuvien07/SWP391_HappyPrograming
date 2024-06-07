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
    String sql = "SELECT \n" +
                 "    m.id,\n" +
                 "    m.name,\n" +
                 "    m.phone,\n" +
                 "    a.email,\n" +
                 "    m.ava,\n" +
                 "    m.intro,\n" +
                 "    m.achivement,\n" +
                 "    m.experience,\n" +
                 "    m.certificate,\n" +
                 "    m.status\n" +
                 "FROM \n" +
                 "    Mentor m\n" +
                 "JOIN \n" +
                 "    Account a\n" +
                 "ON \n" +
                 "    m.accid = a.accid;";
    try (PreparedStatement ps = connection.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            Mentor m = new Mentor();
            m.setId(rs.getInt("id"));
            m.setName(rs.getString("name"));
            m.setPhone(rs.getString("phone"));
            m.setEmail(rs.getString("email"));
            m.setAva(rs.getString("ava"));
            m.setIntro(rs.getString("intro"));
            m.setAchievement(rs.getString("achivement"));
            m.setExperience(rs.getString("experience"));
            m.setCertificate(rs.getString("certificate"));
            m.setStatus(rs.getBoolean("status"));

            mentors.add(m);
        }
    } catch (SQLException e) {
        // Xử lý ngoại lệ SQL
        System.err.println("SQL exception occurred: " + e.getMessage());
        e.printStackTrace();
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
     
  public ArrayList<Mentor> getMentorByFilter(String id) {
    ArrayList<Mentor> mentors = new ArrayList<>();
    String sql = "SELECT " +
        "M.id, M.name, M.phone, A.email, M.ava, M.intro, M.achivement, M.experience, M.certificate, M.status " +
        "FROM " +
        "Mentor M " +
        "JOIN " +
        "Account A ON M.accid = A.accid " +
        "WHERE M.id = ?";
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
    String sql = "SELECT " +
        "M.id, M.name, M.phone, A.email, M.ava, M.intro, M.achivement, M.experience, M.certificate, M.status " +
        "FROM " +
        "Mentor M " +
        "JOIN " +
        "Account A ON M.accid = A.accid " +
        "WHERE " +
        "    M.name LIKE ?";
    try (PreparedStatement ps = connection.prepareStatement(sql)) {
        ps.setString(1, "%" + txtSearch + "%");
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Mentor m = new Mentor();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                m.setPhone(rs.getString("phone"));
                m.setEmail(rs.getString("email"));
                m.setAva(rs.getString("ava"));
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
        ArrayList<Mentor> mentors = mDao.getMentorBySearch("Jane Smith");

        for (Mentor o : mentors) {
            System.out.println(o);
        }
    }

    
}
