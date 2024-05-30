<%-- 
    Document   : resetpass
    Created on : May 25, 2024, 5:43:52 PM
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
                    Reset password form
                    <p style="color: red; font-weight: lighter; font-size: 15px">${requestScope.err}</p>
                </div>
            </div>
            <div class="form-container">
                <p style="color: red">${requestScope.error}</p>
                <div class="form-inner">
                    <form action="resetpass" class="login" method="post" onsubmit="return submitForm()">
                        <div class="field">
                            <input type="password" name="pass" pattern="^\S{8}$" 
                                           title="Password must be 8 characters long and cannot contain spaces" placeholder="Password" required>
                        </div>
                        <div class="field">
                            <input type="password" name="re-pass" pattern="^\S{8}$" 
                                           title="Password must be 8 characters long and cannot contain spaces" placeholder="Confirm password" required>
                        </div>
                        <div class="field btn">
                            <div class="btn-layer"></div>
                            <input type="submit" value="Enter">
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
            function submitForm(){
                let check = true;
                var pass = $('[name="pass"]').val();
                var repass = $('[name="re-pass"]').val();
                 if (pass !== repass) {
                    window.alert("Password and confirm password does not match!");
                    check = false;
                }
                return check;
            }
         
        </script>
    </body>
</html>