<%-- 
    Document   : myPagination
    Created on : Jun 1, 2024, 4:50:15 PM
    Author     : Admin
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@ attribute name="subject" required="true" type="java.lang.String" %>
<%@ attribute name="currentPage" required="true" type="java.lang.Integer" %>
<%@ attribute name="totalPages" required="true" type="java.lang.Integer" %>

<%-- any content can be specified here e.g.: --%>
<h2>${message}</h2>
<style>
    .pagination {
        margin-top: 20px;
        text-align: center;
    }

    .pagination a {
        width:40px;
        height: 40px;
        display: inline-block;
        padding: 5px 10px;
        margin: 0 10px;
        border: 1px solid #ccc;
        background-color: #f1f1f1;
        color: #333;
        text-decoration: none;
        border-radius: 50%;
    }

    .current-page{
        width:40px;
        height: 40px;
        display: inline-block;
        padding: 5px 10px;
        border: 1px solid #ccc;
        color: white;
        text-decoration: none;
        border-radius: 50%;
        background: #4986fc;
      background: #ce4be8;
      background: -moz-linear-gradient(-45deg, #ce4be8 0%, #207ce5 100%);
      background: -webkit-gradient(left top, right bottom, color-stop(0%, #ce4be8), color-stop(100%, #207ce5));
      background: -webkit-linear-gradient(-45deg, #ce4be8 0%, #207ce5 100%);
      background: -o-linear-gradient(-45deg, #ce4be8 0%, #207ce5 100%);
      background: -ms-linear-gradient(-45deg, #ce4be8 0%, #207ce5 100%);
      background: -webkit-linear-gradient(315deg, #ce4be8 0%, #207ce5 100%);
      background: -o-linear-gradient(315deg, #ce4be8 0%, #207ce5 100%);
      background: linear-gradient(135deg, #ce4be8 0%, #207ce5 100%);
      filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#ce4be8', endColorstr='#207ce5', GradientType=1 );
      color: #fff;
      border: 1px solid transparent;
    }

    .pagination a:hover {
        background-color: #ddd;
    }

</style>
<div class="pagination">
    <c:set var="currentPage" value="${currentPage}" />
    <c:set var="totalPages" value="${totalPages}" />
    <c:set var="maxPagesToShow" value="5" />

    <c:choose>
        <c:when test="${currentPage > maxPagesToShow / 2 + 1}">
            <a href="${subject}page=1">1</a> ...
        </c:when>
        <c:otherwise>
            <c:forEach var="i" begin="1" end="${currentPage - 1}" varStatus="loop">
                <a href="${subject}page=${i}">${i}</a>
            </c:forEach>
        </c:otherwise>
    </c:choose>

    <span class="current-page">${currentPage}</span>

    <c:choose>
        <c:when test="${totalPages - currentPage > maxPagesToShow / 2}">
            <c:forEach var="i" begin="${currentPage + 1}" end="${currentPage + maxPagesToShow / 2}" varStatus="loop">
                <a href="${subject}page=${i}">${i}</a>
            </c:forEach>
            ... <a href="${subject}page=${totalPages}">${totalPages}</a>
        </c:when>
        <c:otherwise>
            <c:forEach var="i" begin="${currentPage + 1}" end="${totalPages}" varStatus="loop">
                <a href="${subject}page=${i}">${i}</a>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>