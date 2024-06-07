<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" %>
<%@ page import="model.Mentor, model.Skill, model.Account,model.Mentor_Skill" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mentor Profile</title>
</head>
<body>
    <h1>Mentor Profile</h1>
    <c:if test="${mentor != null}">
        <p>ID: ${mentor.id}</p>
        <p>Name: ${mentor.name}</p>
        <p>Gender: ${mentor.gender ? 'Male' : 'Female'}</p>
        <p>Phone: ${mentor.phone}</p>
        <p>Address: ${mentor.address}</p>
        <p>Date of Birth: ${mentor.dateOfBirth}</p>
        <p>Avatar: <img src="${mentor.ava}" alt="avatar"/></p>
        <p>Job: ${mentor.job}</p>
        <p>Introduction: ${mentor.intro}</p>
        <p>Achievements: ${mentor.achievement}</p>
        <p>Experience: ${mentor.experience}</p>
        <p>Certificate: ${mentor.certificate}</p>
        <p>Email: ${mentor.account.email}</p>
        <p>Skills: 
            <c:forEach items="${mentor.mentorSkills}" var="skill">
                ${skill.skillname}
            </c:forEach>
        </p>
        <a href="cv?action=update&id=${mentor.id}">Update Profile</a>
    </c:if>
   <!-- Nút chỉnh sửa CV -->
    <form action="updatecv.jsp" method="get">
        <input type="hidden" name="mentorId" value="<%= request.getParameter("mentorId") %>" />
        <button type="submit">Chỉnh sửa CV</button>
    </form>
</body>
</html>