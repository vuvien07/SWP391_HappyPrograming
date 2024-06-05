<%-- 
    Document   : bookmentor
    Created on : Jun 3, 2024, 11:02:22 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="myTag" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>StudyLab - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/${pageContext.request.contextPath}/resources/css/font-awesome.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
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

            .selectslot:hover{
                background-color: #00BFFF;
            }

        </style>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="index.html"><span>Happy</span>Programing</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav"
                        aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="oi oi-menu"></span> Menu
                </button>

                <div class="collapse navbar-collapse" id="ftco-nav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item active"><a href="home" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Home</a></li>
                        <li class="nav-item"><a href="about.html" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">About</a></li>
                        <li class="nav-item"><a href="contact.html" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Contact</a></li>
                            <c:if test="${sessionScope.account == null}">
                            <li class="nav-item"><a href="login" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Login</a></li>
                            </c:if>
                            <c:if test="${sessionScope.account != null}">
                            <!--                                <li class="nav-item"><a href="profile" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Profile</a></li>
                                                            <li class="nav-item"><a href="logout" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Log out</a></li>-->
                        </c:if>
                    </ul>
                    <c:if test="${sessionScope.account != null}">
                        <img src="assets/images/about-1.jpg" class="user_img" style="width: 60px; height: 60px; border-radius: 50%" alt="alt"/>
                        <i class="bi bi-chevron-compact-down icon"></i>
                    </c:if>

                    <div style="margin-top: 27%; margin-left: 45%; position: absolute">
                        <ul class="io" style="display: none; margin-top:10px">
                            <li style="padding: 0"><a href="" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4); margin-top: 80px"><h2><i class="bi bi-person-fill"></i> ${sessionScope.account.username}</h2></a></li>
                            <li style="padding: 0"><a href="profile" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-person-fill"></i>View profile</a></li>
                            <li style="padding: 0"><a href="logout" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-card-list"></i> List request</a></li>
                            <li style="padding: 0"><a href="logout" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-card-list"></i> Statistic request</a></li> 
                            <li style="padding: 0"><a href="logout" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-box-arrow-left"></i> Log out</a></li> 
                        </ul>
                    </div>
                </div>

            </div>
        </nav>
        <section class="ftco-section bg-light">
            <p style="color: red" class="text-center">${requestScope.err}</p>
            <p style="color: green" class="text-center">${requestScope.success}</p>
            <h1>Select schedule for ${sessionScope.mentor.name}</h1>
            <div class="selectyear">
                <p>Year: ${sessionScope.year}</p>
                </div>
                <form id="bookForm" action="book_mentor" method="post">
                    <input type="hidden" name="sesid" id="sesidInput">
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
                                            <div class="selectslot" onclick="submitFormsd(${s.id})">
                                                Lecture:${sessionScope.account.username}<br>
                                                Skill:${s.skill}
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </section>

        <footer class="ftco-footer ftco-no-pt">
            <div class="container">
                <div class="row mb-5">
                    <div class="col-md pt-5">
                        <div class="ftco-footer-widget pt-md-5 mb-4">
                            <h2 class="ftco-heading-2">About</h2>
                            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia,
                                there live the blind texts.</p>
                            <ul class="ftco-footer-social list-unstyled float-md-left float-lft">
                                <li class="ftco-animate"><a href="#"><span class="fa fa-twitter"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="fa fa-facebook"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="fa fa-instagram"></span></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md pt-5">
                        <div class="ftco-footer-widget pt-md-5 mb-4 ml-md-5">
                            <h2 class="ftco-heading-2">Help Desk</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">Customer Care</a></li>
                                <li><a href="#" class="py-2 d-block">Legal Help</a></li>
                                <li><a href="#" class="py-2 d-block">Services</a></li>
                                <li><a href="#" class="py-2 d-block">Privacy and Policy</a></li>
                                <li><a href="#" class="py-2 d-block">Refund Policy</a></li>
                                <li><a href="#" class="py-2 d-block">Call Us</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md pt-5">
                        <div class="ftco-footer-widget pt-md-5 mb-4">
                            <h2 class="ftco-heading-2">Recent Courses</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">Computer Engineering</a></li>
                                <li><a href="#" class="py-2 d-block">Web Design</a></li>
                                <li><a href="#" class="py-2 d-block">Business Studies</a></li>
                                <li><a href="#" class="py-2 d-block">Civil Engineering</a></li>
                                <li><a href="#" class="py-2 d-block">Computer Technician</a></li>
                                <li><a href="#" class="py-2 d-block">Web Developer</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md pt-5">
                        <div class="ftco-footer-widget pt-md-5 mb-4">
                            <h2 class="ftco-heading-2">Have a Questions?</h2>
                            <div class="block-23 mb-3">
                                <ul>
                                    <li><span class="icon fa fa-map-marker"></span><span class="text">203 Fake St. Mountain
                                            View, San Francisco, California, USA</span></li>
                                    <li><a href="#"><span class="icon fa fa-phone"></span><span class="text">+2 392 3929
                                                210</span></a></li>
                                    <li><a href="#"><span class="icon fa fa-paper-plane"></span><span
                                                class="text">info@yourdomain.com</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 text-center">

                        <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;
                            <script>document.write(new Date().getFullYear());</script> All rights reserved | This template
                            is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com"
                                                                                              target="_blank">Colorlib</a>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </p>
                    </div>
                </div>
            </div>
        </footer>

        <!-- loader -->
        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px">
            <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee" />
            <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                    stroke="#F96D00" />
            </svg></div>


        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-3.0.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.stellar.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.animateNumber.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/scrollax.min.js"></script>
        <script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="${pageContext.request.contextPath}/resources/js/google-map.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
        <script>
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
                                function changeYear() {
                                    var year = document.getElementById('year').value;
                                    window.location.href = 'view_schedule?year=' + year;
                                }

                                function submitForm() {
                                    document.forms["bookForm"].submit();
                                }
                                function submitFormsd(sesid) {
                                    if (window.confirm("Are you sure book slot with id " + sesid)) {
                                        document.getElementById("sesidInput").value = sesid;
                                        document.getElementById("bookForm").submit();
                                    }
                                }
                                
        </script>

    </body>
</html>