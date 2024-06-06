<%-- 
    Document   : updatecv
    Created on : Jun 4, 2024, 10:52:32 AM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*, model.Mentor" %>
<html>
<head>
    <title>Update Mentor Profile</title>
</head>
<body>
    <h1>Update Mentor Profile</h1>
    <c:if test="${mentor != null}">
        <form action="cv" method="post">
            <input type="hidden" name="action" value="update"/>
            <input type="hidden" name="id" value="${mentor.id}"/>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${mentor.name}" required/>
            <br/>
            <!-- Add other fields similarly -->
            <button type="submit">Update</button>
        </form>
    </c:if>
</body>
</html>
