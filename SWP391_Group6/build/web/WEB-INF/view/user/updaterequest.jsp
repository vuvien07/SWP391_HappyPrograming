<%-- 
    Document   : updaterequest
    Created on : Jun 7, 2024, 8:40:35 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="myTag" tagdir="/WEB-INF/tags/" %>
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

        </style>
    </head>

    <body>
        <jsp:include page="../component/header.jsp"/>

        <section class="ftco-section bg-light">
            <form action="update_request" method="post">
                <%
                    String requestId = request.getParameter("id");
                    request.getSession().setAttribute("requestid", requestId);
                %>
                <div class="container m-auto w-50">
                    <h1 style=" font-family: Arial, Helvetica, sans-serif">Update Request</h1>
                    <label>Title</label><br>
                    <input type="text" class="form-control w-100" name="title" value="${requestScope.userRequest.title}">
                    <div class="d-flex justify-content-between w-100">
                        <div>
                            <label>Deadline date</label><br>
                            <c:set var="deadlineTime" value="${requestScope.userRequest.deadlineTime}"/>    
                            <c:choose>
                                <c:when test="${not empty deadlineTime and fn:contains(deadlineTime, ' ')}">
                                    <c:set var="formattedDate" value="${fn:split(deadlineTime, ' ')[0]}"/>
                                    <c:set var="formattedTime" value="${fn:split(deadlineTime, ' ')[1]}"/>
                                </c:when>
                            </c:choose>
                            <input type="date" class="form-control" name="deadlineDate" value="${formattedDate}">
                        </div>
                        <div>
                            <label>Deadline hour</label><br>
                            <input type="time" class="form-control" name="deadlineHour" value="${formattedTime}">
                        </div>
                    </div>
                    <div>
                        <label>Content</label><br>
                        <textarea class="form-control" placeholder="Enter your content here..." rows="5" name="content">${requestScope.userRequest.content}</textarea>
                    </div>
                    <div>
                        <label>Select skills:</label><br>
                        <c:set var="skills" value="${requestScope.userRequest.skill}"/>
                        <c:forEach items="${sessionScope.skills}" var="s">
                            ${s.skillname}&nbsp;&nbsp;<input type="checkbox" name="skills" value="${s.skillname}"
                                               <c:set var="skill" value="${s.skillname}"/>   
                                               <c:if test="${fn:contains(skills, skill)}">checked</c:if>       
                                                   ><br>

                        </c:forEach>
                    </div>
                    <div class="text-center">
                        <input type="submit" class="btn btn-primary mt-2" style="padding: 10px 20px" value="Submit">
                    </div>
                </div>
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
        </script>
    </body>
</html>
