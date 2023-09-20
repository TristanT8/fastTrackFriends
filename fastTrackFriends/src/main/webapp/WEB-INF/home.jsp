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
<title>Fast Track Friends Home</title>
</head>
<body>
	<h3>
		Welcome, <c:out value="${currentUser.name}"/>!
	</h3>
	
	<h5>All Posts Below:</h5>

	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th class="col-md-3"> Driver: </th>
				<th class="col-md-4"> Circuit: </th>
				<th> Posted By: </th>
				<th> Posted: </th>
				<th> Actions: </th>
			</tr>
		</thead>
	</table>
	
</body>
</html>