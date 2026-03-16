<%@page import="java.util.List"%>
<%@page import="in.keen.Model.Enrollment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course Students Page</title>
</head>
<body>

<% List<Enrollment> list = (List<Enrollment>) request.getAttribute("courseStudentList"); %>

<h3>Student enrolled </h3>

<table border="1">
<tr>
<th>Student Id</th>
<th>Student Name</th>
</tr>

<% for(Enrollment e : list){ %>
<tr>
<td> <%= e.getStudentId() %></td>
<td> <%= e.getStudentName() %></td>
</tr>
<%} %>
</table >
<br><br>
<a href="<%= request.getContextPath()%>/teachersCourses"><button>Back</button></a>
</body>
</html>