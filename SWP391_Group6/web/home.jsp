<%-- 
    Document   : home
    Created on : May 21, 2024, 10:30:47 AM
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
                        <c:if test="${sessionScope.account.roleid == 3}">
                            <li class="nav-item"><a href="admin" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Dashboard</a></li>
                            </c:if>
                    </ul>
                    <c:if test="${sessionScope.account != null and sessionScope.account.roleid == 2}">
                        <img src="assets/images/about-1.jpg" class="user_img" style="width: 60px; height: 60px; border-radius: 50%" alt="alt"/>
                        <i class="bi bi-chevron-compact-down icon"></i>
                        <div style="margin-top: 27%; margin-left: 45%; position: absolute">
                            <ul class="io" style="display: none; margin-top:10px">
                                <li style="padding: 0"><a href="" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4); margin-top: 80px"><h2><i class="bi bi-person-fill"></i>${sessionScope.account.username}</h2></a></li>
                                <li style="padding: 0"><a href="profile" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-person-fill"></i>View profile</a></li>
                                <li style="padding: 0"><a href="list_request?id=${sessionScope.account.id}" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-card-list"></i> List request</a></li>
                                <li style="padding: 0"><a href="logout" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-card-list"></i> Statistic request</a></li> 
                                <li style="padding: 0"><a href="logout" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-box-arrow-left"></i> Log out</a></li> 
                            </ul>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.account != null and sessionScope.account.roleid == 1}">
                        <img src="assets/images/about-1.jpg" class="user_img" style="width: 60px; height: 60px; border-radius: 50%" alt="alt"/>
                        <i class="bi bi-chevron-compact-down icon"></i>
                        <div style="margin-top: 27%; margin-left: 45%; position: absolute">
                            <ul class="io" style="display: none; margin-top:10px">
                                <li style="padding: 0"><a href="" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4); margin-top: 80px"><h2><i class="bi bi-person-fill"></i>${sessionScope.account.username}</h2></a></li>
                                <li style="padding: 0"><a href="schedule" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-person-fill"></i>Create schedule</a></li>
                                <li style="padding: 0"><a href="list_request?id=${sessionScope.account.id}" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-card-list"></i> List request</a></li>
                                <li style="padding: 0"><a href="logout" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-card-list"></i> Statistic request</a></li> 
                                <li style="padding: 0"><a href="logout" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);"><i class="bi bi-box-arrow-left"></i> Log out</a></li> 
                            </ul>
                        </div>
                    </c:if>

                </div>

            </div>
        </nav>
        <!-- END nav -->

        <section class="hero-wrap hero-wrap-2" style="background-image: url('assets/images/work-3.jpg');">
            <!--        <div class="overlay"></div>-->

            <div class="container">
                <div class="row no-gutters slider-text align-items-end justify-content-center">
                    <div class="col-md-9 ftco-animate pb-5 text-center">
                        <h1 class="mb-0 bread">Home</h1>
                    </div>
                </div>
            </div>

        </section>

        <section class="ftco-section bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 sidebar">
                        <div class="sidebar-box bg-white ftco-animate">
                            <form action="#" class="search-form">
                                <div class="form-group">
                                    <span class="icon fa fa-search"></span>
                                    <input type="text" class="form-control" placeholder="Search...">
                                </div>
                            </form>
                        </div>

                        <div class="sidebar-box bg-white p-4 ftco-animate">
                            <h3 class="heading-sidebar">Course Category</h3>
                            <form action="#" class="browse-form">
                                <label for="option-category-1"><input type="checkbox" id="option-category-1" name="vehicle"
                                                                      value="" checked> Design &amp; Illustration</label><br>
                                <label for="option-category-2"><input type="checkbox" id="option-category-2" name="vehicle"
                                                                      value=""> Web Development</label><br>
                                <label for="option-category-3"><input type="checkbox" id="option-category-3" name="vehicle"
                                                                      value=""> Programming</label><br>
                                <label for="option-category-4"><input type="checkbox" id="option-category-4" name="vehicle"
                                                                      value=""> Music &amp; Entertainment</label><br>
                                <label for="option-category-5"><input type="checkbox" id="option-category-5" name="vehicle"
                                                                      value=""> Photography</label><br>
                                <label for="option-category-6"><input type="checkbox" id="option-category-6" name="vehicle"
                                                                      value=""> Health &amp; Fitness</label><br>
                            </form>
                        </div>


                        <div class="sidebar-box bg-white p-4 ftco-animate">
                            <h3 class="heading-sidebar">Course Type</h3>
                            <form action="#" class="browse-form">
                                <label for="option-course-type-1"><input type="checkbox" id="option-course-type-1"
                                                                         name="vehicle" value="" checked> Basic</label><br>
                                <label for="option-course-type-2"><input type="checkbox" id="option-course-type-2"
                                                                         name="vehicle" value=""> Intermediate</label><br>
                                <label for="option-course-type-3"><input type="checkbox" id="option-course-type-3"
                                                                         name="vehicle" value=""> Advanced</label><br>
                            </form>
                        </div>

                        <div class="sidebar-box bg-white p-4 ftco-animate">
                            <h3 class="heading-sidebar">Software</h3>
                            <form action="#" class="browse-form">
                                <label for="option-software-1"><input type="checkbox" id="option-software-1" name="vehicle"
                                                                      value="" checked> Adobe Photoshop</label><br>
                                <label for="option-software-2"><input type="checkbox" id="option-software-2" name="vehicle"
                                                                      value=""> Adobe Illustrator</label><br>
                                <label for="option-software-3"><input type="checkbox" id="option-software-3" name="vehicle"
                                                                      value=""> Sketch</label><br>
                                <label for="option-software-4"><input type="checkbox" id="option-software-4" name="vehicle"
                                                                      value=""> WordPress</label><br>
                                <label for="option-software-5"><input type="checkbox" id="option-software-5" name="vehicle"
                                                                      value=""> HTML &amp; CSS</label><br>
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="row">
                            <c:forEach var="c" items="${sessionScope.pagedSkills}">
                                <div class="col-md-6 d-flex align-items-stretch ftco-animate">
                                    <div class="project-wrap">
                                        <a href="#" class="img" style="background-image: url(assets/uploads/skill/${c.ava});">
                                            <span class="price">${c.skillname}</span>
                                        </a>
                                        <div class="text p-4">
                                            <h3><a href="list_mentor?id=${c.id}">Design for the web with adobe photoshop</a></h3>
                                            <p class="advisor">Advisor <span>Tony Garret</span></p>
                                            <ul class="d-flex justify-content-between">
                                                <li><span class="flaticon-shower"></span>2300</li>
                                                <li class="price">$199</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!--                        <div class="row mt-5">
                                                    <div class="col">
                                                        <div class="block-27">
                                                            <ul>
                                                                <li><a href="#">&lt;</a></li>
                                                                <li class="active"><span>1</span></li>
                                                                <li><a href="#">2</a></li>
                                                                <li><a href="#">3</a></li>
                                                                <li><a href="#">4</a></li>
                                                                <li><a href="#">5</a></li>
                                                                <li><a href="#">&gt;</a></li>
                                                            </ul>
                                                        </div>
                                                    </div>
                                                </div>-->

                        <myTag:myPagination currentPage="${requestScope.page}" subject="home?" totalPages="${requestScope.num}"></myTag:myPagination>

                        </div>
                    </div>
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
        </script>

    </body>
</html>