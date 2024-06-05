<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Mentor CV</title>
</head>
<body>
  <h1>Mentor CV</h1>
  <p>Name: ${mentor.name}</p>
  <p>Gender: ${mentor.gender ? 'Male' : 'Female'}</p>
  <p>Phone: ${mentor.phone}</p>
  <p>Address: ${mentor.address}</p>
  <p>Date of Birth: ${mentor.dateOfBirth}</p>
  <p>Avatar: ${mentor.ava}</p>
  <p>Job: ${mentor.job}</p>
  <p>Introduction: ${mentor.intro}</p>
  <p>Achievement: ${mentor.achievement}</p>
  <p>Experience: ${mentor.experience}</p>
  <p>Certificate: ${mentor.certificate}</p>
  <a href="cv.jsp?action=update">Update CV</a>
  <a href="cv.jsp?action=insert">Insert New Mentor (if applicable)</a>
</body>
</html>