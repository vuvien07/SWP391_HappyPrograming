<%-- 
    Document   : searchcv
    Created on : Jun 5, 2024, 8:58:39 PM
    Author     : Admin
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" import="java.util.*, model.Mentor" %>
<html>
<head>
    <title>Search Mentors</title>
</head>
<body>
    <h1>Search Mentors</h1>
    <form action="cv" method="get">
        <input type="hidden" name="action" value="search"/>
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required/>
        <button type="submit">Search</button>
    </form>
    <c:if test="${mentors != null}">
        <h2>Search Results</h2>
        <ul>
            <c:forEach items="${mentors}" var="mentor">
                <li>
                    <a href="cv?action=view&id=${mentor.id}">${mentor.name}</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>
</body>
</html>

