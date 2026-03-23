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

<h2>Edit Password</h2>
<hr>

<form action="<%= request.getContextPath()%>/updateAdminPassword" method="post">

 <% if(request.getParameter("error") != null) { %>
        <p style="color:red;"><%= request.getParameter("error") %></p>
    <% } %>

<label> New Password:</label>
<input type="text" name="newPassword" placeholder="Enter new password..."> <br><br>

<label> Confirm Password:</label>
<input type="text" name="confirmPassword" placeholder="Re-enter to confirm password..."> <br><br>

<button type="submit"> Change Password </button>

 </form>
 <br><br>
 <a href="<%= request.getContextPath()%>/Admin/viewAdminProfile.jsp"><button>Back</button></a>

</body>
</html>