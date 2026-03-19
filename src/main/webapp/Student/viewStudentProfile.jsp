<%@page import="in.keen.Model.Student"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Student Profile</title>
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

        <% Student s = (Student) request.getAttribute("studentProfileDetails"); %>

        <div class="info-group">
            <span class="label">Roll No / ID:</span>
            <span class="value"><%= s.getStudentId() %></span>
        </div>

        <div class="info-group">
            <span class="label">Full Name:</span>
            <span class="value"><%= s.getStudentName() %></span>
        </div>

        <div class="info-group">
            <span class="label">Email:</span>
            <span class="value"><%= s.getStudentEmail() %></span>
        </div>
        
        <div class="info-group">
            <span class="label">Password:</span>
            <span class="value"><%= s.getStudentPassword() %></span>
        </div>

        <div class="info-group">
            <span class="label">Department:</span>
            <span class="value"><%= s.getStudentDepartment() %></span>
        </div>

        <div class="info-group">
            <span class="label">Current Year:</span>
            <span class="value"><%= s.getStudentYear() %></span>
        </div>

        <div class="info-group">
            <span class="label">Admission Date:</span>
            <span class="value"><%= s.getStudentAddmissionYear() %></span>
        </div>

        <br>
        <a href="<%= request.getContextPath() %>/Student/editStudentPassword.jsp"><button>Change Password</button></a>
        <a href="<%= request.getContextPath() %>/DashBoard/studentDashboard.jsp"><button style="background: #eee;">Back</button></a>
    </div>
</body>
</html>