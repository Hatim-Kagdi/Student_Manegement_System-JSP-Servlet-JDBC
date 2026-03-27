<%@page import="in.keen.Model.Teacher"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Course Page</title>
</head>
<body>

	<%
	List<Teacher> list = (List<Teacher>) request.getAttribute("teacherList");
	%>

	<h3>Add Course Form</h3>
	<form action="<%=request.getContextPath()%>/addCourse" method="post">

		Course Name: <select name="name" required>
			<option value="" selected disabled>-- Select a Subject --</option>
			<option value="Data Structures & Algorithms">Data Structures
				& Algorithms (Computer Science)</option>
			<option value="Fluid Mechanics">Fluid Mechanics (Civil)</option>
			<option value="Machine Design">Machine Design (Mechanical)</option>
			<option value="Machine Learning">Machine Learning
				(Artificial Intelligence)</option>
			<option value="Microcontrollers">Microcontrollers
				(Automation & Robotics)</option>
			<option value="Power Systems">Power Systems (Electrical)</option>
		</select> <br>
		<br> Teacher : <select name="teacherId">

			<%
			for (Teacher t : list) {
			%>

			<option value="<%=t.getTeacherId()%>"><%=t.getTeacherName()%></option>

			<%
			}
			%>
		</select><br>
		<br>

		<button type="submit">Add Course</button>

	</form>
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/DashBoard/adminDashboard.jsp"><button>Back</button></a>
</body>
</html>