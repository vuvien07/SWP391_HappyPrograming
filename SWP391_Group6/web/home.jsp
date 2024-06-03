<%-- 
    Document   : home
    Created on : May 21, 2024, 10:30:47 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    
    <%@ include file="component/header.jsp" %>

    <section class="ftco-section bg-light">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 sidebar">
                    <div class="sidebar-box bg-white ftco-animate">
                        <form action="search" class="search-form">
                            <div class="form-group">
                                <span class="icon fa fa-search"></span>
                                <input type="search" class="form-control" placeholder="Search..." aria-label="Search" " name="keyword" value=${keyword}>
                                <button class="btn" style="color: white; background-color: #e3b04b; border: 1px solid #e3b04b" type="submit">Search</button>
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
                                
                                
                                
                                
                                
                                 <!-- List Product -->
                <div class="col-lg-9">
                    <div class="row">
                        <c:forEach var="c" items="${sessionScope.skills}">
                            <div class="col-md-6 d-flex align-items-stretch ftco-animate">
                                <div class="project-wrap">
                                    <a href="#" class="img" style="background-image: url(assets/images/work-5.jpg);">
                                        <span class="price">${c.skillname}</span>
                                    </a>
                                    <div class="text p-4">
                                        <h3><a href="#">Design for the web with adobe photoshop</a></h3>
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
                    <div class="row mt-5">
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
                    </div>
                </div>
            </div>
    </section>

    <%@ include file="component/footer.jsp" %>

    <!-- loader -->
    <div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px">
        <circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee" />
        <circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10"
                stroke="#F96D00" />
        </svg></div>


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

</body>

</html>