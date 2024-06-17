<%-- 
    Document   : timetable
    Created on : Jun 12, 2024, 11:14:47 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, java.text.SimpleDateFormat, java.sql.Date, java.sql.Time" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="myTag" tagdir="/WEB-INF/tags/" %>
<%@ page import="model.Session" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>StudyLab - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/${pageContext.request.contextPath}/assets/css/font-awesome.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/animate.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/magnific-popup.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jquery.timepicker.css">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-xO/6v5UxY+adJ+0SQ3Kyq4lQtW9BpJr1t/5io2RcUEx5ZVvAnSV/cuotfYKw1dVMzXf8lsabB3a4QlhnBfK+0Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />

        <style>
            .search-form .form-group {
                border: 1px solid #ccc;

                border-radius: 20px;

                overflow: hidden;

            }

            .search-form .form-control {
                border: none;

                box-shadow: none;

            }

            .io{
                background-color: white;
                width: 300px;
                list-style: none;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.4);
            }

            body{
                overflow-x: hidden;
            }
            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
            }
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                margin: 0;
                padding: 0;
            }

            h1 {
                color: #333;
                text-align: center;
                margin-top: 0;
            }

            h2 {
                color: #555;
                text-align: center;
            }

            table {
                width: 95%;
                border-collapse: collapse;
                margin-top: 20px;
                margin-left: 30px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
                background-color: #fff;
            }

            th, td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }

            td {
                background-color: #fff;
            }

            select {
                padding: 5px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }

            input[type="hidden"] {
                display: none;
            }

            a {
                color: #007bff;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }

            .absent {
                color: red;
            }

            .present {
                color: green;
            }

            .slot {
                font-weight: bold;
            }
            .banner{
                display: flex;
                align-items: center;
                width: 40%;
            }

            .banner img{
                margin: 10px;
                width: 25%;
            }

            .banner h2{
                font-size: 20px;
            }

            .selectyear{
                text-align: center;
                margin: 0 auto;
            }

        </style>
    </head>

    <body>
        <jsp:include page="../component/header.jsp"/>
        <section class="ftco-section bg-light">
            <h1>Time table for ${sessionScope.account.username}</h1>
            <div class="selectyear">
                <p>Year: ${sessionScope.year}</p>
            </div>
            <form id="timetableForm" action="time_table" method="post">
                <table>
                    <tr>
                        <td style="background-color: #00BFFF">
                            Select week:
                            <select name="weeks" onchange="submitForm()">
                                <c:set var="weekField" value="1"/>
                                <c:forEach var="entry" items="${sessionScope.weeks_on_year}">
                                    <c:set var="key" value="${entry.key}"/>
                                    <c:set var="value" value="${entry.value}"/>                                                      
                                    <option value="${key} ${value}" <c:if test="${(requestScope.currentWeek eq weekField) or (requestScope.changeWeek eq weekField)}">selected</c:if> 
                                            >
                                        ${key.toString().split("-")[2]}-${key.toString().split("-")[1]} 
                                        to ${value.toString().split("-")[2]}-${value.toString().split("-")[1]}

                                    </option>
                                    <c:set var="weekField" value="${weekField + 1}"/>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center; background-color: #00BFFF">Mon<br>${sessionScope.dates[0].toString().split("-")[2]}-${sessionScope.dates[0].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Tue<br>${sessionScope.dates[1].toString().split("-")[2]}-${sessionScope.dates[1].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Wed<br>${sessionScope.dates[2].toString().split("-")[2]}-${sessionScope.dates[2].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Ths<br>${sessionScope.dates[3].toString().split("-")[2]}-${sessionScope.dates[3].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Fri<br>${sessionScope.dates[4].toString().split("-")[2]}-${sessionScope.dates[4].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Sat<br>${sessionScope.dates[5].toString().split("-")[2]}-${sessionScope.dates[5].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Sun<br>${sessionScope.dates[6].toString().split("-")[2]}-${sessionScope.dates[6].toString().split("-")[1]}</td>

                    </tr>
                    <c:forEach items="${sessionScope.slots}" var="r">
                        <tr>
                            <td>Slot ${r.id}</td>
                            <c:forEach var="d" items="${sessionScope.dates}">
                                <td>
                                    <c:forEach items="${sessionScope.sessions}" var="s">
                                        <c:if test="${(r.id eq s.slot.id) and (s.date eq d)}">
                                            Lecture:${sessionScope.account.username}<br>
                                            Skill:${s.skill}<br>
                                            <c:if test="${s.status eq true}">
                                                <p style="color: green">Checked</p>
                                            </c:if>
                                            <c:if test="${s.status eq false}">
                                                <p style="color: red">Not yet</p>
                                            </c:if>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </section>

        <jsp:include page="../component/footer.jsp"/>

        <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery-migrate-3.0.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.easing.1.3.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.waypoints.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.stellar.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.magnific-popup.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.animateNumber.min.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/bootstrap-datepicker.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/scrollax.min.js"></script>
        <script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="${pageContext.request.contextPath}/assets/js/google-map.js"></script>
        <script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
        <script>
                                window.onload = function () {
                                    var xhr = new XMLHttpRequest();
                                    xhr.open("GET", "home", true);
                                    xhr.send();
                                };
                                $(document).ready(function () {
                                    // Bắt sự kiện click trên cả trang
                                    $(document).on('click', function (event) {
                                        // Kiểm tra xem sự kiện click có xảy ra trên icon hay không
                                        if (!$(event.target).closest('.icon').length) {
                                            // Nếu không, ẩn icon
                                            $('.io').hide();
                                        }
                                    });
                                    // Bắt sự kiện click trên icon
                                    $('.icon').click(function () {
                                        $('.io').css('display', 'block');
                                    });
                                });
                                function submitForm() {
                                    document.forms["timetableForm"].submit();
                                }
        </script>
    </body>
</html>