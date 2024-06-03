/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Mentor;

/**
 *
 * @author Admin
 */
public class MentorDBContext extends DBContext<Mentor> {

    @Override
    public ArrayList<Mentor> listAll() {
        ArrayList<Mentor> mentors = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Mentor";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mentor mentor = new Mentor();
                mentor.setId(rs.getInt("id"));
                mentors.add(mentor);
            }
        } catch (SQLException e) {
        }
        return mentors;
    }

//    public ArrayList<Mentor> getCVMentor() {
//        ArrayList<Mentor> mentors = new ArrayList<>();
//        try {
//            String sql = " SELECT id, ava, job, skill, intro, achivement, experience, certificate, status FROM Mentor";
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Mentor mentor = new Mentor();
//                mentor.setId(rs.getInt(1));
//                mentor.setAva(rs.getString(7));
//                mentor.setJob(rs.getString(8));
//                mentor.setSkill(rs.getString(9));
//                mentor.setIntro(rs.getString(10));
//                mentor.setAchievement(rs.getString(11));
//                mentor.setExperience(rs.getString(13));
//                mentor.setCertificate(rs.getString(14));
//                mentor.setStatus(rs.getBoolean(15));
//                mentors.add(mentor);
//            }
//        } catch (Exception e) {
//        }
//        return mentors;
//    }
    public ArrayList<Mentor> getInfoMentor() {
        ArrayList<Mentor> mentors = new ArrayList<>();
        try {
                    String sql = """
            SELECT m.id, m.name, u.phone 
            FROM Mentor m 
            JOIN [HappyPrograming].[dbo].[User] u ON m.accid = u.userid
            WHERE m.status = 0""";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Mentor mentor = new Mentor();
                mentor.setId(rs.getInt("id"));
                mentor.setName(rs.getString("name"));
                mentor.setPhone(rs.getString("phone"));
                mentors.add(mentor);
            }
        } catch (Exception e) {
        }
        return mentors;
    }

    public void changeStatus(int mentorId) {
        String getStatusSql = "SELECT [status] FROM Mentor WHERE [id] = ?";
        String updateStatusSql = "UPDATE Mentor SET [status] = ? WHERE [id] = ?";
        try {
            // Lấy trạng thái hiện tại
            PreparedStatement getStatusPs = connection.prepareStatement(getStatusSql);
            getStatusPs.setInt(1, mentorId);
            ResultSet rs = getStatusPs.executeQuery();
            if (rs.next()) {
                boolean currentStatus = rs.getBoolean("status");

                // Đảo ngược trạng thái
                boolean newStatus = !currentStatus;

                // Cập nhật trạng thái mới
                PreparedStatement updateStatusPs = connection.prepareStatement(updateStatusSql);
                updateStatusPs.setBoolean(1, newStatus);
                updateStatusPs.setInt(2, mentorId);
                updateStatusPs.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//        public List<Product> getProductsWithPagging(int page, int PAGE_SIZE) {
//        List<Product> list = new ArrayList<>();
//        try {
//            String sql = "select * from Product order by productId \n"
//                    + "offset (?-1)*? row fetch next ? rows only ";
//
//            conn = new DBContext().getConnection();
//
//            ps = conn.prepareStatement(sql);
//            ps.setInt(1, page);
//            ps.setInt(2, PAGE_SIZE);
//            ps.setInt(3, PAGE_SIZE);
//
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                Product product = new Product();
//                product.setProductId(rs.getInt(1));
//                product.setProductName(rs.getString(2));
//                product.setQuantity(rs.getInt(3));
//                product.setPrice(rs.getInt(4));
//                product.setDescript(rs.getString(5));
//                product.setImageUrl(rs.getString(6));
//                product.setCreateDate(rs.getDate(7));
//                product.setCategoryId(rs.getInt(8));
//                list.add(product);
//
//            }
//        } catch (Exception e) {
//        }
//        return list;
//    }
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
            String sql = "INSERT INTO Mentor(id, [name], gender, phone, [address], dateofbirth, [status], accid) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            ps.setString(2, entity.getName());
            ps.setBoolean(3, entity.isGender());
            ps.setString(4, entity.getPhone());
            ps.setString(5, entity.getAddress());
            ps.setDate(6, (Date) entity.getDateOfBirth());
            ps.setBoolean(7, entity.isStatus());
            ps.setInt(8, entity.getAccount().getId());
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

}
