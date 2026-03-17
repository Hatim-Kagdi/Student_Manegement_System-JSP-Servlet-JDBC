<%@page import="in.keen.Model.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Course for Marks Page</title>
</head>
<body>

<% List<Course> list = (List<Course>) request.getAttribute("courseList"); %>

<h3>Select Course for Grading</h3>

<form action="<%= request.getContextPath()%>/getStudentsForMarks"  method="get">

<label for="courseId">Choose a Course :</label>
<select name="courseId" id="courseId" required>

<option value="" selected disabled>--Select a Course--</option><br>

<% for(Course c : list){
	if(list != null && !list.isEmpty()){%>
	
	<option value="<%= c.getCourseId() %>"> <%= c.getCourseName() %> (Id : <%= c.getCourseId() %>)</option>
	
<%} }%>

</select><br>

<button type="submit"> View Students </button><br>
</form>

<br><br>
<a href="<%= request.getContextPath()%>/DashBoard/teacherDashboard.jsp"><button>Back</button></a>
</body>
</html>