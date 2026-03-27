<%@page import="in.keen.Model.Teacher"%>
<%@page import="java.util.List"%>
<%@page import="in.keen.Model.Course"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Course Details</title>
</head>
<body>
	<%
	Course course = (Course) request.getAttribute("course");
	List<Teacher> teacher = (List<Teacher>) request.getAttribute("teacherList");
	%>
	<form action="<%=request.getContextPath()%>/updateCourse"
		method="post">

		<input type="hidden" name="courseId"
			value="<%=course.getCourseId()%>"><br>
		<br> Course Name: <select name="name" required>
			<option value="" disabled
				<%=(course.getCourseName() == null) ? "selected" : ""%>>--
				Select a Subject --</option>

			<option value="Data Structures & Algorithms"
				<%="Data Structures & Algorithms".equals(course.getCourseName()) ? "selected" : ""%>>
				Data Structures & Algorithms (CS)</option>

			<option value="Fluid Mechanics"
				<%="Fluid Mechanics".equals(course.getCourseName()) ? "selected" : ""%>>
				Fluid Mechanics (Civil)</option>

			<option value="Machine Design"
				<%="Machine Design".equals(course.getCourseName()) ? "selected" : ""%>>
				Machine Design (Mechanical)</option>

			<option value="Machine Learning"
				<%="Machine Learning".equals(course.getCourseName()) ? "selected" : ""%>>
				Machine Learning (AI)</option>

			<option value="Microcontrollers"
				<%="Microcontrollers".equals(course.getCourseName()) ? "selected" : ""%>>
				Microcontrollers (Robotics)</option>

			<option value="Power Systems"
				<%="Power Systems".equals(course.getCourseName()) ? "selected" : ""%>>
				Power Systems (Electrical)</option>
		</select> <br>
		<br> Teacher Id : <select name="teacherId"
			value="<%=course.getTeacherId()%>">
			<%
			for (Teacher t : teacher) {
			%>
			<option value="<%=t.getTeacherId()%>"
				<%=(t.getTeacherId() == course.getTeacherId()) ? "selected" : ""%>>
				<%=t.getTeacherName()%></option>
			<%
			}
			%>
		</select><br>
		<br>

		<button value="submit">Update</button>

	</form>
	<br>
	<br>
	<a href="viewCourses"><button>Back</button></a>
</body>
</html>