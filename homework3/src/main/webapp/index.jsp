<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
</head>
<body>
<!-- Form with a button to redirect to signUP.html -->
<form action="#" method="get">
    <button type="button" onclick="redirectToSignUp()">signUP</button>
</form>

<!-- JavaScript function to redirect to signUP.html -->
<script>
    function redirectToSignUp() {
        window.location.href = "http://localhost:8080/homework3_war/JSP/signUP.jsp";
    }
</script>
</body>
</html>
