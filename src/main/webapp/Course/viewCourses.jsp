<%@page import="in.keen.Model.Teacher"%>
<%@page import="in.keen.Model.Course"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Courses</title>
</head>
<body>

<% List<Course> list = (List<Course>) request.getAttribute("courseList"); 
List<Teacher> tList = (List<Teacher>) request.getAttribute("teacherList");
%>
<h3>Course List</h3>
<table border="1">

<tr>
<th>Course ID</th>
<th>Course Name</th>
<th>Teacher Name </th>
<th>Update Course</th>
<th>Delete Course</th>
</tr>

<%for(Course c : list){ %>

<tr>
<td><%= c.getCourseId() %></td>
<td><%= c.getCourseName() %></td>
<td><% for(Teacher t : tList){
	if(t.getTeacherId() == c.getTeacherId()){
		out.print(t.getTeacherName());
	}
}
%>
</td>
<td><a href="editCourse?courseId=<%=c.getCourseId()%>"><button>EDIT</button></a></td>
<td><a href="deleteCourse?courseId=<%=c.getCourseId()%>"><button>Delete</button></a></td>
</tr>
<%} %>
</table>
<br><br>
<a href="<%= request.getContextPath() %>/DashBoard/adminDashboard.jsp"><button>Back</button></a>
</body>
</html>