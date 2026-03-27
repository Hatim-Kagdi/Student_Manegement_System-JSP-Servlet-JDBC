<%@page import="in.keen.Model.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher List</title>
<style>
.pagination a {
	padding: 8px 16px;
	text-decoration: none;
	border: 1px solid #ddd;
	color: black;
	border-radius: 4px;
	margin: 0 4px;
}

.pagination a.active {
	background-color: #007bff;
	color: white;
	border: 1px solid #007bff;
}

.pagination a:hover:not(.active) {
	background-color: #f1f1f1;
}
</style>
</head>
<body>

	<%
	List<Teacher> teacher = (List<Teacher>) request.getAttribute("teacherList");
	%>

	<h2>Teacher List</h2>

	<form action="<%=request.getContextPath()%>/searchTeacher" method="get">
		<input type="text" name="searchQuery"
			placeholder="Search by Teacher name or id...">
		<button type="submit">Search</button>
		<a href="<%=request.getContextPath()%>/searchTeacher"><button>Reset</button></a>
	</form>
	<br>

	<form action="<%=request.getContextPath()%>/viewTeachers" method="get">
		<input type="number" name="pageSize" placeholder="Enter number..."
			min="1"
			value="<%=request.getAttribute("pageSize") != null ? request.getAttribute("pageSize") : 10%>">
		<button type="submit">Set Size</button>
	</form>
	<br>
	<br>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Email</th>
			<th>Department</th>
			<th>UPDATE Teacher</th>
			<th>DELETE Teacher</th>
		</tr>

		<%
		for (Teacher t : teacher) {
		%>
		<tr>
			<td><%=t.getTeacherId()%></td>
			<td><%=t.getTeacherName()%></td>
			<td><%=t.getTeacherEmail()%></td>
			<td><%=t.getTeacherDepartment()%></td>
			<td><a href="editTeacher?teacherId=<%=t.getTeacherId()%>"><button>EDIT</button></a></td>
			<td><a href="deleteTeacher?teacherId=<%=t.getTeacherId()%>"><button>DELETE</button></a></td>
		</tr>
		<%
		}
		%>
	</table>
	<br>

	<div class="pagination">
		<%
		int totalPages = (int) request.getAttribute("totalPages");
		int currentPage = (int) request.getAttribute("currentPage");

		if (currentPage > 1) {
		%>
		<a
			href="<%=request.getContextPath()%>/viewTeachers?page=<%=(currentPage - 1)%>&pageSize=<%=request.getAttribute("pageSize")%>">&laquo;
			Previous</a>
		<%
		}

		for (int i = 1; i <= totalPages; i++) {
		%>
		<a
			href="<%=request.getContextPath()%>/
			<%=(request.getAttribute("searchQuery") != null) ? "searchTeacher" : "viewTeachers"%>
			?page=<%=i%>&pageSize=<%=request.getAttribute("pageSize")%>
			<%=(request.getAttribute("searchQuery") != null) ? "&searchQuery=" + request.getAttribute("searchQuery") : ""%>"
			class="<%=(i == currentPage) ? "active" : ""%>"> <%=i%>
		</a>
		<%
		}
		if (currentPage < totalPages) {
		%>
		<a
			href="<%=request.getContextPath()%>/viewTeachers?page=<%=currentPage + 1%>&pageSize=<%=request.getAttribute("pageSize")%>">Next
			&raquo;</a>
		<%
		}
		%>

	</div>
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/DashBoard/adminDashboard.jsp"><button>Back</button></a>
</body>
</html>