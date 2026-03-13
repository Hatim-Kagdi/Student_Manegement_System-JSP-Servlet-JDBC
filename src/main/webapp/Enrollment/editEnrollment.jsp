<%@page import="in.keen.Model.Course"%>
<%@page import="in.keen.Model.Student"%>
<%@page import="java.util.List"%>
<%@page import="in.keen.Model.Enrollment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Enrollment Form</title>
</head>
<body>
<% Enrollment enrollment= (Enrollment) request.getAttribute("enrollment");
List<Student> studentList = (List<Student>) request.getAttribute("student");
List<Course> courseList = (List<Course>) request.getAttribute("course");
%>

<h3>Edit Enrollment Form</h3>
<form action="<%= request.getContextPath()%>/updateEnrollment" method="post">
<input type="hidden" name="enrollmentId" value="<%= enrollment.getEnrollmentId() %>"><br><br>

Student Name :
<select name="studentId" required>

<option value="">--Select Student--</option>

<% for(Student s : studentList){ %>

<option value="<%= s.getStudentId() %>"
<%= s.getStudentId() == enrollment.getStudentId() ? "selected" : "" %>>

<%= s.getStudentName() %>

</option>

<% } %>

</select><br><br>
Course Name :
<select name="courseId" required>

<option value="">--Select Course--</option>

<% for(Course c : courseList){ %>

<option value="<%= c.getCourseId() %>"
<%= c.getCourseId() == enrollment.getCourseId() ? "selected" : "" %>>

<%= c.getCourseName() %>

</option>

<% } %>

</select><br><br>

<button value="submit">UPDATE</button>
</form>

</body>
</html>