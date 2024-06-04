<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mentor Details</title>
</head>
<body>
    <h1>Mentor Details</h1>
    <c:if test="${not empty mentor}">
        <p><strong>Account ID:</strong> ${mentor.accid}</p>
        <p><strong>Username:</strong> ${mentor.name}</p>
        <p><strong>Job:</strong> ${mentor.job}</p>
        <p><strong>Introduction:</strong> ${mentor.intro}</p>
        <p><strong>Achievements:</strong> ${mentor.achievement}</p>
        <p><strong>Experience:</strong> ${mentor.experience}</p>
        <p><strong>Certificates:</strong> ${mentor.certificate}</p>
        <p><strong>Status:</strong> ${mentor.status}</p>
        <p><strong>Avatar:</strong> <img src="${mentor.ava}" alt="Avatar" /></p>
        <p><strong>Email:</strong> ${mentor.email}</p>
        <p><strong>Name:</strong> ${mentor.name}</p>
        <p><strong>Gender:</strong> ${mentor.gender ? "Male" : "Female"}</p>
        <p><strong>Phone:</strong> ${mentor.phone}</p>
        <p><strong>Address:</strong> ${mentor.address}</p>
        <p><strong>Date of Birth:</strong> ${mentor.dateOfBirth}</p>

        <h2>Skills</h2>
        <c:forEach var="skill" items="${skills}">
            <div>
                <p><strong>Skill Name:</strong> ${skill.skillName}</p>
                <p><strong>Status:</strong> ${skill.status}</p>
                <p><strong>Description:</strong> ${skill.description}</p>
                <p><strong>Avatar:</strong> <img src="${skill.avatar}" alt="Skill Avatar" /></p>
            </div>
        </c:forEach>
    </c:if>
</body>
</html>