<%@page import="in.keen.Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Edit Profile Page </title>
</head>
<body>

<% User user = (User) session.getAttribute("session_user"); %>

<h2>Edit Profile Details</h2>
<hr>

<form action="<%= request.getContextPath()%>/updateAdminDetails" method="post">

<input type="hidden" name="userId" value="<%= user.getUserId() %>"> <br><br>

<label>Name :</label>
<input type="text" name="userName" value="<%= user.getUserName() %>"> <br><br>

<label>Email :</label>
<input type="email" name="userEmail" value="<%= user.getUserEmail() %>"> <br><br>

<label>Password:</label>
<input type="text" name="userPassword" value="<%= user.getUserPassword() %>"> <br><br>

<button type="submit">Update Details</button>

 </form>
 <br><br>
 <a href="<%= request.getContextPath()%>/Admin/viewAdminProfile.jsp"><button>Back</button></a>

</body>
</html>