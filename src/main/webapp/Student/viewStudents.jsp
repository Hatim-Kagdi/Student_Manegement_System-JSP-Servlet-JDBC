<%@page import="java.util.List"%>
<%@page import="in.keen.Model.Student"%>

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

<%
List<Student> students = (List<Student>) request.getAttribute("studentList");
%>

<h2>Student List</h2>

<form action="<%=request.getContextPath()%>/searchStudents" method="get">
	<input type="text" name="searchQuery"
		placeholder="Search by name or ID...">
	<button type="submit">Search</button>
	<a href="<%=request.getContextPath()%>/searchStudents"><button>Reset</button></a>
	<br> <br>
</form>

<form action="<%=request.getContextPath()%>/viewStudents" method="get">
	<input type="number" name="pageSize"
		placeholder="Enter number of records you want to see" min="1"
		value="<%=request.getAttribute("pageSize") != null ? request.getAttribute("pageSize") : 10%>">
	<button type="submit">Set Size</button>
</form>

<table border="1">

	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Email</th>
		<th>Department</th>
		<th>Year</th>
		<th>Admission Date</th>
		<th>UPDATE</th>
		<th>DELETE</th>
	</tr>

	<%
	for (Student s : students) {
	%>

	<tr>
		<td><%=s.getStudentId()%></td>
		<td><%=s.getStudentName()%></td>
		<td><%=s.getStudentEmail()%></td>
		<td><%=s.getStudentDepartment()%></td>
		<td><%=s.getStudentYear()%></td>
		<td><%=s.getStudentAddmissionYear()%></td>
		<td><a href="editStudent?studentId=<%=s.getStudentId()%>"><button>EDIT</button></a></td>
		<td><a href="deleteStudent?studentId=<%=s.getStudentId()%>">
				<button>Delete</button>
		</a></td>
	</tr>

	<%
	}
	%>

</table>
<br>
<br>

<div class="pagination">
	<%
	int totalPages = (int) request.getAttribute("totalPages");
	int currentPage = (int) request.getAttribute("currentPage");

	if (currentPage > 1) {
	%>
	<a
		href="viewStudents?page=<%=currentPage - 1%>&pageSize=<%=request.getAttribute("pageSize")%>">&laquo;
		Previous</a>
	<%
	}
	String searchedQuery = (String) request.getAttribute("searchQuery");
	for (int i = 1; i <= totalPages; i++) {
	%>
	<a
		href="<%=request.getContextPath()%>/
		<%=(searchedQuery != null ? "searchStudents" : "viewStudents")%>
		?page=<%=i%>&pageSize=<%=request.getAttribute("pageSize")%> 
		<%=(searchedQuery != null ? "&searchQuery=" + searchedQuery : "")%>"
		class="<%=(i == currentPage) ? "active" : ""%>"> <%=i%>
	</a>
	<%
	}
	if (currentPage < totalPages) {
	%>
	<a
		href="viewStudents?page=<%=currentPage + 1%>&pageSize=<%=request.getAttribute("pageSize")%>">Next
		&raquo;</a>
	<%
	}
	%>
</div>
<br>
<br>

<a href="<%=request.getContextPath()%>/DashBoard/adminDashboard.jsp"><button>Back</button></a>