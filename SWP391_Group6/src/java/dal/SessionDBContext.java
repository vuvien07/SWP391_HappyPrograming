/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import model.Mentor;
import model.Session;
import model.Slot;
import model.User;

/**
 *
 * @author Admin
 */
public class SessionDBContext extends DBContext<Session> {

    @Override
    public ArrayList<Session> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Session entity) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Session]([date], slotid, [status], skill, menid) VALUES(?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, (Date) entity.getDate());
            ps.setInt(2, entity.getSlot().getId());
            ps.setBoolean(3, false);
            ps.setString(4, entity.getSkill());
            ps.setInt(5, entity.getMentor().getId());
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

    @Override
    public void update(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Session entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean isDuplicatedSession(Date date, int slotid, int menid) throws SQLException {
        try {
            String sql = "SELECT * FROM [Session] s WHERE s.[date] = ? AND s.slotid = ? AND s.menid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDate(1, date);
            ps.setInt(2, slotid);
            ps.setInt(3, menid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean isDuplicatedSessionByUserId(int sesid, int userid) throws SQLException {
        try {
            String sql = "SELECT * FROM [Session] s WHERE s.id = ? AND s.userid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sesid);
            ps.setInt(2, userid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public ArrayList<Session> listByMentorId(int id) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = """
                         SELECT * FROM [dbo].[Session] s JOIN Slot sl ON sl.id = s.slotid 
                         LEFT JOIN [dbo].[User]
                         u ON u.[userid] = s.[userid] LEFT JOIN [dbo].[Account] a 
                         ON a.[accid] = u.accid WHERE s.[menid] = ? AND s.[userid]
                         IS NULL AND s.[tempuserid] IS NULL
                         """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slot slot = new Slot();
                slot.setId(rs.getInt("slotid"));
                Mentor mentor = new Mentor();
                mentor.setId(rs.getInt("menid"));
                User user = new User();
                user.setName(rs.getString("Name"));
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setDate(rs.getDate("date"));
                session.setSlot(slot);
                session.setMentor(mentor);
                session.setSkill(rs.getString("skill"));
                session.setUser(user);
                sessions.add(session);
            }
        } catch (SQLException e) {
        }
        return sessions;
    }

    public ArrayList<Session> listScheduleByMentorId(int id) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = """
                       SELECT s.id, s.[date], s.skill, s.[status], sl.[from], sl.[to],
                       s.slotid, s.menid, u.[Name]  FROM [dbo].[Session] s LEFT JOIN [dbo].[User]
                      u ON u.[userid] = s.[userid] LEFT JOIN [dbo].[Account] a 
                       ON a.[accid] = u.accid JOIN [dbo].[Slot] sl ON sl.id = s.slotid WHERE s.[menid] = ?
                         """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slot slot = new Slot();
                slot.setFrom(rs.getTime("from"));
                slot.setTo(rs.getTime("to"));
                slot.setId(rs.getInt("slotid"));
                Mentor mentor = new Mentor();
                mentor.setId(rs.getInt("menid"));
                User user = new User();
                user.setName(rs.getString("Name"));
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setDate(rs.getDate("date"));
                session.setSlot(slot);
                session.setMentor(mentor);
                session.setSkill(rs.getString("skill"));
                session.setUser(user);
                session.setStatus(rs.getBoolean("status"));
                sessions.add(session);
            }
        } catch (SQLException e) {
        }
        return sessions;
    }

    public ArrayList<Session> listScheduleByUserId(int id) {
        ArrayList<Session> sessions = new ArrayList<>();
        try {
            String sql = """
                         SELECT s.id AS sesid, s.[date], s.skill, s.slotid, sl.id, sl.[from], sl.[to], s.[status]
                         FROM [dbo].[Session] s JOIN Slot sl ON sl.id = s.slotid
                         JOIN [dbo].[Mentor] m ON m.id = s.menid
                         WHERE s.[userid] = ?
                         """;
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Slot slot = new Slot();
                slot.setFrom(rs.getTime("from"));
                slot.setTo(rs.getTime("to"));
                slot.setId(rs.getInt("slotid"));
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setDate(rs.getDate("date"));
                session.setSlot(slot);
                session.setSkill(rs.getString("skill"));
                session.setStatus(rs.getBoolean("status"));
                sessions.add(session);
            }
        } catch (SQLException e) {
        }
        return sessions;
    }

    public void updateById(Session entity, int sesid) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE [Session] SET userid = ?, requestid = ?, temprequestid = ? WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(2, sesid);
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

    public boolean isSlotAvailable(int id) throws SQLException {
        String sql = "SELECT * FROM [dbo].[Session] s WHERE s.id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("tempuserid") == 0;
            }
        } catch (SQLException e) {
            throw new SQLException("An error occured");
        }
        return false;
    }

    public void updateByTempuser(int requestId, int userTempId, int sesid) {
        String sql = "UPDATE [dbo].[Session] SET [requestid] = ?, [tempuserid] = ? WHERE [id] = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, requestId);
            ps.setInt(2, userTempId);
            ps.setInt(3, sesid);
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

    public void updateSession(String skill, int userid, int requestid) {
        String sql = "UPDATE [dbo].[Session] SET [skill] = ?, [userid] = ? WHERE [requestid] = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, skill);
            ps.setInt(2, userid);
            ps.setInt(3, requestid);
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

    public void removeEmptyScheduleById(int id) {
        String sql = "DELETE FROM [dbo].[Session] WHERE [id] = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
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

    public String getTimeBySesid(int sesid) {
        StringBuilder result = new StringBuilder();
        try {
            String sql = "SELECT s.[date], sl.[from]  FROM [dbo].[Session] s JOIN [dbo].[Slot] sl ON sl.id = s.slotid\n"
                    + "WHERE s.[id] = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, sesid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result.append(rs.getDate("date")).append(" ").append(rs.getTime("from"));
            }
        } catch (Exception e) {
        }
        return result.toString();
    }

    public ArrayList<String> listByRequestIdAndUserid(int requestid, int tempuserid) {
        ArrayList<String> results = new ArrayList<>();
        try {
            String sql = "SELECT s.[date], s.slotid FROM [dbo].[Request] r JOIN [dbo].[Session] s ON r.id = s.requestid WHERE s.requestid = ?  AND s.tempuserid = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, requestid);
            ps.setInt(2, tempuserid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StringBuilder sb = new StringBuilder();
                sb.append(rs.getDate("date")).append(",Slot:").append(rs.getInt("slotid"));
                results.add(sb.toString());
            }
        } catch (Exception e) {
        }
        return results;
    }

    public void checkScheduleById(int id) {
        String sql = "UPDATE [dbo].[Session] SET [status] = 1 WHERE [id] = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
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
