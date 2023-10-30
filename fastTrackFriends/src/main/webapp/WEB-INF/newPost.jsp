<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <!-- for Bootstrap CSS -->
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <!-- YOUR own local CSS -->
    <link rel="stylesheet" href="/css/main.css" />
    <!-- For any Bootstrap that uses JS -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
    <title>Create New Post</title>
    <script type="text/javascript">
        function handleDriverSelection() {
            var driverSelect = document.getElementById("driverSelect");
            var otherDriverInput = document.getElementById("otherDriverInput");

            if (driverSelect.value === "other") {
                otherDriverInput.style.display = "block";
            } else {
                otherDriverInput.style.display = "none";
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h3>Create your new post below!</h3>
                <form:form action="/post/created" method="POST" modelAttribute="post">
                    <form:errors class="error text-danger" path="driver" />
                    <div class="form-group">
                        <label for="driver">Driver Name:</label>
                        <form:select id="driverSelect" path="driver" class="form-control"
                            onchange="handleDriverSelection()">
                            <form:option value="" label="Select a Driver" />
                            <c:forEach items="${drivers}" var="driver">
                                <form:option value="${driver}" label="${driver}" />
                            </c:forEach>
                            <form:option value="other" label="Other" />
                        </form:select>
                    </div>
                    <div class="form-group" id="otherDriverInput" style="display: none;">
                        <label for="otherDriver">Other Driver:</label>
                        <form:input path="otherDriver" class="form-control" />
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
                        <input class="btn btn-success" type="submit" value="Submit" /> <a
                            class="btn btn-outline-warning" href="/post/return/home">Return
                            Home</a>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
