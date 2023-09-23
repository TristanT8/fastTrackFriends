<%@ page isErrorPage="true"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <!-- YOUR own local CSS -->
    <link rel="stylesheet" href="/css/main.css" />
    <!-- For any Bootstrap that uses JS -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <meta charset="ISO-8859-1">
    <title>F1 Post Details</title>
</head>
<body class="text-center">
    <div class="container">
        <h3>View of Post</h3>
        <div class="row">
            <div class="col-md-4">
                <h5>Driver's Name:</h5>
                <p>${post.driver}</p>
                <h5>Circuit Name:</h5>
                <p>${post.circuit}</p>
            </div>
            <div class="col-md-4">
                <h5>Post Content:</h5>
                <p>${post.postContent}</p>
            </div>
            <div class="col-md-4">
                <h5>Posted On:</h5>
                <p>${post.createdAt}</p>
            </div>
        </div>
    </div>

    <a class="btn btn-outline-warning" href="/post/return/home">Return Home</a>
</body>
</html>
