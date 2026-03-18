<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Marks View Page</title>
</head>
<body>
<% List<Map<String, Object>> marksList = (List<Map<String,Object>>) request.getAttribute("marksList"); %>

<table border="1" cellspacing="5">

<thead>
<tr>
<th>Course Name</th>
<th> Marks Obtained </th>
<th> Result </th>
</tr>
</thead>

<% if(marksList != null && !marksList.isEmpty()){ 
	for(Map<String, Object> map : marksList){
		int score = (int) map.get("marks");
%>
<tr>
<td> <%= map.get("courseName") %> </td>
<td> <%= score %></td>
<td>
	<b style= "color <%= (score >= 40) ? "green" : "red" %>">
	<%= (score >= 40) ? "PASS" : "FAIL" %>
	</b>
</td>
</tr>
<%} } else {%>
	<tr><td colspan="3"> MARKS NOT ASSIGNED YET! </td></tr>
<% } %>
</table>
<br><br>

<a href="<%= request.getContextPath() %>/DashBoard/studentDashboard.jsp"><button>BACK</button></a>

</body>
</html>