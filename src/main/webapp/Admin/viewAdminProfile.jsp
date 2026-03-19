<%@page import="in.keen.Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Profile Page</title>
<style>
        .profile-container { width: 50%; margin: 50px auto; border: 1px solid #ddd; padding: 20px; border-radius: 8px; font-family: sans-serif; }
        .info-group { margin-bottom: 15px; border-bottom: 1px solid #eee; padding-bottom: 5px; }
        .label { font-weight: bold; color: #555; display: inline-block; width: 150px; }
        .value { color: #222; }
        .locked-msg { font-size: 12px; color: #cc0000; font-style: italic; }
    </style>
</head>
<body>
<% User user = (User) session.getAttribute("session_user"); %>
<% if("success".equals(request.getParameter("msg"))) { %>
    <div style="color: green; padding: 10px; border: 1px solid green;">
        Profile updated successfully!
    </div>
<% } %>
<div class="profile-container">
        <h2>My Profile</h2>
        <hr>
        
        <div class="info-group">
            <span class="label">ID:</span>
            <span class="value"><%= user.getUserId() %></span>
        </div>

        <div class="info-group">
            <span class="label">Full Name:</span>
            <span class="value"><%= user.getUserName() %></span>
        </div>

        <div class="info-group">
            <span class="label">Email:</span>
            <span class="value"><%= user.getUserEmail() %></span>
        </div>

        <div class="info-group">
            <span class="label">Password :</span>
            <span class="value"><%= user.getUserPassword() %></span>
        </div>

        <br>
        <a href="<%= request.getContextPath() %>/Admin/editAdminProfile.jsp"><button>Edit Details</button></a>
        <a href="<%= request.getContextPath() %>/DashBoard/adminDashboard.jsp"><button style="background: #eee;">Back</button></a>
    </div>

</body>
</html>