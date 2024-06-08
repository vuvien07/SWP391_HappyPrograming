/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Mentor;
import model.Mentor_Skill;
import model.Skill;

/**
 *
 * @author Admin
 */
public abstract class MentorDAO extends DBContext<Mentor> {

    // Method to find mentors by name
    public List<Mentor> findByNameMentor(String name) {
        List<Mentor> listFound = new ArrayList<>();
        String sql = """
                SELECT 
                    m.id as mentor_id, m.name as mentor_name, m.gender, m.phone, m.address, m.dateofbirth, 
                    m.ava as mentor_avatar, m.job, m.intro, m.achivement, m.experience, m.certificate, 
                    a.email, a.username as accountUsername, s.skillname
                FROM 
                    Mentor m
                JOIN 
                    Account a ON m.accid = a.accid
                JOIN 
                    Mentor_Skill ms ON m.id = ms.mentorid
                JOIN 
                    Skill s ON ms.skillid = s.id
                WHERE 
                    m.name = ?""";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Fetch data from ResultSet
                    int mentorId = resultSet.getInt("mentor_id");
                    String mentorName = resultSet.getString("mentor_name").trim();
                    boolean gender = resultSet.getBoolean("gender");
                    String phone = resultSet.getString("phone").trim();
                    String address = resultSet.getString("address").trim();
                    Date dateOfBirth = resultSet.getDate("dateofbirth");
                    String mentorAvatar = resultSet.getString("mentor_avatar").trim();
                    String job = resultSet.getString("job").trim();
                    String intro = resultSet.getString("intro").trim();
                    String achievement = resultSet.getString("achivement").trim();
                    String experience = resultSet.getString("experience").trim();
                    String certificate = resultSet.getString("certificate").trim();
                    String email = resultSet.getString("email").trim();
                    String accountUsername = resultSet.getString("accountUsername").trim();
                    String skillName = resultSet.getString("skillname").trim();

                    // Create Account object
                    Account account = new Account();
                    account.setId(resultSet.getInt("accid"));
                    account.setEmail(email);
                    account.setUsername(accountUsername);

                    // Create Skill object
                    Skill skill = new Skill();
                    skill.setSkillname(skillName);

                    // Create Mentor object
                    Mentor mentor = new Mentor();
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
                    mentor.setMentorSkills(new ArrayList<>());  // Assuming a list of skills

                    listFound.add(mentor);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding mentor by name: " + e.getMessage());
        }

        return listFound;
    }

    // Method to update mentor information and skills
    public boolean updateMentorSkillAndEmail(Mentor mentor) {
        try {
            connection.setAutoCommit(false); // Start transaction

            String sql = """
                    UPDATE Mentor
                    SET name = ?, gender = ?, phone = ?, address = ?, dateofbirth = ?, ava = ?, job = ?, intro = ?, achivement = ?, experience = ?, certificate = ?
                    WHERE id = ?
                """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, mentor.getName().trim());
                statement.setBoolean(2, mentor.isGender());
                statement.setString(3, mentor.getPhone().trim());
                statement.setString(4, mentor.getAddress().trim());
                statement.setDate(5, new java.sql.Date(mentor.getDateOfBirth().getTime()));
                statement.setString(6, mentor.getAva().trim());
                statement.setString(7, mentor.getJob().trim());
                statement.setString(8, mentor.getIntro().trim());
                statement.setString(9, mentor.getAchievement().trim());
                statement.setString(10, mentor.getExperience().trim());
                statement.setString(11, mentor.getCertificate().trim());
                statement.setInt(12, mentor.getId());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    updateMentorSkill(mentor.getId(), mentor.getMentorSkills());
                    updateMentorEmail(mentor.getAccount().getId(), mentor.getAccount().getEmail());
                    connection.commit();
                    return true;
                } else {
                    throw new SQLException("Mentor update failed");
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                System.err.println("Error during rollback: " + e1.getMessage());
            }
            System.err.println("Error during update: " + e.getMessage());
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                System.err.println("Error setting autocommit: " + e.getMessage());
            }
        }
    }

    // Private method to update mentor skills
    private void updateMentorSkill(int mentorId, List<Mentor_Skill> mentorSkills) {
        String deleteSkillSql = "DELETE FROM Mentor_Skill WHERE mentorid = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteSkillSql)) {
            deleteStatement.setInt(1, mentorId);
            deleteStatement.executeUpdate();

            for (Mentor_Skill mentorSkill : mentorSkills) {
                String insertSkillSql = """
                        INSERT INTO Mentor_Skill (mentorid, skillid)
                        VALUES (?, ?)
                    """;

                try (PreparedStatement statement = connection.prepareStatement(insertSkillSql)) {
                    statement.setInt(1, mentorId);
                    statement.setInt(2, mentorSkill.getSkillId());
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error updating mentor skills: " + e.getMessage());
        }
    }

    // Private method to update mentor email
    private void updateMentorEmail(int accountId, String email) {
        String updateEmailSql = """
                UPDATE Account
                SET email = ?
                WHERE accid = ?
            """;

        try (PreparedStatement statement = connection.prepareStatement(updateEmailSql)) {
            statement.setString(1, email);
            statement.setInt(2, accountId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating mentor email: " + e.getMessage());
        }
    }

    // Method to list all mentors

    // Method to find skill id by name
    public int findSkillIdByName(String skillName) {
        String sql = "SELECT id FROM Skill WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, skillName);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if skill not found
    }

    // Method to insert a new mentor
    public boolean insertMentor(Mentor mentor) throws SQLException {

            connection.setAutoCommit(false); // Start transaction

            String sql = """
                    UPDATE [dbo].[Mentor]
                       SET [name] = ?
                          ,[gender] = ?
                          ,[phone] = ?
                          ,[address] = ?
                          ,[dateofbirth] = ?
                          ,[ava] = ?
                          ,[job] = ?
                          ,[intro] = ?
                          ,[achivement] = ?
                          ,[experience] = ?
                          ,[certificate] = ?
                          ,[status] = ?
                          ,[accid] = ?
                     WHERE [id] = ?
                """;

            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, mentor.getName().trim());
                statement.setBoolean(2, mentor.isGender());
                statement.setString(3, mentor.getPhone().trim());
                statement.setString(4, mentor.getAddress().trim());
                statement.setDate(5, new java.sql.Date(mentor.getDateOfBirth().getTime()));
                statement.setString(6, mentor.getAva().trim());
                statement.setString(7, mentor.getJob().trim());
                statement.setString(8, mentor.getIntro().trim());
                statement.setString(9, mentor.getAchievement().trim());
                statement.setString(10, mentor.getExperience().trim());
                statement.setString(11, mentor.getCertificate().trim());
                statement.setInt(12, mentor.getId());

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                   ResultSet generatedKeys = statement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int mentorId = generatedKeys.getInt(1);
                            insertMentorSkill(mentorId, mentor.getMentorSkills());
                            insertMentorEmail(mentorId, mentor.getAccount().getEmail());
                        }
                    }
                    connection.commit();
                    return true;
                
            }
          

    // Private method to insert mentor skills
    private void insertMentorSkill(int mentorId, List<Mentor_Skill> mentorSkills) {
        String insertSkillSql = """
                INSERT INTO Mentor_Skill (mentorid, skillid)
                VALUES (?, ?)
            """;

        try (PreparedStatement statement = connection.prepareStatement(insertSkillSql)) {
            for (Mentor_Skill mentorSkill : mentorSkills) {
                statement.setInt(1, mentorId);
                statement.setInt(2, mentorSkill.getSkillId());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            System.err.println("Error inserting mentor skills: " + e.getMessage());
        }
    }

    // Private method to insert mentor email
    private void insertMentorEmail(int mentorId, String email) {
        String insertEmailSql = """
                INSERT INTO Account (accid, email, username, password, role, status)
                VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (PreparedStatement statement = connection.prepareStatement(insertEmailSql)) {
            statement.setInt(1, mentorId);
            statement.setString(2, email);
            statement.setString(3, mentorId + "_username"); // Generate temporary username
            statement.setString(4, mentorId + "_password"); // Generate temporary password
            statement.setString(5, "mentor");
            statement.setBoolean(6, true);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting mentor email: " + e.getMessage());
        }
    }

    // Method to view the profile of a single mentor
    public Mentor viewMentorProfile(int mentorId) {
        Mentor mentor = null;
        String sql = """
                SELECT 
                    m.id as mentor_id, m.name as mentor_name, m.gender, m.phone, m.address, m.dateofbirth, 
                    m.ava as mentor_avatar, m.job, m.intro, m.achivement, m.experience, m.certificate,m. 
                    a.email, a.username as accountUsername, s.skillname
                FROM 
                    Mentor m
                JOIN 
                    Account a ON m.accid = a.accid
                JOIN 
                    Mentor_Skill ms ON m.id = ms.mentorid
                JOIN 
                    Skill s ON ms.skillid = s.id
                WHERE 
                    m.id = ?""";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mentorId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Fetch data from ResultSet
                    String mentorName = resultSet.getString("mentor_name").trim();
                    boolean gender = resultSet.getBoolean("gender");
                    String phone = resultSet.getString("phone").trim();
                    String address = resultSet.getString("address").trim();
                    Date dateOfBirth = resultSet.getDate("dateofbirth");
                    String mentorAvatar = resultSet.getString("mentor_avatar").trim();
                    String job = resultSet.getString("job").trim();
                    String intro = resultSet.getString("intro").trim();
                    String achievement = resultSet.getString("achivement").trim();
                    String experience = resultSet.getString("experience").trim();
                    String certificate = resultSet.getString("certificate").trim();
                    String email = resultSet.getString("email").trim();
                    String accountUsername = resultSet.getString("accountUsername").trim();
                    String skillName = resultSet.getString("skillname").trim();

                    // Create Account object
                    Account account = new Account();
                    account.setId(resultSet.getInt("accid"));
                    account.setEmail(email);
                    account.setUsername(accountUsername);

                    // Create Skill object
                    Skill skill = new Skill();
                    skill.setSkillname(skillName);

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
                    mentor.setMentorSkills(new ArrayList<>());  // Assuming a list of skills

                    // Assuming the list of skills needs to be populated from the result set
                    List<Mentor_Skill> mentorSkills = new ArrayList<>();
                    do {
                        Mentor_Skill mentorSkill = new Mentor_Skill();
                        mentorSkill.setMentorId(mentorId);
                        mentorSkill.setSkillId(resultSet.getInt("skillid"));
                        mentorSkills.add(mentorSkill);
                    } while (resultSet.next());
                    
                    mentor.setMentorSkills(mentorSkills);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error viewing mentor profile: " + e.getMessage());
        }

        return mentor;
    }

    // Private method to get account ID by mentor ID
    private int getAccountIdByMentorId(int mentorId) {
        String sql = "SELECT account_id FROM Mentor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mentorId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("account_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Return -1 if account ID not found
    }

    // New method to check if the job field of a mentor is null
    public boolean isJobFieldNull(int mentorId) {
        String sql = "SELECT job FROM Mentor WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mentorId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    String job = rs.getString("job");
                    return job == null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true; // Return true if job field is null or not found
    }
}

