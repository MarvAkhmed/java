<%--
  Created by IntelliJ IDEA.
  User: Itis
  Date: 24/11/2023
  Time: 8:09 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign Up</title>

    <style>
    </style>
</head>
<body>

<div class="signup-container">
    <h2>Sign Up</h2>
    <form id="signupForm" action="${pageContext.request.contextPath}/profile" method="post" onsubmit="return redirectToProfile()">
        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" placeholder="Enter your first name" required>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" placeholder="Enter your last name" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" placeholder="Enter your email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" placeholder="Enter your password" required>

        <label for="confirmPassword">Confirm Password:</label>
        <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password" required>

        <button type="submit">Sign Up</button>
    </form>
</div>

<script>
    function redirectToProfile() {
        // Perform any client-side validation here if needed

        // Redirect to the profile page after form submission
        window.location.href = "http://localhost:8080/homework3_war/profile";
        return true; // Returning true allows the form submission to proceed
    }
</script>

</body>
</html>
