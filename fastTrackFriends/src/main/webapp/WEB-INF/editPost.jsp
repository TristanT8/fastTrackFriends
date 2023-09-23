<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
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
<title>Edit Post Page</title>
</head>
<body>
<div class="text-center">
    <h3 class="text-primary">Edit your post below:</h3>
</div>
<div class="container">
    <div class="row justify-content-center">
        <form:form action="/post/update/${post.id}" method="POST" modelAttribute="post" class="col-md-6">
            <input type="hidden" name="_method" value="PUT">

            <form:errors path="driver" class="error text-danger" />
            <div class="form-group">
                <form:label path="driver">Driver Name:</form:label>
                <form:input path="driver" class="form-control" />
            </div>
            <form:errors path="circuit" class="error text-danger" />
            <div class="form-group">
                <form:label path="circuit">Circuit Name:</form:label>
                <form:input path="circuit" class="form-control" />
            </div>
            <form:errors path="postContent" class="error text-danger" />
            <div class="form-group">
                <form:label path="postContent">Post Content:</form:label>
                <form:textarea path="postContent" class="form-control" rows="3"></form:textarea>
            </div>

            <div class="text-center">
                <input class="btn btn-success" type="submit" value="Submit" /> 
                <a class="btn btn-outline-warning" href="/post/return/home">Return Home</a>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>