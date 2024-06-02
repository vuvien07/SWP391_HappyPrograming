<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <style>
            body {
                margin: 0;
                padding: 0;
                background-color: #fbfbfb;
            }
            @media (min-width: 991.98px) {
                main {
                    padding-left: 270px;
                }
            }

            /* Sidebar */
            .sidebar {
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                padding: 58px 0 0; /* Height of navbar */
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
                width: 270px;
                z-index: 600;
            }

            @media (max-width: 991.98px) {
                .sidebar {
                    width: 100%;
                }
            }
            .sidebar .active {
                border-radius: 5px;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
            }

            .sidebar-sticky {
                position: relative;
                top: 0;
                height: calc(100vh - 48px);
                padding-top: 0.5rem;
                overflow-x: hidden;
                overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
            }
        </style>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap">
        <link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb5/3.8.1/compiled.min.css">
        <link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb-plugins-gathered.min.css">
    </head>
    <body>
        <header>
            <jsp:include page="LeftDashboard.jsp"></jsp:include>
            </header>
        <jsp:include page="header.jsp"></jsp:include>
            <main>
                <div class="container pt-4">          
                    <section class="mb-4">
                        <div class="card">
                            <div class="row" style="">
                                <div class="col-lg-4" style="text-align: center; margin-top: 20px; margin-bottom: 20px;padding-top: 20px">
                                    <h3 class="mb-0" id="">
                                        <strong>Manage Skills</strong>
                                    </h3>
                                </div>

                                <div class="col-lg-6" style="text-align: center; margin-top: 20px; margin-bottom: 20px;padding-top: 20px;">
                                    <form action="skills" method="post" style="display: flex; justify-content: center">
                                        <input name="valueSearch" value="${requestScope.searchValue != null ? requestScope.searchValue : ""}"  type="text" placeholder="Search skill name" style="width: 60%; padding: 4px 10px; border-radius: 15px">
                                    <button type="submit" style="border-radius: 50%; width: 40px; font-size: 18px; margin-left: 10px"><i class="fa fa-search"></i></button>
                                </form>
                            </div>
                            <div class="col-lg-2">
                                <a href="#addEmployeeModal" style="height: 40px; margin-top: 36px;" class="buttonadd btn btn-success" data-toggle="modal"><i class="fa-solid fa-plus"></i></a>
                            </div>
                        </div>

                    </div>

                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover text-nowrap">
                                <thead>
                                    <tr>
                                        <th class="text_page_head" scope="col">ID</th>
                                        <th class="text_page_head" scope="col">Skill name</th>
                                        <th class="text_page_head" scope="col">Status</th> 
                                        <th class="text_page_head" scope="col">Description</th> 
                                        <th class="text_page_head" scope="col">Image</th> 
                                        <th style="text-align: center;" class="text_page_head" scope="col">Actions</th> 
                                    </tr>
                                </thead>
                                <tbody id="contentt">
                                    <c:forEach items="${listByPage}" var="s">
                                        <tr>
                                            <td class="text_page">${s.id}</td>
                                            <td class="text_page">${s.skillname}</td>
                                            <td class="text_page">${s.status ? "Active" : "Inactive"}</td>
                                            <td class="text_page">${s.description}</td>
                                            <td class="text_page"><img src="resources/images/${s.image}" alt="${s.skillname}" width="50" height="50"/></td>
                                            <td class="text_page" style="padding: 10px 2px 14px; text-align: center;">
                                                <a href="updateskill?sid=${s.id}"><button type="button" class="btn btn-warning"><i class="fa-solid fa-pen"></i></button></a>
                                                <a href="hideskill?sid=${s.id}">
                                                    <button type="button" class="btn btn-secondary" id="hideButton">
                                                        <i class="fa-solid fa-eye-slash"></i>
                                                    </button>

                                                </a>
                                            </td> 
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>

                            <div class="clearfix" style="text-align: center">
                                <ul class="pagination" style="padding-left: 420px;">
                                    <c:if test="${page != 1}">
                                        <li class="page-item">
                                            <a class="page-link" href="skills?page=1">First</a>
                                        </li>
                                        <li class="page-item">
                                            <a class="page-link" href="skills?page=${page-1}">Previous</a>
                                        </li>
                                    </c:if>

                                    <c:forEach begin="1" end="${numberpage}" var="i">
                                        <li class="page-item">
                                            <a class="${page == i ? 'page-link activee' : 'page-link'}" href="skills?page=${i}">
                                                ${i}
                                            </a>
                                        </li>
                                    </c:forEach>

                                    <c:if test="${page < numberpage}">
                                        <li class="page-item">
                                            <a class="page-link" href="skills?page=${page+1}">Next</a>
                                        </li>
                                        <li class="page-item">
                                            <a class="page-link" href="skills?page=${numberpage}">Last</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>



                        </div>
                    </div>
            </div>
        </section>

    </div>


</main>
<script type="text/javascript">
    <% String message = (String)request.getAttribute("mess"); %>
    <% if(message != null && !message.isEmpty()) { %>
    alert("<%= message %>");
    <% } %>


</script>

</body>
</html>
