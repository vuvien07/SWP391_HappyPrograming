<%-- 
    Document   : profile
    Created on : May 24, 2024, 11:03:07 AM
    Author     : Admin
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="font/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style_1.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signup.css"/>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
        <style>
            .profile-img {
                width: 20%;
                height: 40%;
                border-radius: 50%;
            }
            .preview-img {
                display: block;
                margin-top: 10px;
                width: 300px;
                height: 200px;
                object-fit: cover;
            }

            .img-upload{
                background-color: green;
                padding: 5px 10px;
                border-style: none;
                border-radius: 10px;
            }

        </style>
    </head>
    <body>
        <div class="wrapper">
            <div class="title-text">
                <div class="title login">
                    <a href="home.jsp"><img src="${pageContext.request.contextPath}/resources/images/login.jpg" style="width: 20%; height: 40%; border-radius: 50%"><br></a>
                    <p style="color: black">Profile</p><br>
                    <p style="color: red; font-weight: lighter; font-size: 20px">${requestScope.err}</p>
                    <p style="color: green; font-weight: lighter; font-size: 20px">${requestScope.success}</p>
                </div>
                <!--            <div class="title signup">
                                Signup Form
                            </div>-->
            </div><br>
            <div class="form-container">
                <div class="form-inner">
                    <form action="profile" class="signup" method="post" enctype="multipart/form-data">
                        <input type="file" id="fileUpload" name="avatar" accept=".jpg, .jpeg, .png" style="display: none;" onchange="previewImage(event)">
                        <button type="button" class="img-upload" onclick="document.getElementById('fileUpload').click()">Click here to upload</button>
                        <br>
                        <img id="preview" class="preview-img" style="margin-left: 24%;border-radius: 12px" src="${pageContext.request.contextPath}/resources/uploads/${sessionScope.user.ava}">
                        <input type="hidden" name="image-initiate" value="${sessionScope.user.ava}">
                        <div style="display: flex; margin-bottom: 10%;">
                            <div style="margin-right: 10px;width: 49%">
                                <div class="field">
                                    <input type="text" name="name" pattern="^[A-Z][a-z]* (?:[A-Z][a-z]* ){1}[A-Z][a-z]*$" title="Name must be three letters and the first char of each word must be capitalize" value="${sessionScope.user.getName()}" placeholder="Full Name">
                                </div>
                                <div class="field">
                                    <input type="text" name="username" value="${sessionScope.user.account.username}" placeholder="Username" required>
                                </div>
                                <div style="margin-top:8%">
                                    <label>Gender:
                                    </label><br>
                                    <select name="gender" style="width: 100%">
                                        <option value="Male" <c:if test="${sessionScope.user.gender == true}">selected</c:if>>Male</option>
                                        <option value="Female" <c:if test="${sessionScope.user.gender == false}">selected</c:if>>Female</option>
                                        </select>

                                    </div>
                                </div>

                                <div style="margin-left: 10px; width: 49%">
                                    <!--                                    <div class="field">
                                                                            <input type="password" name="re-pass" placeholder="Confirm password" pattern="^\S{8}$" 
                                                                                   title="Password must be 8 characters long and cannot contain spaces" required>
                                                                        </div>-->
                                    <div class="field">
                                        <input type="text" name="email" value="${sessionScope.user.account.email}" placeholder="Email" readonly>
                                </div>

                                <div class="field">
                                    <label style="min-width: 100px;">Date of birth:</label>
                                    <input type="date" name="dob" value="${sessionScope.user.dateOfBirth}">
                                </div>
                            </div>
                        </div>
                        <div class="field">
                            <input type="text" name="add" style="width: 100%" value="${sessionScope.user.address}" placeholder="Address">
                        </div>
                        <div class="field btn">
                            <label style="margin-left: 39%"><a href="changepass" style="text-decoration: none">Change password</a></label>
                        </div>
                        <div class="field btn">
                            <div class="btn-layer"></div>
                            <input type="submit" value="Update profile">
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
                $(".wrapper").slideDown(500).fadeIn({duration: 250, queue: false});
            });
//            function submitForm() {
//                let check = true;
//                var name = $('[name="name"]').val();
//                var username = $('[name="username"]').val();
//                var pass = $('[name="pass"]').val();
//                var repass = $('[name="re-pass"]').val();
//                var phone = $('[name="phone"]').val();
//                var add = $('[name="add"]').val();
//                var gender = $('[name="name"]').val();
//                var dob = $('[name="dob"]').val();
//                var email1 = $('[name="email"]').val();
//                if (!name.match("^[A-Z][a-z]* (?:[A-Z][a-z]* ){1}[A-Z][a-z]*$")) {
//                    window.alert("Full name must be in 'First Last' format with each word capitalized.");
//                    check = false;
//                }
//                if (username.trim() === "") {
//                    window.alert("Username cannot be empty.");
//                    check = false;
//                }
//                if (pass.trim() === "") {
//                    window.alert("Password cannot be empty.");
//                    check = false;
//                } else if (pass !== repass) {
//                    window.alert("Passwords do not match.");
//                    check = false;
//                }
//                if (!phone.match("^[0-9]{10}$")) {
//                    window.alert("Phone number must be 10 digits.");
//                    check = false;
//                }
//                if (add.trim() === "") {
//                    window.alert("Address cannot be empty.");
//                    check = false;
//                }
//                if (gender.trim() === "") {
//                    window.alert("Gender cannot be empty.");
//                    check = false;
//                }
//                if (!dob.match("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
//                    window.alert("Date of Birth must be in YYYY-MM-DD format.");
//                    check = false;
//                }
//                return check;
//            }
            function previewImage(event) {
                var reader = new FileReader();
                reader.onload = function () {
                    var output = document.getElementById('preview');
                    output.src = reader.result;
                };
                reader.readAsDataURL(event.target.files[0]);
            }
        </script>
    </body>
</html>