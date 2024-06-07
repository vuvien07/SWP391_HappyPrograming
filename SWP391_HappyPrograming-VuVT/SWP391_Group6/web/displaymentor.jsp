<%-- 
    Document   : displaymentor
    Created on : May 31, 2024, 5:00:02 PM
    Author     : LENOVO
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>StudyLab - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-xO/6v5UxY+adJ+0SQ3Kyq4lQtW9BpJr1t/5io2RcUEx5ZVvAnSV/cuotfYKw1dVMzXf8lsabB3a4QlhnBfK+0Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />
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
                        <li class="nav-item"><a href="index.html" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Home</a></li>
                        <li class="nav-item"><a href="about.html" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">About</a></li>
                        <li class="nav-item"><a href="contact.html" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Contact</a></li>
                        <li class="nav-item active"><a href="displaymentor" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">instructor</a></li>
                            <c:if test="${sessionScope.account == null}">
                            <li class="nav-item"><a href="login" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Login</a></li>
                            </c:if>
                            <c:if test="${sessionScope.account != null}">
                            <li class="nav-item"><a href="profile" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Profile</a></li>
                            <li class="nav-item"><a href="logout" class="nav-link " style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);">Log out</a></li>
                            </c:if>
                    </ul>



                </div>
                <a style="color: white; " href="profile.html">
                    <i  class="fas fa-user"></i> 
                </a>
            </div>
        </nav>
        <!-- END nav -->

        <section class="hero-wrap hero-wrap-2" style="background-image: url('images/bg_2.jpg');">
            <div class="overlay"></div>
            <div class="container">
                <div class="row no-gutters slider-text align-items-end justify-content-center">
                    <div class="col-md-9 ftco-animate pb-5 text-center">
                        <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home <i class="fa fa-chevron-right"></i></a></span> <span>Certified Instructor <i class="fa fa-chevron-right"></i></span></p>
                        <h1 class="mb-0 bread">Certified Instructor</h1>
                    </div>
                </div>
            </div>
        </section>
        <section class="ftco-section bg-light">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 sidebar">
                        <div class="sidebar-box bg-white ftco-animate">
                            <form action="Seach" class="search-form">
                                <div class="form-group">
                                    <button type="submit" >
                                <span class="icon fa fa-search"></span>
                            </button>
                                    <input  name="txt" type="text" class="form-control" placeholder="Search...">
                                </div>
                            </form>
                        </div>
<<<<<<< Updated upstream:SWP391_HappyPrograming-VuVT/SWP391_Group6/web/displaymentor.jsp

                        <div class="sidebar-box bg-white p-4 ftco-animate active">
                            <h3 class="heading-sidebar">Course Instructor</h3>
                            <form action="yourFilterAction" method="GET" class="browse-form">
                                 <c:forEach items="${mentorskilP}" var="o">
                                    <label for="option-category-1">
                                        <input type="checkbox" id="option-category-1" name="vehicle" value="" onclick="redirectToLink(event, '${o.mentorId}')">
                                        <a href="mentorSkill?mentorId=${o.mentorId}">${o.skillName}</a>
                                    </label>                                                    
                                </c:forEach>  

                            </form>
                        </div>

                        <div class="sidebar-box bg-white p-4 ftco-display: flex;
            align-items: center;nstructor</h3>
                            <form action="#" class="browse-form" style="            display: flex;
            flex-direction: column;">
=======
                           <div class="sidebar-box bg-white p-4 ftco-display: flex;
            align-items: center;nstructor</h3>
                            <form action="#" class="browse-form" style="            display: flex;
            flex-direction: column;">
                             <h3 class="heading-sidebar">Course Instructor</h3>
>>>>>>> Stashed changes:SWP391_Group6/web/displaymentor.jsp
                                <c:forEach items="${mentorsP}" var="o">
                                    <label for="option-instructor-1">
                                        <input type="checkbox" id="option-category-1" name="vehicle" value="" onclick="redirectToLink(event, '${o.id}')">

                                        <a href="mentorSkill?mentorId=${o.id}">${o.name}</a>
                                    </label>                                   
                                </c:forEach>

                            </form>
                        </div>

<<<<<<< Updated upstream:SWP391_HappyPrograming-VuVT/SWP391_Group6/web/displaymentor.jsp
=======
                       

>>>>>>> Stashed changes:SWP391_Group6/web/displaymentor.jsp
                        <div class="sidebar-box bg-white p-4 ftco-animate">
                            <h3 class="heading-sidebar">Course Experience</h3>
                            <form action="#" class="browse-form" style=" display: flex;
                                  flex-direction: column;">
                                <c:forEach items="${mentorsP}" var="o">
                                    <label for="option-instructor-1">
                                        <input type="checkbox" id="option-category-1" name="vehicle" value="" onclick="redirectToLink(event, '${o.id}')">

                                        <a href="mentorSkill?mentorId=${o.id}">${o.experience}</a></label> 
                                </c:forEach> 
                            </form>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="row">
                            <c:forEach items="${mentorsP}" var="o">
                                <div class="col-md-4 d-flex ftco-animate">
                                    <div class="blog-entry align-self-stretch">
                                        <img class="block-20 rounded" src="${o.ava}" alt="Mentor Avatar">
                                        <div class="text mt-3 text-center">
                                            <h3 class="heading"><a href="#">${o.name}</a></h3>
                                            <div class="meta mb-2">
                                                <div><a href="#">${o.job}</a></div>
                                                <div><a href="#">${o.intro}</a></div>
                                                <div><a href="#" class="meta-chat"><span class="fa fa-envelope"></span> ${o.email}</a></div>
                                                <div><a href="#" class="meta-chat"><span class="fa fa-phone"></span> ${o.phone}</a></div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="ftco-freeTrial ftco-section ftco-no-pb">
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-10 ftco-animate">
                        <div class="freeTrial-wrap d-md-flex align-items-center">
                            <div class="text d-block pl-md-5">
                                <h3 class="m-2">Try Our Free Trial Course</h3>
                                <p>Get started with our free trial course and experience the quality of our training.</p>
                            </div>
                            <div class="btn-join text-md-right">
                                <p><a href="#" class="btn btn-primary py-3 px-4">Join now!</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <footer class="ftco-footer ftco-bg-dark ftco-section">
            <div class="container">
                <div class="row mb-5">
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">StudyLab</h2>
                            <p>Explore a variety of online courses and enhance your skills with certified instructors.</p>
                            <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-3">
                                <li class="ftco-animate"><a href="#"><span class="fa fa-twitter"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="fa fa-facebook"></span></a></li>
                                <li class="ftco-animate"><a href="#"><span class="fa fa-instagram"></span></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4 ml-md-5">
                            <h2 class="ftco-heading-2">Explore</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">About</a></li>
                                <li><a href="#" class="py-2 d-block">Contact</a></li>
                                <li><a href="#" class="py-2 d-block">Courses</a></li>
                                <li><a href="#" class="py-2 d-block">Blog</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Quick Links</h2>
                            <ul class="list-unstyled">
                                <li><a href="#" class="py-2 d-block">Privacy Policy</a></li>
                                <li><a href="#" class="py-2 d-block">Terms &amp; Conditions</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-md">
                        <div class="ftco-footer-widget mb-4">
                            <h2 class="ftco-heading-2">Have a Questions?</h2>
                            <div class="block-23 mb-3">
                                <ul>
                                    <li><span class="icon fa fa-map"></span><span class="text">123 Main Street, Suite 101, New York, NY 10001</span></li>
                                    <li><a href="#"><span class="icon fa fa-phone"></span><span class="text">+1 234 567 890</span></a></li>
                                    <li><a href="#"><span class="icon fa fa-envelope"></span><span class="text">info@example.com</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12 text-center">

                        <p>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </p>
                    </div>
                </div>
            </div>
        </footer>

        <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#F96D00"/></svg></div>

        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-3.0.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.stellar.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/aos.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.animateNumber.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/scrollax.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
       <script>
        function redirectToLink(event, mentorId) {
            if (event.target.checked) {
                window.location.href = 'mentorSkill?mentorId=' + mentorId;
            }
        }
    </script>
    </body>
</html>
