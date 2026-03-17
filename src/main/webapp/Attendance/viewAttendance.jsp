<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Attendance Page</title>
</head>
<body>

<% List<Map<String,String>> list = (List<Map<String,String>>) request.getAttribute("attendanceList"); %>

<h2>Attendance History</h2>

<table border="1" cellpadding= "10">
<thead>
<tr>
<th>Course Name</th>
<th>Date </th>
<th> Status </th>
</tr>
</thead>

<tbody>
<% if(list != null && !list.isEmpty()){ 
	for(Map<String, String> map : list){
%>
<tr>
<td><%= map.get("courseName") %></td>
<td> <%= map.get("date") %> </td>
<td> <%= map.get("status") %> </td>
</tr>
<%} } else {%>
<tr><td colspan="3">No attendance records found.</td></tr>
<%} %>

</tbody>
</table><br>

<a href="<%= request.getContextPath() %>/DashBoard/studentDashboard.jsp"><button>Back to Dashboard</button></a>

</body>
</html>