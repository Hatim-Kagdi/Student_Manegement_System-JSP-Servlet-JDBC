<%@page import="in.keen.Model.Enrollment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Students Grading Page</title>
</head>
<body>
<% List<Enrollment> list = (List<Enrollment>) request.getAttribute("studentListForMarks");
	int courseId = (int) request.getAttribute("selectedCourseId"); %>
	
	<h3>Grading Sheet for <%= courseId %></h3>
	
	<form action="<%= request.getContextPath()%>/saveMarks" method="post">
	
	<input type="hidden" name="courseId" value="<%= courseId %>"> 
	
	<table border="1" cellpadding="10">
	<thead>
	<tr>
	<th>Student Id</th>
	<th>Student Name</th>
	<th>Enter Marks (0 - 100)</th>
	</tr>
	</thead>
	
	<tbody>
	<% if(list != null && !list.isEmpty()){ 
	for(Enrollment e : list){%>
	<tr>
	<td><%= e.getStudentId() %></td>
	<td> <%= e.getStudentName() %> </td>
	<td> <input type="number" name="marks_<%= e.getStudentId()%>" min="0" max="100" required placeholder="Enter 0-100">
	<input type="hidden" name="studentsId" value="<%= e.getStudentId() %>">
	</td>
	</tr>
	<%} }else{ %>
	<tr><td colspan="3">No records found for this student </td></tr>
	<%} %>
	</tbody>
	</table>
	<br>
	<button type="submit">Submit</button>
	</form>
	<br>
	<a href="<%= request.getContextPath()%>/DashBoard/teacherDashboard.jsp"><button>Back</button></a>
</body>
</html>