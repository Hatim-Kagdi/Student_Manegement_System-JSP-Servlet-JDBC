<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="in.keen.Model.Student"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student Details</title>
</head>
<body>

	<%
	Student student = (Student) request.getAttribute("student");
	%>

	<h2>Edit Student</h2>

	<form action="updateStudent" method="post">

		<input type="hidden" name="id" value="<%=student.getStudentId()%>">

		Name: <input type="text" name="name"
			value="<%=student.getStudentName()%>"><br> <br>
		Email: <input type="email" name="email"
			value="<%=student.getStudentEmail()%>"><br> <br>
		Department: <select name="department">
			<option value="Computer Science"
				<%="Computer Science".equals(student.getStudentDepartment()) ? "selected" : ""%>>Computer
				Science</option>
			<option value="Civil"
				<%="Civil".equals(student.getStudentDepartment()) ? "selected" : ""%>>Civil</option>
			<option value="Mechanical"
				<%="Mechanical".equals(student.getStudentDepartment()) ? "selected" : ""%>>Mechanical</option>
			<option value="Automation and Robotics"
				<%="Automation and Robotics".equals(student.getStudentDepartment()) ? "selected" : ""%>>Automation
				and Robotics</option>
			<option value="Artificial Intelligence"
				<%="Artificial Intelligence".equals(student.getStudentDepartment()) ? "selected" : ""%>>Artificial
				Intelligence</option>
			<option value="Electrical"
				<%="Electrical".equals(student.getStudentDepartment()) ? "selected" : ""%>>Electrical</option>
		</select> <br> <br> Year: <input type="number" name="year"
			value="<%=student.getStudentYear()%>"><br> <br>
		Addmission Date: <input type="date" name="addmissionDate"
			value="<%=student.getStudentAddmissionYear()%>"><br> <br>

		<button type="submit">Update Student</button>

	</form>
	<br>
	<br>
	<a href="viewStudents"><button>Back</button></a>
</body>
</html>