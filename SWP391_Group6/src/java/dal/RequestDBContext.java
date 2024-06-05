/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.Request;
import java.sql.*;
import model.Mentor;

/**
 *
 * @author Admin
 */
public class RequestDBContext extends DBContext<Request> {

    @Override
    public ArrayList<Request> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Request entity) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO Request(title, deadlineTime,skills,contentRequest,[status],userid, menid) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getTitle());
            ps.setTimestamp(2, Timestamp.valueOf(entity.getDeadlineTime()));
            ps.setString(3, entity.getSkill());
            ps.setString(4, entity.getContent());
            ps.setString(5, "Processing");
            ps.setInt(6, entity.getUser().getId());
            ps.setInt(7, entity.getMentor().getId());
            ps.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e3) {
                e3.printStackTrace();
            }
        }
    }

    @Override
    public void update(Request entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Request entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Request> listByUserId(int id) {
        ArrayList<Request> requests = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Request r WHERE r.userid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Request request = new Request();
                request.setId(rs.getInt("id"));
                request.setTitle(rs.getString("title"));
                request.setDeadlineTime(rs.getString("deadlineTime"));
                request.setSkill(rs.getString("skills"));
                request.setContent(rs.getString("contentRequest"));
                requests.add(request);
            }
        } catch (Exception e) {
        }
        return requests;
    }

    public Request getById(int id) {
        try {
            String sql = "SELECT * FROM Request r WHERE r.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Mentor m = new Mentor();
                m.setId(rs.getInt("menid"));
                Request request = new Request();
                request.setId(rs.getInt("id"));
                request.setTitle(rs.getString("title"));
                request.setDeadlineTime(rs.getString("deadlineTime"));
                request.setSkill(rs.getString("skills"));
                request.setContent(rs.getString("contentRequest"));
                request.setMentor(m);
                return request;
            }
        } catch (Exception e) {
        }
        return null;
    }

}
