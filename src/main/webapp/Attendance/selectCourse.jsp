<%@page import="java.util.List"%>
<%@page import="in.keen.Model.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Course Page</title>
</head>
<body>
<% List<Course> list = (List<Course>) request.getAttribute("teacherCourseList"); %>

<h3>Select Course to Mark Attendance</h3>

<form action="<%= request.getContextPath() %>/getStudentsForAttendance" method="get">
<label for="courseId">Choose a Course </label>
<select name="courseId" id="courseId" required>

<option value="" selected disabled> --Select Course-- </option>

<% if(list != null){ 
for(Course c : list){ %>

<option value="<%= c.getCourseId()%>">
<%= c.getCourseName() %> (ID : <%= c.getCourseId() %>)
</option>

<%} }%>

</select><br>

<button type="submit"> View Student List </button>

</form>

<a href="<%= request.getContextPath()%>/DashBoard/teacherDashboard.jsp"><button> Back </button></a>

</body>
</html> 