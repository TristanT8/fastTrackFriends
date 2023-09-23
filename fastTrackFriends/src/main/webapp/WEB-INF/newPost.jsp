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
<title>Create New Post</title>
</head>
<body>
	
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<h3>Create your new post below!</h3>
				<form:form action="/post/created" method = "POST" modelAttribute="post">
					
					<form:errors class="error text-danger" path="driver" />
					<div class="form-group">
					 	<label for="driver">Driver Name:</label>
					 	<form:input class="form-control" path="driver" />
					</div>
					<form:errors class="error text-danger" path="circuit" />
					<div class="form-group">
						<label for="circuit">Circuit Name:</label>
						<form:input class="form-control" path="circuit" />
					</div>
					<form:errors class="error text-danger" path="postContent" />
					<div class="form-group">
					 	<label for="postContent">Post Content:</label>
                		<form:textarea path="postContent" class="form-control" rows="3"></form:textarea>
					</div>
					<div class="text-center">
						<input class="btn btn-success" type="submit" value="Submit" />
						<a class="btn btn-outline-warning" href="/post/return/home" >Return Home</a>
						
					</div>					 
				</form:form>
			</div>
		</div>
	</div>
	
</body>
</html>