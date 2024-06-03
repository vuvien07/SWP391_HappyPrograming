<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Create Skill</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            img {
                width: 200px;
                height: 120px;
            }
            select {
                width: 32.3%;
                margin: 0;
                font-size: 100%;
                padding: 5px 10px 5px 10px;
                font-family: Segoe UI, Helvetica, sans-serif;
                border: 1px solid #D0D0D0;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
                border-radius: 20px;
                outline: none;
            }


            .error-message {
                color: red;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Create new skill</h2>
                        </div>
                    </div>
                </div>
              


            </div>
            <div id="editSkillModal">
                <div class="modal-dialog" style="width: 100%">
                    <div class="modal-content">
                        <form id="form" action="inputskills" method="post">

                            <div class="modal-header">						
                                <h4 class="modal-title">Create skill</h4>
                                <a href="skills">
                                    <button style="position: absolute; right: 20px; top: 20px; color: black" type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> 
                                </a>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Skill Name</label>
                                    <input name="skillname" type="text" class="form-control" placeholder="Enter a skill name" required>
                                </div>
                                <div class="form-group" style="display: flex; align-items: center; justify-content: space-between">
                                    <label style="margin-right: 20px">Image</label>
                                    <input id="imageInput" name="image" type="file" enctype="multipart/form-data">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea style="height: 200px" name="description" class="form-control" placeholder="Enter a description" required></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Create">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
