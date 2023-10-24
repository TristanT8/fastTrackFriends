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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    $(document).ready(function() {
        // Function to send a like request to the server
        function likePost(postId) {
            // Make an AJAX request to the server to like the post
            $.post("/like/" + postId, function(data) {
                // Update the like count on the page
                $("#likeCount").text("Current Likes: " + data.likeCount);
            });
        }

        // Attach a click event to the like button
        $("#likeButton").click(function() {
            var postId = $("#hiddenPostId").val();
            likePost(postId);
        });
    });
</script>
    <script>
        $(document).ready(function() {
            $("#likeButton").click(function() {
                alert("Button Clicked");
            });
        });
    </script>

<title>F1 Post Details</title>

</head>
<body class="text-center">
	<div class="container">
		<h3>View of Post</h3>
		<div class="row">
			<div class="col-md-4">
				<h5>Post ID:</h5>
				<p id="postId">${post.id}</p>
				<!-- Add a hidden input field for postId -->
				<input type="hidden" id="hiddenPostId" value="${post.id}" />
			</div>
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
		<!-- In your JSP template -->
		<!-- Display the current like count -->
		<p id="likeCount">Current Likes: ${updatedLikeCount}</p>

		<!-- Button to like the post -->
		<button id="likeButton" class="btn btn-outline-primary">Like</button>
	</div>

	<a class="btn btn-outline-warning" href="/post/return/home">Return
		Home</a>

</body>
</html>
