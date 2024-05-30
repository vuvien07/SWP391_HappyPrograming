<%-- 
    Document   : login
    Created on : May 13, 2024, 10:55:40 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login.css"/>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="wrapper" style="width: 400px">
            <div class="title-text">
                <div class="title login">
                    <a href="home.jsp"><img src="${pageContext.request.contextPath}/resources/images/login.jpg" style="width: 20%; border-radius: 50%"><br></a>
                    Login Form
                    <p style="color: green; font-weight: lighter; font-size: 15px">${requestScope.success}</p>
                    <p style="color: red; font-weight: lighter; font-size: 15px">${requestScope.err}</p>
                    <p style="color: green; font-weight: lighter; font-size: 15px">${requestScope.reset_success}</p>
                </div>
            </div>
            <div class="form-container">
                <p style="color: red">${requestScope.error}</p>
                <div class="form-inner">
                    <form action="login" class="login" method="post">
                        <div class="field">
                            <input type="text" name="username" value="${requestScope.user}" pattern="^\S{1,}$" placeholder="Username" required>
                        </div>
                        <div class="field">
                            <input type="password" name="password" pattern="^\S{1,}$" value="${requestScope.pass}" placeholder="Password" required>
                        </div>
                        <div class="pass-link">
                            <input type="checkbox" name="rememberMe" value="remember" <c:if test="${not empty requestScope.rememberMe}">checked</c:if>> Remember me
                            <a href="forgot" style="margin-left: 15%">Forgot password?</a>
                        </div>
                        <div class="field btn">
                            <div class="btn-layer"></div>
                            <input type="submit" value="Login">
                        </div>
                        <div class="signup-link">Not a member? <a href="register">Signup now</a></div>
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
