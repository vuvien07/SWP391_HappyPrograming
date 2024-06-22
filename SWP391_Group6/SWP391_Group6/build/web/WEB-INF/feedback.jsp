<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Feedback Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        /* Star Rating System */
        .rating {
            display: flex;
            flex-direction: row-reverse;
            justify-content: center;
            margin-left: -4px;
            float: left;
        }

        .rating>input {
            display: none;
        }

        .rating>label {
            position: relative;
            width: 25px;
            font-size: 25px;
            color: #ff0000;
            cursor: pointer;
        }

        .rating>label::before {
            content: "\2605";
            position: absolute;
            opacity: 0;
        }

        .rating>label:hover:before,
        .rating>label:hover~label:before {
            opacity: 1 !important;
        }

        .rating>input:checked~label:before {
            opacity: 1;
        }

        .rating:hover>input:checked~label:before {
            opacity: 0.4;
        }

        /* Average Rating Stars */
        .average-rating span.rating {
            color: #f2b600;
        }

        /* Other styles */
        .navbar-nav {
            width: 100%;
        }

        @media (min-width: 568px) {
            .end {
                margin-left: auto;
            }
        }

        @media (max-width: 768px) {
            #post {
                width: 100%;
            }
        }

        #clicked {
            padding-top: 1px;
            padding-bottom: 1px;
            text-align: center;
            width: 100%;
            background-color: #ecb21f;
            border-color: #a88734 #9c7e31 #846a29;
            color: black;
            border-width: 1px;
            border-style: solid;
            border-radius: 13px;
        }

        #profile {
            background-color: unset;
        }

        #post {
            margin: 10px;
            padding: 6px;
            padding-top: 2px;
            padding-bottom: 2px;
            text-align: center;
            background-color: #ecb21f;
            border-color: #a88734 #9c7e31 #846a29;
            color: black;
            border-width: 1px;
            border-style: solid;
            border-radius: 13px;
            width: 50%;
        }

        body {
            background-color: black;
        }

        #nav-items li a, #profile {
            text-decoration: none;
            color: rgb(224, 219, 219);
            background-color: black;
        }

        .comments {
            margin-top: 5%;
            margin-left: 20px;
        }

        .darker {
            border: 1px solid #ecb21f;
            background-color: black;
            float: right;
            border-radius: 5px;
            padding-left: 40px;
            padding-right: 30px;
            padding-top: 10px;
        }

        .comment {
            border: 1px solid rgba(16, 46, 46, 1);
            background-color: rgba(16, 46, 46, 0.973);
            float: left;
            border-radius: 5px;
            padding: 10px;
            margin-top: 30px;
        }

        .comment img {
            margin-top: -10px;
            margin-left: -15px;
        }

        .comment h4 {
            margin-left: 50px;
            color: white;
        }

        .comment span {
            color: rgb(223, 217, 217);
            font-size: 12px;
        }

        .comment p {
            margin-left: 50px;
            color: rgb(224, 219, 219);
        }

        .form-inline {
            display: flex;
            flex-flow: row wrap;
            align-items: center;
        }

        @media (max-width: 800px) {
            .form-inline label {
                margin: 5px 10px 0 0;
            }

            .form-inline {
                flex-direction: column;
                align-items: stretch;
            }
        }
    </style>
</head>
<body>
<!-- Main Body -->
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-5 col-md-6 col-12 pb-4">
                <h1>Comments</h1>

                <!-- Average Rating -->
                <div class="average-rating mb-4">
                    <h2>Average Rating:${averageRating}
                        <span>
                            <c:forEach var="i" begin="1" end="${averageRating}">
                                <i class="fa fa-star"></i>
                            </c:forEach>
                            <c:forEach var="i" begin="${averageRating + 1}" end="5">
                                <i class="fa fa-star-o"></i>
                            </c:forEach>
                        </span>
                    </h2>
                </div>

                <!-- Comments Section -->
                <c:forEach var="feedback" items="${feedbacks}">
                    <div class="comment mt-4 text-justify float-left">
                        <h4>${feedback.user.name}</h4>
                        <div class="rating">
                            <!-- Hiển thị số sao theo đánh giá của feedback -->
                            <span>${feedback.rate}</span>
                            <c:forEach var="i" begin="1" end="5">
                                <i class="fa ${i <= feedback.rate ? 'fa-star' : 'fa-star-o'}"></i>
                            </c:forEach>
                        </div>
                        <br>
                        <p>${feedback.comment}</p>
                    </div>
                </c:forEach>

            </div>
            <div class="col-lg-4 col-md-5 col-sm-4 offset-md-1 offset-sm-1 col-12 mt-4">
                <form id="align-form" action="submitFeedback" method="post">
                    <div class="form-group">
                        <h4>Leave a comment</h4>
                        <label for="message">Message</label>
                        <textarea name="msg" id="msg" cols="30" rows="5" class="form-control"
                                  style="background-color: black;"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="rating">
                            <div class="rating">
                                <input type="radio" name="rating" value="5" id="5"><label for="5">☆</label>
                                <input type="radio" name="rating" value="4" id="4"><label for="4">☆</label>
                                <input type="radio" name="rating" value="3" id="3"><label for="3">☆</label>
                                <input type="radio" name="rating" value="2" id="2"><label for="2">☆</label>
                                <input type="radio" name="rating" value="1" id="1"><label for="1">☆</label>
                            </div>
                        </label>
                    </div>
                    <div class="form-group">
                        <p class="text-secondary">If you have a <a href="#" class="alert-link">gravatar account</a> your address will be used to display your profile picture.</p>
                    </div>
                    <div class="form-inline">
                        <input type="checkbox" name="check" id="checkbx" class="mr-1">
                        <label for="subscribe">Subscribe me to the newsletter</label>
                    </div>
                    <div class="form-group">
                        <button type="submit" id="post" class="btn">Post Comment</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</body>
</html>
