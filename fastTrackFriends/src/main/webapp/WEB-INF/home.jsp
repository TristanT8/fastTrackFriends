<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        function confirmDelete(postId) {
            if (confirm("Are you sure you want to delete this post?")) {
                window.location.href = "post/delete/" + postId; // Redirect to the delete URL
            }
        }
    </script>
    <title>Fast Track Friends Home</title>
</head>
<body>
    <h3>
        Welcome, <c:out value="${currentUser.name}"/>!
    </h3>

    <h5>All Posts Below:</h5>

    <div class="text-center">   
        <a class="btn btn-outline-success" href="/post/add"> Create Post</a>
    </div>  
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

        <tbody>
            <c:forEach var="post" items="${allPosts}">
                <tr>
                    <td><c:out value="${post.driver}" /></td>
                    <td><c:out value="${post.circuit}" /></td>
                    <td><c:out value="${post.user.username}" /></td>
                    <td><c:out value="${post.createdAt}" /></td>
                    <td><c:if test="${post.user.id eq currentUser.id}">
                        <a class="btn btn-outline-primary" href="/post/edit/${post.id}">Edit</a>
                        <a class="btn btn-outline-danger" onclick="confirmDelete(${post.id})">Delete</a>
                        </c:if>
                        <a class="btn btn-outline-secondary" href="/post/view/${post.id}">View Post</a>                       
                    </td>
                </tr>
            </c:forEach>       
        </tbody>

    </table>
</body>
</html>
