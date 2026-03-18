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
<%List<Enrollment> eList = (List<Enrollment>) request.getAttribute("enrollmentList");%>
<h3>Enrollment List</h3>
<form action="<%= request.getContextPath()%>/searchEnrollment" method="get">
<input type="text" name="searchQuery" placeholder="Enter id or name">
<button type="submit">Search</button>
<a href="<%= request.getContextPath() %>/searchEnrollment"><button>Reset</button></a>
</form><br>
<table border = "1">
<thead>
<tr>
<th>Id</th>
<th>Student Name</th>
<th>Course Name </th>
<th> Teacher Name </th>
<th>Edit </th>
<th>DELETE</th>
</tr>
</thead>
<tbody>
<% for(Enrollment e : eList){ %>
<tr>
<td><%= e.getEnrollmentId() %></td>
<td> <%= e.getStudentName() %></td>
<td> <%= e.getCourseName() %></td>
<td> <%= e.getTeacherName() %></td>
<td><a href="editEnrollment?enrollmentId=<%= e.getEnrollmentId()%>"><button>Edit</button></a></td>
<td><a href="deleteEnrollment?enrollmentId=<%= e.getEnrollmentId()%>"><button>Delete</button></a></td>
</tr>
<%} %>
</tbody>
</table><br><br>
<a href="<%= request.getContextPath() %>/DashBoard/adminDashboard.jsp"><button>Back</button></a>
</body>
</html>