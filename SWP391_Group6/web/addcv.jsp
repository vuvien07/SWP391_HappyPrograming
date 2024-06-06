<%-- 
    Document   : addcv
    Created on : Jun 4, 2024, 11:28:18 AM
    Author     : Admin
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Insert Mentor</title>
</head>
<body>
    <h2>Insert Mentor</h2>
    <form action="${pageContext.request.contextPath}/cv" method="post">
        <input type="hidden" name="action" value="insert">
        <div>
            <label for="name">Name:</label>
            <input type="text" name="name" id="name" required>
        </div>
        <div>
            <label for="gender">Gender:</label>
            <select name="gender" id="gender">
                <option value="true">Male</option>
                <option value="false">Female</option>
            </select>
        </div>
        <div>
            <label for="phone">Phone:</label>
            <input type="text" name="phone" id="phone" required>
        </div>
        <div>
            <label for="address">Address:</label>
            <input type="text" name="address" id="address" required>
        </div>
        <div>
            <label for="dateOfBirth">Date of Birth:</label>
            <input type="date" name="dateOfBirth" id="dateOfBirth" required>
        </div>
        <div>
            <label for="ava">Avatar URL:</label>
            <input type="text" name="ava" id="ava" required>
        </div>
        <div>
            <label for="job">Job:</label>
            <input type="text" name="job" id="job" required>
        </div>
        <div>
            <label for="intro">Introduction:</label>
            <textarea name="intro" id="intro" required></textarea>
        </div>
        <div>
            <label for="achievement">Achievement:</label>
            <textarea name="achievement" id="achievement" required></textarea>
        </div>
        <div>
            <label for="experience">Experience:</label>
            <textarea name="experience" id="experience" required></textarea>
        </div>
        <div>
            <label for="certificate">Certificate:</label>
            <textarea name="certificate" id="certificate" required></textarea>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" required>
        </div>
        <input type="submit" value="Insert">
    </form>
</body>
</html>
