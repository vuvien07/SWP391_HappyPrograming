<%-- 
    Document   : updatecv
    Created on : Jun 4, 2024, 11:28:18 AM
    Author     : Admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Mentor CV</title>
</head>
<body>
    <h1>Update Mentor CV</h1>
    <form action="updatecv" method="post">
        <input type="hidden" name="mentorId" value="${param.id}" />

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${mentor.name}" required><br>

        <label for="gender">Gender:</label>
        <input type="radio" id="male" name="gender" value="true" ${mentor.gender ? 'checked' : ''} required> Male
        <input type="radio" id="female" name="gender" value="false" ${!mentor.gender ? 'checked' : ''} required> Female<br>

        <label for="phone">Phone:</label>
        <input type="text" id="phone" name="phone" value="${mentor.phone}" required><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" value="${mentor.address}" required><br>

        <label for="dateOfBirth">Date of Birth:</label>
        <input type="date" id="dateOfBirth" name="dateOfBirth" value="${mentor.dateOfBirth}" required><br>

        <label for="ava">Avatar URL:</label>
        <input type="text" id="ava" name="ava" value="${mentor.ava}"><br>

        <label for="job">Job:</label>
        <input type="text" id="job" name="job" value="${mentor.job}" required><br>

        <label for="intro">Introduction:</label>
        <textarea id="intro" name="intro" required>${mentor.intro}</textarea><br>

        <label for="achievement">Achievement:</label>
        <textarea id="achievement" name="achievement" required>${mentor.achievement}</textarea><br>

        <label for="experience">Experience:</label>
        <textarea id="experience" name="experience" required>${mentor.experience}</textarea><br>

        <label for="certificate">Certificate:</label>
        <textarea id="certificate" name="certificate" required>${mentor.certificate}</textarea><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${mentor.account.email}" required><br>

        <h2>Skills</h2>
        <div id="skillsContainer">
            <c:forEach var="mentorSkill" items="${mentor.mentorSkills}">
                <div class="skill">
                    <label for="skill${mentorSkill.skillId}">Skill:</label>
                    <input type="text" id="skill${mentorSkill.skillId}" name="skills" value="${mentorSkill.skillName}" required>
                    <button type="button" onclick="removeSkill(this)">Remove</button>
                </div>
            </c:forEach>
        </div>
        <button type="button" id="addSkillButton" onclick="addSkill()">Add Skill</button><br>

        <input type="submit" value="Update Mentor">
    </form>

    <script>
    let skillCount = document.querySelectorAll('.skill').length;

    function addSkill() {
        if (skillCount < 3) {
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

            if (skillCount === 3) {
                document.getElementById('addSkillButton').style.display = 'none';
            }
        }
    }

    function removeSkill(button) {
        const skill = button.parentNode;
        skill.remove();
        skillCount--;
        if (skillCount < 3) {
            document.getElementById('addSkillButton').style.display = 'block';
        }
    }
    </script>
</body>
</html>
