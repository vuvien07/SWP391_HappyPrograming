/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import model.Skill;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class SkillDAO extends DBContext<Skill> {

    @Override
    public ArrayList<Skill> listAll() {
        ArrayList<Skill> skills = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Skill s WHERE s.[status] = 1";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getInt("id"));
                skill.setSkillname(rs.getString("skillname"));
                skill.setDescription(rs.getString("description"));
                skill.setAva(rs.getString("ava"));
                skills.add(skill);
            }
        } catch (SQLException e) {
        }
        return skills;
    }

    @Override
    public void insert(Skill entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Skill entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Skill entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Skill> listByMentor(int id) {
        ArrayList<Skill> skills = new ArrayList<>();
        try {
            String sql = "SELECT s.id, s.skillname FROM Mentor m JOIN Mentor_Skill mt ON m.id = mt.mentorid JOIN Skill s ON s.id = mt.skillid\n"
                    + "WHERE s.[status] = 1 AND m.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getInt("id"));
                skill.setSkillname(rs.getString("skillname"));
                skills.add(skill);
            }
        } catch (SQLException e) {
        }
        return skills;
    }

    public ArrayList<Skill> listAllForAdmin() {
        ArrayList<Skill> skills = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Skill";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getInt("id"));
                skill.setSkillname(rs.getString("skillname"));
                skill.setStatus(rs.getBoolean("status"));
                skill.setDescription(rs.getString("description"));
                skill.setAva(rs.getString("ava"));
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }
    
    public Skill getSkillById(int id){
        try {
            String sql = "SELECT * FROM Skill s WHERE s.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Skill skill = new Skill();
                skill.setId(rs.getInt("id"));
                skill.setSkillname(rs.getString("skillname"));
                skill.setStatus(rs.getBoolean("status"));
                skill.setDescription(rs.getString("description"));
                skill.setAva(rs.getString("ava"));
                return skill;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void editSkill(int skillID, String skillName, boolean status, String description, String image) {
        String sql = "UPDATE [dbo].[Skill]\n"
                + "   SET [skillname] = ?,\n"
                + "       [status] = ?,\n"
                + "       [description] = ?";

        if (!image.equals("")) {
            sql += ",\n [ava] = ?";
        }

        sql += " WHERE [id] = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, skillName);
            st.setBoolean(2, status);
            st.setString(3, description);
            
            if (!image.equals("")) {
                st.setString(4, image);
                st.setInt(5, skillID);
            } else {
                st.setInt(4, skillID);
            }

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
