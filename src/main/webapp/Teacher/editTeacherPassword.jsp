<%@page import="in.keen.Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher Password Edit Page</title>
</head>
<body>

<% User user = (User) session.getAttribute("session_user"); %>

<form action="<%= request.getContextPath()%>/updateTeacherPassword" method="post">

<label> Current Password : <%= user.getUserPassword() %></label><br><br>

<label>New Password :</label>
<input type="text" name="newPassword" required><br><br>

<label>Confirm Password :</label>
<input type="text" name="confirmPassword" required><br><br>

<button type="submit">Change Password</button>
 
</form>
<br><br>

<a href="<%= request.getContextPath()%>/viewTeacherProfile"><button>CANCEL</button></a>

</body>
</html>