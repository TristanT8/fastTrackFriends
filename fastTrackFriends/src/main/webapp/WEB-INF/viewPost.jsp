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
    <!--  <link rel="stylesheet" href="/css/main.css" /> -->
    <!-- For any Bootstrap that uses JS -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <meta charset="UTF-8">
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

    <a class="btn btn-outline-warning" href="/post/return/home">Return Home</a>

    <!-- Include your JavaScript code here -->
    <script>
		document.addEventListener("DOMContentLoaded", function () {
    	document.getElementById("likeButton").addEventListener("click", function () {
        console.log("Like button clicked");

        // Access postId and userId directly from the JSP template
        const postId = ${post.id};
        const userId = ${post.user.id};

        // Send an HTTP POST request to your '/likes' endpoint here
        fetch("/likes/likePost", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                postId: postId,
                userId: userId
            }),
        })
        .then((response) => {
            console.log("Response status:", response.status);
            if (response.status === 200) {
                // Parse the response JSON
                return response.json();
            } else {
                // Handle errors or display a message if the like couldn't be added
                console.error("Failed to add like");
            }
        })
        .then((data) => {
            if (data) {
                // Update the like count on the view page
                const likeCountElement = document.getElementById("likeCount");
                if (likeCountElement) {
                    likeCountElement.innerText = `Current Likes: ${data.likeCount}`;
                    console.log("Like count updated: " + data.likeCount); // Log the updated like count
                } else {
                    console.error("Like count element not found"); // Debug statement
                }
            }
        })
        .catch((error) => {
            console.error("Error:", error);
        });
    });
});
</script>
</body>
</html>
