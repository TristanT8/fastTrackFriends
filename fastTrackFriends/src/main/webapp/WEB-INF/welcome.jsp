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
	<style>
		.container {
			display: flex;
			justify-content: space-between;
		}
		.form-container {
			width: 45%;
		}
		.left-form {
			order: 1;
		}
		.right-form {
			order: 2;
		}
		@media (max-width: 768px) {
			.container {
				flex-direction: column;
			}
			.form-container {
				width: 100%;
			}
		}
		
	</style>
	
<title>Register or Login</title>
</head>

<body class="text-center">
    <img src="images/Screenshot (70).png" alt="WebP Image" style="width: flex; height: 600px;"/>

<h1>Welcome to the Fast Track Friends!!</h1>
<h2>Please Register or Login to proceed</h2>

	<div class="container">
		<div class="form-container left-form">
			<h4>Register Here</h4>
			<form:form action="/register" method="POST"
				modelAttribute="newUser">
				<form:errors class="error text-danger" path="username" />
				<div>
					<label for="username">Username:</label>
					<form:input class="form-control" path="username" />
					<form:label path="username" />
				</div>
				<form:errors class="error text-danger" path="name" />
				<div>
					<label for="name">Name:</label>
					<form:input class="form-control" path="name" />
					<form:label path="name" />
				</div>
				<form:errors class="error text-danger" path="email" />
				<div>
					<label for="email">Email:</label>
					<form:input class="form-control" path="email" />
					<form:label path="email" />
				</div>
				<form:errors class="error text-danger" path="password" />
				<div>
					<label for="password">Password:</label>
					<form:input class="form-control" path="password" type="password" />
					<form:label path="password" />
				</div>
				<form:errors class="error text-danger" path="confirm" />
				<div>
					<label for="confirm">Confirm Password:</label>
					<form:input class="form-control" path="confirm" type="password" />
					<form:label path="confirm" />
				</div>

				<input type="submit" value="Register" class="btn btn-success" />
			</form:form>
		</div>

		<div class="form-container right-form">
			<h4>Login Below</h4>
			<form:form class="form-floating"
				action="/login"
				method="POST" modelAttribute="newLoginValidator">
				<form:errors class="error text-danger" path="email" />
				<div>
					<label for="email">Email:</label>
					<form:input class="form-control" path="email" />
					<form:label path="email" />
				</div>
				<form:errors class="error text-danger" path="password" />
				<div>
					<label for="password">Password:</label>
					<form:input class="form-control" path="password" type="password" />
					<form:label path="password" />
				</div>
				<input type="submit" value="Login" class="btn btn-success" />

			</form:form>

		</div>
	</div>

</body>
</html>