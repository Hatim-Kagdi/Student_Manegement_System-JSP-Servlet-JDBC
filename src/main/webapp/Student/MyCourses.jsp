<%@page import="in.keen.Model.Enrollment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Courses</title>
</head>
<body>
<% List<Enrollment> list = (List<Enrollment>) request.getAttribute("courseList"); %>

<h2>My Courses</h2>

<table border="1">

<tr>
<th>Course</th>
<th>Teacher</th>
</tr>

<% for(Enrollment e : list){ %>

<tr>
<td><%= e.getCourseName() %></td>
<td><%= e.getTeacherName() %></td>
</tr>

<% } %>

</table>

<br>

<a href="<%= request.getContextPath() %>/DashBoard/studentDashboard.jsp"><button>BACK</button></a>
</body>
</html>