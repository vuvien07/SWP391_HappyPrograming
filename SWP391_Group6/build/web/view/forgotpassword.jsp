<%-- 
    Document   : forgotpassword
    Created on : May 25, 2024, 11:50:00 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css"/>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="wrapper" style="width: 400px">
            <div class="title-text">
                <div class="title login">
                    <a href="home.jsp"><img src="${pageContext.request.contextPath}/assets/images/login.jpg" style="width: 20%; border-radius: 50%"><br></a>
                    <p style="font-weight: lighter; font-size: 15px">If you've forgotten your password, enter your account and email</p>
                    <p style="color: red; font-weight: lighter; font-size: 15px">${requestScope.err}</p>
                </div>
            </div>
            <div class="form-container">
                <p style="color: red">${requestScope.error}</p>
                <div class="form-inner">
                    <form action="forgot" class="login" method="post">
                        <div class="field">
                            <input type="text" name="username" placeholder="Enter your username" required>
                        </div>
                        <div class="field">
                            <input type="email" name="email" placeholder="Enter your email" required>
                        </div>
                        <div class="field btn">
                            <div class="btn-layer"></div>
                            <input type="submit" value="Submit form">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                // Ẩn phần tử container trước
                $(".wrapper").hide();
                // Áp dụng hiệu ứng kết hợp fadeIn và slideDown trong 2000ms (2 giây)
                $(".wrapper").slideDown(500).fadeIn({duration: 500, queue: true});
            });
        </script>
    </body>
</html>

