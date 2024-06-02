/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Skill;
import java.sql.*;
import java.util.List;

public class SkillDBContext extends DBContext<Skill> {

    @Override
    public ArrayList<Skill> listAll() {
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
                skill.setImage(rs.getString("image"));
                skills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skills;
    }

    public List<Skill> getListByPage(List<Skill> list, int start, int end) {
        ArrayList<Skill> array = new ArrayList<>();
        for (int i = start; i < end; i++) {
            array.add(list.get(i));
        }
        return array;
    }

    public List<Skill> getSkillsSearch(String text) {
        List<Skill> list = new ArrayList<>();
        String sql = "SELECT * FROM Skill WHERE skillname LIKE ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + text + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill(
                        rs.getInt("id"),
                        rs.getString("skillname"),
                        rs.getBoolean("status"),
                        rs.getString("description"),
                        rs.getString("image")
                );
                list.add(skill);
            }
        } catch (SQLException e) {
            // Handle exception
        }
        return list;
    }
    
    public Skill getSkillByID(int id) {
    String sql = "SELECT * FROM Skill WHERE id = ?";
    try {
        PreparedStatement st = connection.prepareStatement(sql);
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            Skill skill = new Skill(
                    rs.getInt("id"),
                    rs.getString("skillname"),
                    rs.getBoolean("status"),
                    rs.getString("description"),
                    rs.getString("image")
            );
            return skill;
        }
    } catch (Exception e) {
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
            sql += ",\n       [image] = ?";
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

    @Override
    public void insert(Skill entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Skill entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(Skill entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
