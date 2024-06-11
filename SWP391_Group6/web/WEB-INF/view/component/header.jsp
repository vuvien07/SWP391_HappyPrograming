<%-- 
    Document   : header
    Created on : Jun 7, 2024, 11:19:47 AM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        </style>
    </head>

    <body>
        <nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
            <div class="container">
                <a class="navbar-brand" href="https://www.google.com/"><span>Happy</span>Programing</a>
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
                        <c:if test="${sessionScope.account.roleid == 3}">
                            <li class="nav-item"><a href="admin" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Dashboard</a></li>
                            </c:if>
                             <c:if test="${sessionScope.account.roleid == 4}">
                            <li class="nav-item"><a href="manage_cv" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Dashboard</a></li>
                            </c:if>
                    </ul>
                    <c:if test="${sessionScope.account != null}">
                        <img src="assets/images/about-1.jpg" class="user_img" style="width: 60px; height: 60px; border-radius: 50%" alt="alt"/>
                        <i class="bi bi-chevron-compact-down icon"></i>
                        <div style="margin-top: 27%; margin-left: 45%; position: absolute">
                            <ul class="io" id="dynamicMenu" style="display: none; margin-top:10px">
                                <li style="padding: 0"><a href="" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4); margin-top: 80px"><h2><i class="bi bi-person-fill"></i>${sessionScope.account.username}</h2></a></li>
                                                <c:if test="${sessionScope.account.roleid == 2}">
                                    <li style="padding: 0"><a href="profile" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-person-fill"></i>View profile</a></li>
                                    <li style="padding: 0"><a href="list_request" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-card-list"></i> List request</a></li>
                                    </c:if>
                                    <c:if test="${sessionScope.account.roleid == 1}">
                                    <li style="padding: 0"><a href="mentor_profile" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-person-fill"></i>View profile</a></li>
                                    <li style="padding: 0"><a href="schedule" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-person-fill"></i>Create schedule</a></li>
                                    <li style="padding: 0"><a href="view_schedule" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-card-list"></i>View schedule</a></li>
                                    </c:if>
                                <li style="padding: 0"><a href="logout" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-card-list"></i> Statistic request</a></li> 
                                <li style="padding: 0"><a href="logout" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-box-arrow-left"></i> Log out</a></li> 
                            </ul>
                        </div>
                    </c:if>
                </div>

            </div>
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
            </script>
        </nav>
        <!-- END nav -->
