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

	<%
	List<Course> list = (List<Course>) request.getAttribute("courseList");
	%>
	<h3>Course List</h3>

	<form action="<%=request.getContextPath()%>/searchCourse" method="get">
		<input type="text" name="searchQuery"
			placeholder="Enter Course Id or name">
		<button type="submit">Search</button>
		<a href="<%=request.getContextPath()%>/searchCourse"><button>Reset</button></a>
	</form>
	<br>
	<form action="<%=request.getContextPath()%>/viewCourses" method="get">
		<input type="number" name="pageSize" placeholder="Enter number.." min="1"
			value="<%=request.getAttribute("pageSize") != null ? request.getAttribute("pageSize") : 10%>">
		<button type="submit">Set Size</button>
	</form>
	<br>
	<br>

	<table border="1">

		<tr>
			<th>Course ID</th>
			<th>Course Name</th>
			<th>Teacher Name</th>
			<th>Update Course</th>
			<th>Delete Course</th>
		</tr>

		<%
		for (Course c : list) {
		%>

		<tr>
			<td><%=c.getCourseId()%></td>
			<td><%=c.getCourseName()%></td>
			<td><%= c.getTeacherName() %></td>
			<td><a href="editCourse?courseId=<%=c.getCourseId()%>"><button>Edit</button></a></td>
			<td><a href="deleteCourse?courseId=<%=c.getCourseId()%>"><button>Delete</button></a></td>
		</tr>
		<%
		}
		%>
	</table>
	<br>
	<br>
	<div class="pagination">
		<% int totalPages = (int) request.getAttribute("totalPages");
		int currentPage = (int) request.getAttribute("currentPage");
		int pageSize = (int) request.getAttribute("pageSize");
		
		if(currentPage > 1){
	%>
		<a
			href="<%= request.getContextPath()%>/viewCourses?page=<%= currentPage - 1%>&pageSize=<%= pageSize%>">&laquo;
			Previous</a>
		<% } 
		String searchQuery = (String) request.getAttribute("searchQuery");
		String baseUrl = (searchQuery != null) ? "searchCourse" : "viewCourses";
		String searchParam = (searchQuery != null ? "&searchQuery=" + searchQuery : "");
	for(int i=1; i<=totalPages ;i++){
		%>
		<a
			href="<%=baseUrl%>?page=<%=i%>&pageSize=<%=pageSize%><%=searchParam%>"
			class="<%= (i == currentPage) ? "active" : "" %>"> <%=i%>
		</a>
		<% } 
           
           if(currentPage < totalPages){%>
		<a
			href="<%=baseUrl%>?page=<%=currentPage + 1%>&pageSize=<%=pageSize%><%=searchParam%>">Next
			&raquo;</a>
		<%} %>

	</div>
	<a href="<%=request.getContextPath()%>/DashBoard/adminDashboard.jsp"><button>Back</button></a>
</body>
</html>