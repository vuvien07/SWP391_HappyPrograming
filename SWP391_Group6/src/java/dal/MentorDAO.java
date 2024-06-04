/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
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

    // Existing getCvMentorByAccid method
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

    try {
        statement = connection.prepareStatement(sql);
        statement.setString(1, name);  // set the parameter
        resultSet = statement.executeQuery();

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
            mentor.setAccount(account);  // Set account object
            mentor.setMentorSkills(new ArrayList<>());  // Assuming a list of skills
            
            listFound.add(mentor);
        }
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    return listFound;
}
public boolean updateMentorSkillAndEmail(Mentor mentor) {
  try {
    connection.setAutoCommit(false); // Start transaction

    String sql = """
        UPDATE Mentor
        SET name = ?, gender = ?, phone = ?, address = ?, dateofbirth = ?, ava = ?, job = ?, intro = ?, achivement = ?, experience = ?, certificate = ?
        WHERE id = ?
    """;

    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, mentor.getName().trim());
    statement.setBoolean(2, mentor.isGender());
    statement.setString(3, mentor.getPhone().trim());
    statement.setString(4, mentor.getAddress().trim());
    statement.setDate(5, (java.sql.Date) mentor.getDateOfBirth());
    statement.setString(6, mentor.getAva().trim());
    statement.setString(7, mentor.getJob().trim());
    statement.setString(8, mentor.getIntro().trim());
    statement.setString(9, mentor.getAchievement().trim());
    statement.setString(10, mentor.getExperience().trim());
    statement.setString(11, mentor.getCertificate().trim());
    statement.setInt(12, mentor.getId()); // Set the mentor ID for the update

    int rowsAffected = statement.executeUpdate();

    if (rowsAffected > 0) { // Update skill and email if mentor updated successfully
      updateMentorSkill(mentor.getId(), mentor.getMentorSkills());
      updateMentorEmail(mentor.getId(), mentor.getAccount().getEmail());
    } else {
      throw new SQLException("Mentor update failed"); // Explicitly throw exception
    }

    connection.commit(); // Commit changes if successful
    return true;

  } catch (SQLException e) {
    try {
      connection.rollback(); // Rollback on failure
    } catch (SQLException e1) {
      System.err.println("Error during rollback: " + e1.getMessage());
    }
    System.err.println("Error during update: " + e.getMessage());
    return false;
  } finally {
    try {
      connection.setAutoCommit(true); // Reset autocommit
    } catch (SQLException e3) {
      System.err.println("Error setting autocommit: " + e3.getMessage());
    }
  }
}
private void updateMentorSkill(int mentorId, List<Mentor_Skill> mentorSkills) {
  try {
    // Delete existing mentor skills (assuming a separate DELETE statement)
    String deleteSkillSql = "..."; // Implement your DELETE logic here
    PreparedStatement deleteStatement = connection.prepareStatement(deleteSkillSql);
    deleteStatement.setInt(1, mentorId);
    deleteStatement.executeUpdate();

    // Insert updated skills
    for (Mentor_Skill mentorSkill : mentorSkills) {
      int skillId = mentorSkill.getSkillId(); // Extract the skill ID
      String insertSkillSql = """
          INSERT INTO Mentor_Skill (mentorid, skillid)
          VALUES (?, ?)
      """;

      PreparedStatement statement = connection.prepareStatement(insertSkillSql);
      statement.setInt(1, mentorId);
      statement.setInt(2, skillId);
      statement.executeUpdate();
    }
  } catch (SQLException e) {
    System.err.println("Error updating mentor skills: " + e.getMessage());
  }
}


private void updateMentorEmail(int mentorId, String email) {
  String updateEmailSql = """
      UPDATE Account
      SET email = ?
      WHERE accid = ?
  """;

  try {
    statement = connection.prepareStatement(updateEmailSql);
    statement.setString(1, email);
    statement.setInt(2, mentorId); // Get account ID from mentor ID
    statement.executeUpdate();
  } catch (SQLException e) {
    System.err.println(e.getMessage());
  } finally {
    try {
      if (statement != null) statement.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }
  }
}
public boolean insertMentor(Mentor mentor) {
  try {
    connection.setAutoCommit(false); // Start transaction

    String sql = """
        INSERT INTO Mentor (name, gender, phone, address, dateofbirth, ava, job, intro, achivement, experience, certificate)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

    PreparedStatement statement = connection.prepareStatement(sql);
    statement.setString(1, mentor.getName().trim());
    statement.setBoolean(2, mentor.isGender());
    statement.setString(3, mentor.getPhone().trim());
    statement.setString(4, mentor.getAddress().trim());
    statement.setDate(5, (java.sql.Date) mentor.getDateOfBirth());
    statement.setString(6, mentor.getAva().trim());
    statement.setString(7, mentor.getJob().trim());
    statement.setString(8, mentor.getIntro().trim());
    statement.setString(9, mentor.getAchievement().trim());
    statement.setString(10, mentor.getExperience().trim());
    statement.setString(11, mentor.getCertificate().trim());

    int rowsAffected = statement.executeUpdate();

    if (rowsAffected > 0) { // Insert mentor skills if insertion successful
      insertMentorSkill(mentor.getId(), mentor.getMentorSkills());
      insertMentorEmail(mentor.getId(), mentor.getAccount().getEmail());
    } else {
      throw new SQLException("Mentor insertion failed"); // Explicitly throw exception
    }

    connection.commit(); // Commit changes if successful
    return true;

  } catch (SQLException e) {
    try {
      connection.rollback(); // Rollback on failure
    } catch (SQLException e1) {
      System.err.println("Error during rollback: " + e1.getMessage());
    }
    System.err.println("Error during insertion: " + e.getMessage());
    return false;
  } finally {
    try {
      connection.setAutoCommit(true); // Reset autocommit
    } catch (SQLException e3) {
      System.err.println("Error setting autocommit: " + e3.getMessage());
    }
  }
}
private void insertMentorSkill(int mentorId, List<Mentor_Skill> mentorSkills) {
  try {
    for (Mentor_Skill mentorSkill : mentorSkills) {
      String insertSkillSql = """
          INSERT INTO Mentor_Skill (mentorid, skillid)
          VALUES (?, ?)
      """;

      PreparedStatement statement = connection.prepareStatement(insertSkillSql);
      statement.setInt(1, mentorId);
      statement.setInt(2, mentorSkill.getSkillId()); // Assuming Mentor_Skill has getSkillId()
      statement.executeUpdate();
    }
  } catch (SQLException e) {
    System.err.println("Error inserting mentor skills: " + e.getMessage());
  }
}

private void insertMentorEmail(int mentorId, String email) {
  try {
    String insertEmailSql = """
        INSERT INTO Account (accid, email, username, password, role, status)
        VALUES (?, ?, ?, ?, ?, ?)
    """;

    PreparedStatement statement = connection.prepareStatement(insertEmailSql);
    statement.setInt(1, mentorId); // Use mentor ID as account ID
    statement.setString(2, email);
    statement.setString(3, mentorId + "_username"); // Generate temporary username
    statement.setString(4, mentorId + "_password"); // Generate temporary password
    statement.setString(5, "mentor"); // Set role to "mentor"
    statement.setBoolean(6, true); // Set status to active

    statement.executeUpdate();
  } catch (SQLException e) {
    System.err.println("Error inserting mentor email: " + e.getMessage());
  }
}

}

   


