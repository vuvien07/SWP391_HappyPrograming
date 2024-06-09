<%-- 
    Document   : profile
    Created on : Jun 7, 2024, 11:36:22 PM
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
        <jsp:include page="../component/header.jsp"/>
        <p style="color: red; font-weight: lighter; font-size: 20px">${requestScope.err}</p>
        <div class="container-xl" style="margin-top: 10%">
            <form action="profile" class="signup" method="post" enctype="multipart/form-data">
                <p style="color: green; font-weight: lighter; font-size: 20px">${requestScope.success}</p>
                <div class="row">
                    <div class="col-xl-4">
                        <div class="card mb-4 mb-xl-0">
                            <div class="card-header text-center">Profile Avatar</div>
                            <div class="card-body text-center box_info">
                                <div class="avatar">
                                    <input type="file" id="fileUpload" name="avatar" accept=".jpg, .jpeg, .png" style="display: none;" onchange="previewImage(event)">
                                    <img id="preview" class="preview-img" style="margin-left: 20%;border-radius: 50%; width: 200px; height: 200px; border: 1px solid black; margin-bottom: 10px"  src="${pageContext.request.contextPath}/resources/uploads/${sessionScope.user.ava}">
                                    <button type="button" class="img-upload" onclick="document.getElementById('fileUpload').click()">Click here to upload</button>
                                    <br>
                                    <input type="hidden" name="image-initiate" value="${sessionScope.user.ava}">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-8">
                        <div class="card-mb-4 mb-xl-0">
                            <div class="card-header">YOUR PROFILE</div>
                            <div class="card-body box_info">

                                <div class="row">
                                    <div class="col-md-6">  
                                        <label class="mb-1" for="inputUsername">Username</label>
                                        <input class="form-control" id="inputUsername" name="username" type="text" placeholder="Enter your username" value="${sessionScope.mentor.account.username}">
                                    </div>
                                    <div class="col-md-6">  
                                        <label class="mb-1" for="inputUsername">Date of birth</label><br>
                                        <input type="date" name="dob" value="${sessionScope.mentor.dateOfBirth}">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <label class="mb-1" for="inputFirstName">Full name</label>
                                        <input class="form-control acceptEdit"  name="name" id="inputFirstName" type="text" placeholder="Full Name" value="${sessionScope.mentor.name}">
                                    </div>
                                    <div class="col-md-6">
                                        <label class="mb-1" for="inputFirstName">Email</label>
                                        <input class="form-control acceptEdit" readonly  name="email" id="inputFirstName" type="text" placeholder="Email" value="${sessionScope.mentor.account.email}">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <label>Gender:
                                        </label><br>
                                        <select name="gender" style="width: 100%">
                                            <option value="Male" <c:if test="${sessionScope.mentor.gender == true}">selected</c:if>>Male</option>
                                            <option value="Female" <c:if test="${sessionScope.mentor.gender == false}">selected</c:if>>Female</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="mb-3">
                                        <label>Address
                                        </label><br>
                                        <input class="form-control acceptEdit" name="add" id="inputFirstName" type="text" placeholder="Address" value="${sessionScope.mentor.address}">
                                </div>

                                <div class="row justify-content-between">
                                    <div class="mb-3">
                                        <label><a href="mentor_cv" style="text-decoration: none">View CV</a></label>
                                    </div>
                                    <div class="mb-3">
                                        <div class="btn-layer"></div>
                                        <input type="submit" class="btn-primary" style="border-style: none; padding: 5px 10px; border-radius: 10px" value="Update profile">
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>


        <script>
            $(document).ready(function () {
                // Ẩn phần tử container trước
                $(".wrapper").hide();
                // Áp dụng hiệu ứng kết hợp fadeIn và slideDown trong 2000ms (2 giây)
                $(".wrapper").slideDown(500).fadeIn({duration: 250, queue: false});
            });
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
