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
	<hr>
	<form action="<%=request.getContextPath()%>/addCourse" method="post">

		Course Name: <select name="name" id="courseSelect"
			onchange="filterTeachers()" required>
			<option value="" selected disabled>-- Select a Subject --</option>
			<option value="Data Structures & Algorithms"
				data-dept="Computer Science">Data Structures & Algorithms
				(Computer Science)</option>
			<option value="Fluid Mechanics" data-dept="Civil">Fluid
				Mechanics (Civil)</option>
			<option value="Machine Design" data-dept="Mechanical">Machine
				Design (Mechanical)</option>
			<option value="Machine Learning" data-dept="Artificial Intelligence">Machine
				Learning (Artificial Intelligence)</option>
			<option value="Microcontrollers" data-dept="Automation & Robotics">MicroControllers
				(Automation & Robotics)</option>
			<option value="Power Systems" data-dept="Electrical">Power
				Systems (Electrical)</option>
		</select> <br> <br> Teacher : <select name="teacherId"
			id="teacherSelect">
			<%
			for (Teacher t : list) {
			%>
			<option value="" selected disabled>-- Select a Teacher --</option>
			<option value="<%=t.getTeacherId()%>"
				data-dept="<%=t.getTeacherDepartment()%>">
				<%=t.getTeacherName()%> :
				<%=t.getTeacherDepartment()%></option>
			<%
			}
			%>
		</select> <br> <br>
		<button type="submit">Add Course</button>
	</form>
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/DashBoard/adminDashboard.jsp"><button>Back</button></a>
</body>
<script>
	function filterTeachers() {
		const courseSelect = document.getElementById('courseSelect');
		const teacherSelect = document.getElementById('teacherSelect');

		const selectedOption = courseSelect.options[courseSelect.selectedIndex];
		const requiredDept = selectedOption.getAttribute('data-dept');

		teacherSelect.value = "";

		const teacherOption = teacherSelect.options;

		for (let i = 1; i < teacherOption.length; i++) {
			const teacherDept = teacherOption[i].getAttribute('data-dept');

			if (teacherDept == requiredDept) {
				teacherOption[i].style.display = "block";
			} else {
				teacherOption[i].style.display = "none";
			}
		}

	}
</script>
</html>