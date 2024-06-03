/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Mentor;

/**
 *
 * @author Admin
 */
public class MentorDAO extends DBContext<Mentor> {

    @Override
    public ArrayList<Mentor> listAll() {
        ArrayList<Mentor> mentors = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Mentor";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentor m = new Mentor();
                m.setId(rs.getInt("id"));
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

    public void insertDefault(Mentor entity) {
        try {
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
        }
    }

    public ArrayList<Mentor> listBySkill(int id) {
        ArrayList<Mentor> mentors = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Mentor m JOIN Mentor_Skill mt ON m.id = mt.mentorid JOIN Skill s ON s.id = mt.skillid\n"
                    + "WHERE s.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Mentor m = new Mentor();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                mentors.add(m);
            }
        } catch (SQLException e) {

        }
        return mentors;
    }

}
