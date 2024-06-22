<%-- 
    Document   : createschedule
    Created on : Jun 3, 2024, 9:04:52 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib prefix="myTag" tagdir="/WEB-INF/tags/" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>StudyLab - Free Bootstrap 4 Template by Colorlib</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700,800,900" rel="stylesheet">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/${pageContext.request.contextPath}/resources/css/font-awesome.min.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/animate.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.carousel.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/owl.theme.default.min.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/magnific-popup.css">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-datepicker.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/jquery.timepicker.css">


        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/flaticon.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
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

            table {
                width: 95%;
                border-collapse: collapse;
                margin-top: 20px;
                margin-left: 30px;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
                background-color: #fff;
            }

            th, td {
                border: 1px solid #ccc;
                padding: 8px;
                text-align: left;
            }

            th {
                background-color: #f2f2f2;
            }

            td {
                background-color: #fff;
            }

        </style>
    </head>

    <body>
        <jsp:include page="../component/header.jsp"/>
        <section class="ftco-section bg-light">
            <p class="text-center" style="color: red">${sessionScope.err}</p>
            <% request.getSession().removeAttribute("err"); %>
            <p class="text-center" style="color: green">${sessionScope.success}</p>
            <% request.getSession().removeAttribute("success"); %>
            <!--            <form action="schedule" method="post">
                            <div class="container m-auto w-50">
                                <h1 style=" font-family: Arial, Helvetica, sans-serif">Create Schedule</h1>
                                <div class="d-flex justify-content-between w-100">
                                    <div>
                                        <label>Select date</label><br>
                                        <input type="date" class="form-control" name="scheduleDate">
                                    </div>
                                </div>
                                <div>
                                    <label>Select slot</label><br>
            <c:forEach items="${sessionScope.slots}" var="s">
                <input type="checkbox" name="freeslot" value="${s.id}"/> Slot ${s.id} <br>
            </c:forEach>
        </div>
        <div>
            <label>Select skills:</label><br>
            <c:forEach items="${sessionScope.skills}" var="s">
                <input type="checkbox" name="skill" value="${s.skillname}"/>${s.skillname}<br>
            </c:forEach>
        </div>
        <div class="text-center">
            <input type="submit" class="btn btn-primary mt-2" style="padding: 10px 20px" value="Submit">
        </div>
    </div>
</form>-->

            <h1 style=" font-family: Arial, Helvetica, sans-serif" class="text-center">Create Schedule</h1>
            <div class="selectyear">
                <p class="text-center">Year: ${sessionScope.year}</p>
            </div>
            <form id="timetableForm" action="schedule" method="post">
                <input type="hidden" name="menid" value="${requestScope.mentor.id}"/>
                <input type="hidden" name="scheduleDate" id="date"/>
                <input type="hidden" name="freeSlot" id="slotid"/>
                <input type="hidden" name="remove" id="remove"/>
                <input type="hidden" name="sesid" id="sesid"/>
                <input type="hidden" name="changeweek" id="changeweek"/>
                <table class="table-bordered">
                    <tr>
                        <td style="background-color: #00BFFF">
                            Select week:
                            <select name="weeks" onchange="submitForm()">
                                <c:set var="weekField" value="1"/>
                                <c:forEach var="entry" items="${sessionScope.weeks_on_year}">
                                    <c:set var="key" value="${entry.key}"/>
                                    <c:set var="value" value="${entry.value}"/>                                                      
                                    <option value="${key} ${value}" <c:if test="${(requestScope.currentWeek eq weekField) or (requestScope.changeWeek eq weekField)}">selected</c:if> 
                                            >
                                        ${key.toString().split("-")[2]}-${key.toString().split("-")[1]} 
                                        to ${value.toString().split("-")[2]}-${value.toString().split("-")[1]}

                                    </option>
                                    <c:set var="weekField" value="${weekField + 1}"/>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="text-align: center; background-color: #00BFFF">Mon<br>${sessionScope.dates[0].toString().split("-")[2]}-${sessionScope.dates[0].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Tue<br>${sessionScope.dates[1].toString().split("-")[2]}-${sessionScope.dates[1].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Wed<br>${sessionScope.dates[2].toString().split("-")[2]}-${sessionScope.dates[2].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Ths<br>${sessionScope.dates[3].toString().split("-")[2]}-${sessionScope.dates[3].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Fri<br>${sessionScope.dates[4].toString().split("-")[2]}-${sessionScope.dates[4].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Sat<br>${sessionScope.dates[5].toString().split("-")[2]}-${sessionScope.dates[5].toString().split("-")[1]}</td>
                        <td style="text-align: center;background-color: #00BFFF">Sun<br>${sessionScope.dates[6].toString().split("-")[2]}-${sessionScope.dates[6].toString().split("-")[1]}</td>

                    </tr>
                    <c:forEach items="${sessionScope.slots}" var="r">
                        <tr>
                            <td>Slot ${r.id}</td>
                            <c:forEach var="d" items="${sessionScope.dates}">
                                <c:set var="hasSession" value="false" />
                                <c:forEach items="${sessionScope.sessions}" var="s">
                                    <c:if test="${(r.id eq s.slot.id) and (s.date eq d)}">
                                        <c:set var="hasSession" value="true" />
                                    </c:if>
                                </c:forEach>
                                <c:if test="${hasSession}">
                                    <td>
                                        <c:forEach items="${sessionScope.sessions}" var="s">
                                            <c:if test="${(r.id eq s.slot.id) and (s.date eq d)}">
                                                <span onclick="removeSchedule('${s.id}')">
                                                    Lecture:${sessionScope.account.username}<br>
                                                </span>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </c:if>
                                <c:if test="${!hasSession}">
                                    <td onclick="bookSchedule('${d}', ${r.id})">

                                    </td>
                                </c:if>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </section>

        <jsp:include page="../component/footer.jsp"/>


        <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery-migrate-3.0.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.easing.1.3.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.waypoints.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.stellar.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/owl.carousel.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.magnific-popup.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/jquery.animateNumber.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/scrollax.min.js"></script>
        <script
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
        <script src="${pageContext.request.contextPath}/resources/js/google-map.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
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

                                        function bookSchedule(date, slot) {
                                            if (window.confirm("Are u sure book with date " + date + ", slot" + slot)) {
                                                $("#date").val(date);
                                                $("#slotid").val(slot);
                                                $("#timetableForm").submit();
                                            }
                                        }

                                        function removeSchedule(id) {
                                            if (window.confirm("Are u sure remove schedule with id " + id)) {
                                                $("#remove").val('remove');
                                                $("#sesid").val(id);
                                                $("#timetableForm").submit();
                                            }
                                        }
                                        function submitForm() {
                                            $("#changeweek").val('change');
                                            $("#timetableForm").submit();
                                        }
        </script>

    </body>
</html>