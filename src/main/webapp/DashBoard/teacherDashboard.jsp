<%@page import="in.keen.Model.User"%>

<%
User user = (User) session.getAttribute("session_user");

if(user == null){
    response.sendRedirect(request.getContextPath() +"/login.html");
    return;
}

if(!"teacher".equals(user.getUserRole())){
	response.sendRedirect(request.getContextPath() + "/login.html");
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Teacher Dashboard</title>
</head>

<body>

<h1>Teacher Dashboard</h1>

<h3>Welcome <%= user.getUserName() %></h3>

<hr>
<ul>
<li>
<a href="<%= request.getContextPath()%>/viewTeacherProfile">View My Profile</a>
</li><br>
<li>
<a href="<%= request.getContextPath()%>/teachersCourses">View My Courses</a>
</li><br>
<li>
<a href="<%= request.getContextPath()%>/markAttendance">Mark Attendance</a>
</li>
<br>
<li>
<a href="<%= request.getContextPath()%>/markGrades">Grade Students</a>
</li>
<br>
</ul>

<br><br>

<a href="<%= request.getContextPath() %>/logoutUser"><button>Logout</button></a>

</body>
</html>