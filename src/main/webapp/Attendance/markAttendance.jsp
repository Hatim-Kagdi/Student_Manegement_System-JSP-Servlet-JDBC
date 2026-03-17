<%@page import="in.keen.Model.Enrollment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mark Student Attendance </title>
</head>
<body>
	<% List<Enrollment> studentList = (List<Enrollment>) request.getAttribute("studentList"); 
		int courseId = (int) request.getAttribute("selectedCourseId");
		
	%>
	
	<h3>Attendance Sheet</h3>
	<p> <strong>CourseId : <%= courseId %></strong> | Date : <strong><%= new java.sql.Date(System.currentTimeMillis()) %></strong></p>
	
	<form action="<%= request.getContextPath()%>/saveAttendance" method="post">
	
	<input type="hidden" name="courseId" value="<%= courseId %>"> 
	
	Select Date: <input type="date" name="attendanceDate" value="<%= new java.sql.Date(System.currentTimeMillis()) %>" required>
    <br><br>
    
    <table border="1">
        <thead>
            <tr>
                <th>Student ID</th>
                <th>Student Name</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <% if(studentList != null && !studentList.isEmpty()) { 
                for(Enrollment e : studentList) { %>
                <tr>
                    <td><%= e.getStudentId() %></td>
                    <td><%= e.getStudentName() %></td>
                    <td>
                        <input type="radio" name="status_<%= e.getStudentId() %>" value="Present" checked> P
                        <input type="radio" name="status_<%= e.getStudentId() %>" value="Absent"> A
                        
                        <input type="hidden" name="studentIds" value="<%= e.getStudentId() %>">
                    </td>
                </tr>
            <% } 
            } else { %>
                <tr><td colspan="3">No students enrolled in this course.</td></tr>
            <% } %>
        </tbody>
    </table>
	<br>
    <button type="submit">Submit Attendance</button>
	</form>
	<br>
	<a href="<%= request.getContextPath() %>/DashBoard/teacherDashboard.jsp"><button>Cancel</button></a>
</body> 
</html>