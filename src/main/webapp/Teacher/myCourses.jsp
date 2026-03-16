<%@page import="in.keen.Model.User"%>
<%@page import="in.keen.Model.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher's Courses</title>
</head>
<body>

<%  User user = (User) session.getAttribute("session_user");
List<Course> list = (List<Course>) request.getAttribute("teacherCourseList"); 

if(user == null){
	response.sendRedirect(request.getContextPath()+"/login.html");
}

%>

<h3>Courses Table </h3>

<table border = "1">

<tr>
<th>Course Id</th>
<th>Course Name</th>
<th>View Students </th>
</tr>

<% for(Course c : list){ %>
<tr>
<td> <%= c.getCourseId() %></td>
<td> <%= c.getCourseName() %></td>
<td> <a href="viewCourseStudents?courseId=<%= c.getCourseId() %>"> <button> View Students </button></a></td>
</tr>
<%} %>
</table>
<br><br>
<a href="<%= request.getContextPath()%>/DashBoard/teacherDashboard.jsp"><button>Back</button></a>
</body>
</html>