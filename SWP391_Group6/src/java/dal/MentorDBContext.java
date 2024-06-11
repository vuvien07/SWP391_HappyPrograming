/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import model.Account;
import model.Mentor;
import model.Skill;

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
            while (rs.next()) {
                Mentor m = new Mentor();
                m.setId(rs.getInt("id"));
                m.setName(rs.getString("name"));
                mentors.add(m);
            }
        } catch (SQLException e) {

        }
        return mentors;
    }

    public Mentor getByAccountId(int id) {
        try {
            String sql = "SELECT * FROM Account a JOIN Mentor m ON a.accid = m.accid WHERE a.accid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUsername(rs.getString("username"));
                account.setEmail(rs.getString("email"));
                Mentor mentor = new Mentor();
                mentor.setId(rs.getInt("id"));
                mentor.setName(rs.getString("name"));
                mentor.setGender(rs.getBoolean("gender"));
                mentor.setPhone(rs.getString("phone"));
                mentor.setAddress(rs.getString("address"));
                mentor.setDateOfBirth(rs.getDate("dateofbirth"));
                mentor.setAccount(account);
                return mentor;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public Mentor getByMentorid(int id) {
        try {
            String sql = "SELECT * FROM Mentor m WHERE m.id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Mentor mentor = new Mentor();
                mentor.setId(rs.getInt("id"));
                mentor.setName(rs.getString("name"));
                return mentor;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public Mentor viewMentorProfile(int mentorId) {
        Mentor mentor = null;
        String sql = """
                SELECT 
                                    m.id as mentor_id, m.[name] as mentor_name, m.gender, m.phone, m.[address], m.dateofbirth, 
                                    m.ava as mentor_avatar, m.job, m.intro, m.achivement, m.experience, m.[certificate], 
                                    a.email, a.username as accountUsername, a.accid
                                FROM 
                                    Mentor m
                                JOIN 
                                    Account a ON a.accid = m.accid
                                WHERE 
                                    m.id = ?""";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mentorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetch data from ResultSet
                    String mentorName = resultSet.getString("mentor_name");
                    boolean gender = resultSet.getBoolean("gender");
                    String phone = resultSet.getString("phone");
                    String address = resultSet.getString("address");
                    Date dateOfBirth = resultSet.getDate("dateofbirth");
                    String mentorAvatar = resultSet.getString("mentor_avatar");
                    String job = resultSet.getString("job");
                    String intro = resultSet.getString("intro");
                    String achievement = resultSet.getString("achivement");
                    String experience = resultSet.getString("experience");
                    String certificate = resultSet.getString("certificate");
                    String email = resultSet.getString("email");
                    String accountUsername = resultSet.getString("accountUsername");

                    // Create Account object
                    Account account = new Account();
                    account.setId(resultSet.getInt("accid"));
                    account.setEmail(email);
                    account.setUsername(accountUsername);

                    // Create Mentor object
                    mentor = new Mentor();
                    mentor.setId(mentorId);
                    mentor.setName(mentorName);
                    mentor.setGender(gender);
                    mentor.setPhone(phone);
                    mentor.setAddress(address);
                    mentor.setDateOfBirth(dateOfBirth);
                    mentor.setAva(mentorAvatar);
                    mentor.setJob(job);
                    mentor.setIntro(intro);
                    mentor.setAchievement(achievement);
                    mentor.setExperience(experience);
                    mentor.setCertificate(certificate);
                    mentor.setAccount(account);

                }
            }
        } catch (SQLException e) {
            System.err.println("Error viewing mentor profile: " + e.getMessage());
        }

        return mentor;
    }

    public ArrayList<Mentor> getInfoMentor() {
        ArrayList<Mentor> mentors = new ArrayList<>();
        try {
            String sql = """
             SELECT m.id, m.name, m.phone 
                        FROM Mentor m 
                        JOIN CV c ON m.id = c.menid
                        """;
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
        String getCvIdSql = "SELECT c.id FROM Mentor m JOIN CV c ON m.id = c.menid WHERE m.id = ?";
        String updateCvStatusSql = "UPDATE CV SET [status] = 1 WHERE [id] = ?";
        try {
            // Lấy trạng thái hiện tại từ Mentor
            PreparedStatement getStatusPs = connection.prepareStatement(getStatusSql);
            getStatusPs.setInt(1, mentorId);
            ResultSet rs = getStatusPs.executeQuery();
            if (rs.next()) {
                boolean currentStatus = rs.getBoolean("status");
                boolean newStatus = !currentStatus;

                // Cập nhật trạng thái mới trong Mentor
                PreparedStatement updateStatusPs = connection.prepareStatement(updateStatusSql);
                updateStatusPs.setBoolean(1, newStatus);
                updateStatusPs.setInt(2, mentorId);
                updateStatusPs.executeUpdate();

                // Lấy cvid từ Mentor
                PreparedStatement getCvIdPs = connection.prepareStatement(getCvIdSql);
                getCvIdPs.setInt(1, mentorId);
                ResultSet cvIdRs = getCvIdPs.executeQuery();
                if (cvIdRs.next()) {
                    int cvid = cvIdRs.getInt("id");

//                    PreparedStatement updateCvStatusPs = connection.prepareStatement(updateCvStatusSql);
//                    updateCvStatusPs.setBoolean(1, newStatus);
//                    updateCvStatusPs.setInt(2, cvid);
//                    updateCvStatusPs.executeUpdate();
                    // Cập nhật trạng thái của CV trong Mentor_CV
                    PreparedStatement updateCvStatusPs = connection.prepareStatement(updateCvStatusSql);
                    updateCvStatusPs.setInt(1, cvid);
                    updateCvStatusPs.executeUpdate();
                }
            }
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

    public void rejectCV(int mentorId) {
        String getCvIdSql = "SELECT c.id FROM Mentor m JOIN CV c ON m.id = c.menid WHERE m.id = ?";
        String deleteCV = "DELETE FROM [dbo].[CV] WHERE [id] = ?";
        try {
            // Lấy cvid từ Mentor
            PreparedStatement getCvIdPs = connection.prepareStatement(getCvIdSql);
            getCvIdPs.setInt(1, mentorId);
            ResultSet rs = getCvIdPs.executeQuery();
            if (rs.next()) {
                int cvid = rs.getInt("id");

                // Cập nhật trạng thái của CV trong Mentor_CV
                PreparedStatement deleteCVSql = connection.prepareStatement(deleteCV);
                deleteCVSql.setInt(1, cvid);
                deleteCVSql.executeUpdate();
            }
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
