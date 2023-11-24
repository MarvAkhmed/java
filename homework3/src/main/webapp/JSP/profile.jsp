<%--
  Created by IntelliJ IDEA.
  User: Itis
  Date: 24/11/2023
  Time: 8:33 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
</head>
<body>

<div class="profile-container">
    <h2>Welcome to Your Profile</h2>
    <p>This is your user profile page. Below is your information:</p>

    <ul>
        <li><strong>First Name:</strong> <%= request.getParameter("firstName") %></li>
        <li><strong>Last Name:</strong> <%= request.getParameter("lastName") %></li>
        <li><strong>Email:</strong> <%= request.getParameter("email") %></li>
    </ul>

    <!-- You can add more user-related information or functionality here -->

    <p><a href="<%= request.getContextPath() %>/">Back to Home</a></p>
</div>

</body>
</html>
