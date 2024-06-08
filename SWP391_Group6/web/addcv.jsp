<%-- 
  Document  : addcv
  Created on : Jun 4, 2024, 11:28:18 AM
  Author   : Admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add Mentor</title>
</head>
<body>
  <h1>Add Mentor</h1>
  <form action="/SWP391_Group6/addcv" method="post">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="gender">Gender:</label>
    <input type="radio" id="male" name="gender" value="true" required> Male
    <input type="radio" id="female" name="gender" value="false" required> Female<br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" required><br>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required><br>

    <label for="dateOfBirth">Date of Birth:</label>
    <input type="date" id="dateOfBirth" name="dateOfBirth" required><br>

    <label for="ava">Avatar URL:</label>
    <input type="text" id="ava" name="ava"><br>

    <label for="job">Job:</label>
    <input type="text" id="job" name="job" required><br>

    <label for="intro">Introduction:</label>
    <textarea id="intro" name="intro" required></textarea><br>

    <label for="achievement">Achievement:</label>
    <textarea id="achievement" name="achievement" required></textarea><br>

    <label for="experience">Experience:</label>
    <textarea id="experience" name="experience" required></textarea><br>

    <label for="certificate">Certificate:</label>
    <textarea id="certificate" name="certificate" required></textarea><br>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required><br>

    <h2>Add Skills</h2>
    <div id="skillsContainer">
      <div class="skill">
        <label for="skill1">Skill:</label>
        <input type="text" id="skill1" name="skills" required>
        <button type="button" onclick="removeSkill(this)">Remove</button>
      </div>
    </div>
    <button type="button" id="addSkillButton" onclick="addSkill()">Add Skill</button><br>

    <input type="submit" value="Add Mentor">
  </form>

  <script>
  let skillCount = 1;

  function addSkill() {
    if (skillCount < 3) { // Kiểm tra số lượng kỹ năng đã thêm
      skillCount++;
      const skillsContainer = document.getElementById('skillsContainer');
      const newSkill = document.createElement('div');
      newSkill.className = 'skill';
      newSkill.innerHTML = `
        <label for="skill${skillCount}">Skill:</label>
        <input type="text" id="skill${skillCount}" name="skills" required>
        <button type="button" onclick="removeSkill(this)">Remove</button>
      `;
      skillsContainer.appendChild(newSkill);

      if (skillCount === 3) { // Ẩn nút "Add Skill" nếu đạt được giới hạn
        document.getElementById('addSkillButton').style.display = 'none';
      }
    }
  }

  function removeSkill(button) {
    const skill = button.parentNode;
    skill.remove();

    skillCount--;

    if (skillCount < 3) { // Hiển thị lại nút "Add Skill" nếu dưới giới hạn
      document.getElementById('addSkillButton').style.display = 'block';
    }
  }
</script>

</body>
</html>