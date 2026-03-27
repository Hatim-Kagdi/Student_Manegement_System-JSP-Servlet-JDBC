<%@page import="in.keen.Model.Teacher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Teacher Details</title>
</head>
<body>
	<%
	Teacher teacher = (Teacher) request.getAttribute("teacher");
	%>
	<h2>Edit Teacher</h2>

	<form action="updateTeacher" method="post">

		<input type="hidden" name="id" value="<%=teacher.getTeacherId()%>">

		Name: <input type="text" name="name"
			value="<%=teacher.getTeacherName()%>"><br> <br>
		Email: <input type="email" name="email"
			value="<%=teacher.getTeacherEmail()%>"><br> <br>
		Department: <select name="department">
			<%
			String currentDept = teacher.getTeacherDepartment();
			String[] depts = { "Computer Science", "Civil", "Mechanical", "Automation and Robotics", "Artificial Intelligence",
					"Electrical" };
			for (String d : depts) {
			%>
			<option value="<%=d%>" <%=d.equals(currentDept) ? "selected" : ""%>>
				<%=d%>
			</option>
			<%
			}
			%>
		</select> <br> <br>

		<button type="submit">Update Teacher</button>

	</form>
	<br>
	<br>
	<a href="viewTeachers"><button>Back</button></a>
</body>
</html>