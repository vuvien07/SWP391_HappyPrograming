<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Mentor CV Details</title>
    <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
    <h1>Mentor CV Details</h1>
    <table>
        <tr>
            <th>ID</th>
            <td>${mentorCV.cvid}</td>
        </tr>
        <tr>
            <th>Name</th>
            <td>${mentorCV.name}</td>
        </tr>
        <tr>
            <th>Gender</th>
            <td>${mentorCV.gender}</td>
        </tr>
        <tr>
            <th>Phone</th>
            <td>${mentorCV.phone}</td>
        </tr>
        <tr>
            <th>Address</th>
            <td>${mentorCV.address}</td>
        </tr>
        <tr>
            <th>Date of Birth</th>
            <td>${mentorCV.dateOfBirth}</td>
        </tr>
        <tr>
            <th>Ava</th>
            <td><img src="${mentorCV.ava}" alt="Avatar" /></td>
        </tr>
        <tr>
            <th>Job</th>
            <td>${mentorCV.job}</td>
        </tr>
        <tr>
            <th>Skill</th>
            <td>${mentorCV.skill}</td>
        </tr>
        <tr>
            <th>Intro</th>
            <td>${mentorCV.intro}</td>
        </tr>
        <tr>
            <th>Achievement</th>
            <td>${mentorCV.achievement}</td>
        </tr>
        <tr>
            <th>Experience</th>
            <td>${mentorCV.experience}</td>
        </tr>
        <tr>
            <th>Certificate</th>
            <td>${mentorCV.certificate}</td>
        </tr>
    </table>
    <a href="managercv">Back to List</a>
</body>
</html>
