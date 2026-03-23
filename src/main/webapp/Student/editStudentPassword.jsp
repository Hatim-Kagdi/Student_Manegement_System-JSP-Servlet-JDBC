<%@page import="in.keen.Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Student Password Page</title>
</head>
<body>

<% User user = (User) session.getAttribute("session_user");%>

<h2>Edit Password</h2>
<hr>

<form action="<%= request.getContextPath() %>/updateStudentPassword" method="post">
    <% if(request.getParameter("error") != null) { %>
        <p style="color:red;"><%= request.getParameter("error") %></p>
    <% } %>

    Current Password: ******** (Encrypted) <br><br>
    New Password: <input type="text" name="newPassword" required><br><br>
    Confirm New Password: <input type="text" name="confirmPassword" required><br><br>

    <button type="submit">Change Password</button><br><br>
</form>

<a href="<%= request.getContextPath()%>/studentProfile"><button>CANCEL</button></a>

</body>
</html>