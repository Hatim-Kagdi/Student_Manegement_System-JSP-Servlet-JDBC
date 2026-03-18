<%@page import="in.keen.Model.User"%>

<%
User user = (User) session.getAttribute("session_user");

if(user == null){
    response.sendRedirect(request.getContextPath()+"/login.html");
    return;
}

if(!"student".equals(user.getUserRole())){
    response.sendRedirect(request.getContextPath()+"/login.html");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Student DashBoard</title>
</head>

<body>

<h1>Student DashBoard</h1>

<h3>Welcome <%= user.getUserName() %></h3>

<hr>
<ul>
<li>
<a href="<%= request.getContextPath() %>/myCourses">View My Courses</a>
</li> <br>
<li>
<a href="<%= request.getContextPath() %>/viewStudentAttendance">View Attendance</a>
</li> <br>
<li>
<a href="<%= request.getContextPath() %>/viewStudentMarks">View Marks</a>
</li> <br>
</ul>

<a href="<%= request.getContextPath() %>/logoutUser"><button>Logout</button></a>

</body>
</html>