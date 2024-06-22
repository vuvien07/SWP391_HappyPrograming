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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-xO/6v5UxY+adJ+0SQ3Kyq4lQtW9BpJr1t/5io2RcUEx5ZVvAnSV/cuotfYKw1dVMzXf8lsabB3a4QlhnBfK+0Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
    </head>
    <body>
        <div class="wrapper">
            <a href="home" class="nav-link" style="text-shadow: 0 2px 4px rgba(0,0,0,0.4);text-decoration: none">Home</a>
            <div class="title-text">
                <div class="title login">
                    <i class="bi bi-person-fill"></i><br>
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
                                    <input type="text" name="name" placeholder="Full Name" required>
                                </div>
                                <div class="name">

                                </div>
                                <div class="field">
                                    <input type="text" name="username" placeholder="Username" required>
                                </div>
                                <div class="username">

                                </div>
                                <div class="field">
                                    <input type="password" name="pass"  placeholder="Password" required>
                                </div>
                                <div class="pass">

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
                                    <input type="password" name="re-pass" placeholder="Confirm password" 
                                           required>
                                </div>
                                <div class="repass">

                                </div>
                                <div class="field">
                                    <input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="Enter a valid email address" placeholder="Email" required>
                                </div>
                                <div class="email">

                                </div>
                                <div class="field">
                                    <input type="text" name="phone" placeholder="Phone" required>
                                </div>
                                <div class="phone">

                                </div>
                                <div class="field">
                                    <label style="min-width: 100px;">Date of birth:</label>
                                    <input type="date" name="dob" required>
                                </div><br>
                                <div class="dob"></div>
                            </div>
                        </div>
                        <div class="field">
                            <input type="text" name="add" style="width: 100%" placeholder="Address" required>
                        </div>
                        <div class="address">

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
                var name = $('[name="name"]').val();
                var username = $('[name="username"]').val();
                var pass = $('[name="pass"]').val();
                var repass = $('[name="re-pass"]').val();
                var phone = $('[name="phone"]').val();
                var add = $('[name="add"]').val();
                var gender = $('[name="name"]').val();
                var dob = $('[name="dob"]').val();
                var email1 = $('[name="email"]').val();
                if (!name.match("[A-Za-z ]{1,50}")) {
                    $(".name").html('<p style="color:red">Name must be between 1 and 50 characters and contain only letters and spaces</p>');
                    check = false;
                } else {
                    $(".name").empty();
                }
                if (username.length < 6 || username.length > 20) {
                    $(".username").html('<p style="color:red">Username must be between 6 and 20 characters and cannot contain spaces</p>');
                    check = false;
                } else {
                    $(".username").empty();
                }
                if (pass.length < 8 || pass.length > 20) {
                    $(".pass").html('<p style="color:red">Password must be 8-20 characters long and cannot contain spaces</p>');
                    check = false;
                } else {
                    $(".pass").empty();
                }
                if (pass !== repass) {
                    $(".repass").html('<p style="color:red">Password does not match</p>');
                    check = false;
                } else {
                    $(".repass").empty();
                }
                if (!phone.match("^[0-9]{10}$")) {
                    $(".phone").html('<p style="color:red">Phone must be 10 digits</p>');
                    check = false;
                } else {
                    $(".phone").empty();
                }
                var currentDate = new Date();

                // Chuyển đổi ngày cụ thể thành đối tượng Date
                var targetDateObj = new Date(dob);

                // Lấy các giá trị năm, tháng và ngày của ngày hiện tại và ngày cụ thể
                var currentYear = currentDate.getFullYear();
                var currentMonth = currentDate.getMonth();
                var currentDay = currentDate.getDate();

                var targetYear = targetDateObj.getFullYear();
                var targetMonth = targetDateObj.getMonth();
                var targetDay = targetDateObj.getDate();

                // So sánh chỉ với năm, tháng và ngày
                if (targetYear > currentYear ||
                        (targetYear === currentYear && targetMonth > currentMonth) ||
                        (targetYear === currentYear && targetMonth === currentMonth && targetDay > currentDay)) {
                    $(".dob").html('<p style="color:red">Date of birth is the future date compared to current date</p>');
                    check = false;
                }else{
                    $(".dob").empty();
                }
                return check;
            }
        </script>
    </body>
</html>
