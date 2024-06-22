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
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-xO/6v5UxY+adJ+0SQ3Kyq4lQtW9BpJr1t/5io2RcUEx5ZVvAnSV/cuotfYKw1dVMzXf8lsabB3a4QlhnBfK+0Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="wrapper" style="width: 400px">
            <a href="home" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);text-decoration: none">Home</a>
            <div class="title-text">
                <div class="title login">
                    <i class="bi bi-person-fill"></i><br>
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
