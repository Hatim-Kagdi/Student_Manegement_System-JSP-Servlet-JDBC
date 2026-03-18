<%@page import="java.util.List"%>
<%@page import="in.keen.Model.Student"%>

<%
List<Student> students = (List<Student>) request.getAttribute("studentList");
%>

<h2>Student List</h2>

<form action="<%= request.getContextPath() %>/searchStudents" method="get">
    <input type="text" name="searchQuery" placeholder="Search by name or ID...">
    <button type="submit">Search</button>
    <a href="<%= request.getContextPath() %>/searchStudents"><button>Reset</button></a>
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
for(Student s : students){
%>

<tr>
<td><%= s.getStudentId() %></td>
<td><%= s.getStudentName() %></td>
<td><%= s.getStudentEmail() %></td>
<td><%= s.getStudentDepartment() %></td>
<td><%= s.getStudentYear() %></td>
<td><%= s.getStudentAddmissionYear() %></td>
<td><a href="editStudent?studentId=<%=s.getStudentId()%>"><button>EDIT</button></a></td>
<td><a href="deleteStudent?studentId=<%= s.getStudentId() %>">
    <button>Delete</button>
</a></td>
</tr>

<%
}
%>

</table><br><br>

<a href="<%= request.getContextPath() %>/DashBoard/adminDashboard.jsp"><button>Back</button></a>