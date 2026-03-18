<%@page import="in.keen.Model.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Teacher List</title>
</head>
<body>

<% List<Teacher> teacher = (List<Teacher>) request.getAttribute("teacherList"); %>

<h2>Teacher List</h2>

<form action="<%= request.getContextPath()%>/searchTeacher" method="get">
<input type="text" name="searchQuery" placeholder="Search by name or id...">
<button type="submit"> Search</button>
<a href="<%= request.getContextPath() %>/searchTeacher"><button>Reset</button></a>
</form><br>

<table border="1">
<tr>
<th>ID</th>
<th>Name</th>
<th>Email</th>
<th>Department</th>
<th>UPDATE Teacher</th>
<th>DELETE Teacher</th>
</tr>

<% for(Teacher t : teacher){ %>
<tr>
<td><%=t.getTeacherId()%></td>
<td><%=t.getTeacherName()%></td>
<td><%=t.getTeacherEmail()%></td>
<td><%=t.getTeacherDepartment()%></td>
<td><a href="editTeacher?teacherId=<%=t.getTeacherId()%>"><button>EDIT</button></a></td>
<td><a href="deleteTeacher?teacherId=<%=t.getTeacherId()%>"><button>DELETE</button></a></td>
</tr>
<%} %>
</table><br>
<a href="<%= request.getContextPath() %>/DashBoard/adminDashboard.jsp"><button>Back</button></a>
</body>
</html>