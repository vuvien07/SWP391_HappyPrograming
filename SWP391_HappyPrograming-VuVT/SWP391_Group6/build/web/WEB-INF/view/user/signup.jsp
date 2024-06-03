<%-- 
    Document   : signup
    Created on : May 14, 2024, 4:03:54 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="font/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style_1.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signup.css"/>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <div class="title-text">
                <div class="title login">
                    <a href="home.jsp"><img src="${pageContext.request.contextPath}/resources/images/login.jpg" style="width: 20%; border-radius: 50%"><br></a>
                    <p style="color: black">Sign up form</p>
                    <p style="color: red; font-weight: lighter; font-size: 15px" class="errorMessage">${requestScope.err}</p>
                </div>
            </div>
            <div class="form-container">
                <div class="form-inner">
                    <form action="register" class="signup" method="post" onsubmit="return submitForm()">
                        <div style="display: flex; margin-bottom: 10%;">
                            <div style="margin-right: 10px;width: 49%">
                                <div class="field">
                                    <input type="text" name="name" pattern="[A-Za-z ]{1,50}" title="Name must be between 1 and 50 characters and contain only letters and spaces" placeholder="Full Name" required>
                                </div>
                                <div class="field">
                                    <input type="text" name="username" pattern="^\S{6,20}$" title="Username must be between 6 and 20 characters and cannot contain spaces" placeholder="Username" required>
                                </div>
                                <div class="field">
                                    <input type="password" name="pass" pattern="^\S{8, 20}$" 
                                           title="Password must be 8-20 characters long and cannot contain spaces"  placeholder="Password" required>
                                </div>
                                <div style="margin-top:8%">
                                    <label>Gender:
                                    </label><br>
                                    <select name="gender" style="width: 100%">
                                        <option value="Male">Male</option>
                                        <option value="Female">Female</option>
                                    </select><br><br>

                                    <label>Role:
                                    </label><br>
                                    <select name="role" style="width: 100%">
                                        <option value="Mentee">Mentee</option>
                                        <option value="Mentor">Mentor</option>
                                    </select>

                                </div>
                            </div>

                            <div style="margin-left: 10px; width: 49%">
                                <div class="field">
                                    <input type="password" name="re-pass" placeholder="Confirm password" pattern="^\S{8, 20}$" 
                                           title="Password must be 8-20 characters long and cannot contain spaces" required>
                                </div>
                                <div class="field">
                                    <input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Enter a valid email address" placeholder="Email" required>

                                </div>
                                <div class="field">
                                    <input type="text" name="phone" pattern="0[0-9]{9}" title="Phone must be 10 digits" placeholder="Phone" required>
                                </div>
                                <div class="field">
                                    <label style="min-width: 100px;">Date of birth:</label>
                                    <input type="date" name="dob" required>
                                </div>
                            </div>
                        </div>
                        <div class="field">
                            <input type="text" name="add" style="width: 100%" placeholder="Address" required>
                        </div>
                        <div class="field btn">
                            <label style="margin-left: 20%">Already have an account?Sign in <a href="login">here</a></label>
                        </div>
                        <div class="field btn">
                            <div class="btn-layer"></div>
                            <input type="submit" value="Sign up">
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
            function submitForm() {
                let check = true;
                var username = $('[name="username"]').val();
                var pass = $('[name="pass"]').val();
                var repass = $('[name="re-pass"]').val();
                var phone = $('[name="phone"]').val();
                var add = $('[name="add"]').val();
                var gender = $('[name="name"]').val();
                var dob = $('[name="dob"]').val();
                var email1 = $('[name="email"]').val();
                if (username.trim() === "") {
                    window.alert("Username cannot be empty.");
                    check = false;
                }
                if (pass.trim() === "") {
                    window.alert("Password cannot be empty.");
                    check = false;
                } else if (pass !== repass) {
                    window.alert("Passwords do not match.");
                    check = false;
                }
                if (!phone.match("^[0-9]{10}$")) {
                    window.alert("Phone number must be 10 digits.");
                    check = false;
                }
                if (add.trim() === "") {
                    window.alert("Address cannot be empty.");
                    check = false;
                }
                if (gender.trim() === "") {
                    window.alert("Gender cannot be empty.");
                    check = false;
                }
                if (!dob.match("^[0-9]{4}-[0-9]{2}-[0-9]{2}$")) {
                    window.alert("Date of Birth must be in YYYY-MM-DD format.");
                    check = false;
                }
                return check;
            }
        </script>
    </body>
</html>
