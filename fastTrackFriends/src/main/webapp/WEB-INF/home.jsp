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
<script>
    function confirmDelete(teamId) {
        if (confirm("Are you sure you want to delete this team?")) {
            window.location.href = "team/delete/" + teamId; // Redirect to the delete URL
        }
    }
</script>
<title>Kickball Manager Home</title>
</head>
<body class="text-center">
	<h3>
		Welcome,
		<c:out value="${currentUser.name}" />
		!
	</h3>
	<h5>Team Manager Below:</h5>

	<table class="table table-striped table-hover">
		<thead>
			<tr>
				<th>ID:</th>
				<th>Team Name:</th>
				<th>Skill Level:</th>
				<th>Players:</th>
				<th>Game Day:</th>
				<th>Actions:</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="team" items="${allTeams}">
				<tr>
					<td><c:out value="${team.id}" /></td>
					<td><c:out value="${team.name}" /></td>
					<td><c:out value="${team.level}" /></td>
					<td>${team.players.size()}/9</td>
					<td>${team.day}</td>
					<td><c:if test="${team.user.id eq currentUser.id}">
							<a class="btn btn-outline-primary" href="team/edit/${team.id}">Edit</a>
							<a class="btn btn-outline-danger"
								onclick="confirmDelete(${team.id})">Delete</a>
						</c:if> <a class="btn btn-outline-secondary" href="/team/view/${team.id}">View
							Details</a></td>
				</tr>
			</c:forEach>

		</tbody>

	</table>

	<a class="btn btn-outline-success" href="/team/add">Add Team To
		List</a>
	<a class="btn btn-danger" href="/logout">Logout Here</a>
</body>
</html>
