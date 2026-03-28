<%@page import="in.keen.Model.Enrollment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View All Enrollments</title>
</head>
<body>
	<%
	List<Enrollment> eList = (List<Enrollment>) request.getAttribute("enrollmentList");
	%>
	<h3>Enrollment List</h3>
	<hr>
	<form action="<%=request.getContextPath()%>/searchEnrollment"
		method="get">
		<input type="text" name="searchQuery" placeholder="Enter id or name..">
		<button type="submit">Search</button>
		<a href="<%=request.getContextPath()%>/searchEnrollment"><button>Reset</button></a>
	</form>
	<br>
	<form action="<%=request.getContextPath()%>/viewEnrollments"
		method="get">
		<input type="number" name="pageSize" placeholder="Enter number.."
			min="1"
			value="<%=request.getAttribute("pageSize") != null ? request.getAttribute("pageSize") : 10%>">
		<button type="submit">Set Size</button>
	</form>
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Student Name</th>
				<th>Course Name</th>
				<th>Teacher Name</th>
				<th>EDIT</th>
				<th>DELETE</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Enrollment e : eList) {
			%>
			<tr>
				<td><%=e.getEnrollmentId()%></td>
				<td><%=e.getStudentName()%></td>
				<td><%=e.getCourseName()%></td>
				<td><%=e.getTeacherName()%></td>
				<td><a
					href="editEnrollment?enrollmentId=<%=e.getEnrollmentId()%>"><button>Edit</button></a></td>
				<td><a
					href="deleteEnrollment?enrollmentId=<%=e.getEnrollmentId()%>"><button>Delete</button></a></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<br>
	<br>
	<div class="pagination">
		<%
		int currentPage = (int) request.getAttribute("currentPage");
		int totalPages = (int) request.getAttribute("totalPages");
		int pageSize = (int) request.getAttribute("pageSize");
		String searchQuery = (String) request.getAttribute("searchQuery");

		String baseUrl = (searchQuery != null) ? "searchEnrollment" : "viewEnrollments";
		String searchUrl = (searchQuery != null) ? "&searchQuery=" + searchQuery : "";

		if (currentPage > 1) {
		%>
		<a
			href="<%=request.getContextPath()%>/<%=baseUrl%>?page=<%=currentPage - 1%>
			&pageSize=<%=pageSize%><%=searchUrl%>">
			&laquo; Previous </a>
		<%
		}
		for (int i = 1; i <= totalPages; i++) {
		%>
		<a
			href="<%=request.getContextPath()%>/<%=baseUrl%>?page=<%=i%>&pageSize=<%=pageSize%><%=searchUrl%>"
			class=<%=(i == currentPage) ? "active" : ""%>><%=i%></a>
		<%
		}
		if (currentPage < totalPages) {
		%>
		<a
			href="<%=request.getContextPath()%>/<%=baseUrl%>?page=<%=currentPage + 1%>
			&pageSize=<%=pageSize%><%=searchUrl%>">
			Next &raquo;</a>
		<%
		}
		%>
	</div>
	<a href="<%=request.getContextPath()%>/DashBoard/adminDashboard.jsp"><button>Back</button></a>
</body>
</html>