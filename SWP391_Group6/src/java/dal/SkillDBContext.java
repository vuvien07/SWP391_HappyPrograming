/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Skill;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class SkillDBContext extends DBContext<Skill>{

    @Override
    public ArrayList<Skill> listAll() {
        ArrayList<Skill> skills = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Skill";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Skill skill = new Skill();
                skill.setId(rs.getInt("id"));
                skill.setSkillname(rs.getString("skillname"));
                skill.setDescription(rs.getString("description"));
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
    
}
