<%@page import="in.keen.Model.Course"%>
<%@page import="in.keen.Model.Student"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enroll Student in Course</title>
</head>
<body>

<% List<Student> sList = (List<Student>) request.getAttribute("studentList"); 
List<Course> cList = (List<Course>) request.getAttribute("courseList");
%>

<h3>Student Course Enrollment Form</h3>

<form action="<%= request.getContextPath()%>/addEnrollment" method="post">

Student Name : <select name="studentId">
<option value=""><--Select Option--></option>
<% for(Student s : sList){ %>
<option value="<%= s.getStudentId()%>"> <%= s.getStudentName() %> </option>
<%} %>
</select><br><br>

Course Name : <select name="courseId">
<option value=""><--Select Option--></option>
<% for(Course c : cList){ %>
<option value="<%= c.getCourseId()%>"> <%= c.getCourseName() %> </option>
<%} %>
</select>

<button type = "submit">Add Enrollment</button>
</form>

</body>
</html>