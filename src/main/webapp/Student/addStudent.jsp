<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Student Page</title>
</head>
<body>
	<h3>Add Student Form</h3>
	<form action="<%=request.getContextPath()%>/addStudent" method="post">

		Name: <input type="text" name="name" required><br> <br>
		Email: <input type="email" name="email" required><br> <br>
		Department: <select name="department">
			<option>Select Department</option>
			<option>Computer Science</option>
			<option>Civil</option>
			<option>Mechanical</option>
			<option>Automation and Robotics</option>
			<option>Artificial Intelligence</option>
			<option>Electrical</option>
		</select><br> <br> Year: <input type="number" name="year" required><br>
		<br> Admission Date: <input type="date" name="admissionDate"><br>
		<br>

		<button type="submit">Add Student</button>

	</form>
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/DashBoard/adminDashboard.jsp"><button>Back</button></a>
</body>
</html>