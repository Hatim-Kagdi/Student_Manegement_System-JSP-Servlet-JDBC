<%@page import="in.keen.Model.Teacher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Teacher Profile</title>
    <style>
        .profile-container { width: 50%; margin: 50px auto; border: 1px solid #ddd; padding: 20px; border-radius: 8px; font-family: sans-serif; }
        .info-group { margin-bottom: 15px; border-bottom: 1px solid #eee; padding-bottom: 5px; }
        .label { font-weight: bold; color: #555; display: inline-block; width: 150px; }
        .value { color: #222; }
        .locked-msg { font-size: 12px; color: #cc0000; font-style: italic; }
    </style>
</head>
<body>
    <div class="profile-container">
        <h2>My Profile</h2>
        <p class="locked-msg">* Academic details can only be changed by the Administration.</p>
        <hr>

        <% Teacher s = (Teacher) request.getAttribute("teacherProfileDetails"); %>

        <div class="info-group">
            <span class="label">Roll No / ID:</span>
            <span class="value"><%= s.getTeacherId() %></span>
        </div>

        <div class="info-group">
            <span class="label">Full Name:</span>
            <span class="value"><%= s.getTeacherName() %></span>
        </div>

        <div class="info-group">
            <span class="label">Email:</span>
            <span class="value"><%= s.getTeacherEmail() %></span>
        </div>
 		<div class="info-group">
            <span class="label">Password:</span>
            <span class="value"><%= s.getTeacherPassword() %></span>
        </div>
        <div class="info-group">
            <span class="label">Department:</span>
            <span class="value"><%= s.getTeacherDepartment() %></span>
        </div>

        <br>
        <a href="<%= request.getContextPath() %>/Teacher/editTeacherPassword.jsp"><button>Change Password</button></a>
        <a href="<%= request.getContextPath() %>/DashBoard/teacherDashboard.jsp"><button style="background: #eee;">Back</button></a>
    </div>
</body>
</html>