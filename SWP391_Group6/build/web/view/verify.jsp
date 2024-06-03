<%-- 
    Document   : verify.jsp
    Created on : May 14, 2024, 10:42:12 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="font/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style_1.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/signup.css"/>
        <script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
        <style>
           
        </style>
    </head>
    <body>
        <section class="wrapper">
            <p style="color: red">${requestScope.err}</p>
            <header style="text-align: center;margin-bottom: 10px">A password confirm was sent to email ${sessionScope.email}.Please check your mail</header>
            <form action="verify" method="post">
                <input type="text" name="passconfirm" placeholder="Enter verification code" style="margin-bottom: 12px; width: 100%"><br>
                <input type="submit" class="su" value="Verify" style="padding: 5px 10px; background-color: #0073e6; border-style: none; border-radius: 10px" />
                </form>
        </section>
    </body>
</html>
