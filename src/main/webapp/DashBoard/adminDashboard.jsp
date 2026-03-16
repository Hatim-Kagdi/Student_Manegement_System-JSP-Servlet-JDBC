<%@page import="in.keen.Model.User"%>

<%
User user = (User) session.getAttribute("session_user");

if(user == null){
    response.sendRedirect(request.getContextPath() + "/login.html");
    return;
}

if(!"admin".equals(user.getUserRole())){
    response.sendRedirect(request.getContextPath() +"/login.html");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
<title>Admin Dashboard</title>
</head>

<body>

<h1>Admin Dashboard</h1>

<h3>Welcome <%= user.getUserName() %></h3>

<hr>

<h3>Management Panel</h3>

<ul>
<li><a href="<%= request.getContextPath() %>/Student/addStudent.jsp">Add Student</a></li>
<li><a href="<%= request.getContextPath() %>/viewStudents">View Students</a></li>

<li><a href="<%= request.getContextPath() %>/Teacher/addTeacher.jsp">Add Teacher</a></li>
<li><a href="<%= request.getContextPath() %>/viewTeachers">View Teachers</a></li>

<li><a href="<%= request.getContextPath() %>/addCoursePage">Add Course</a></li>
<li><a href="<%= request.getContextPath() %>/viewCourses">View Courses</a></li>

<li><a href="<%= request.getContextPath() %>/enrollStudentPage">Enroll Student</a></li>
<li><a href="<%= request.getContextPath() %>/viewEnrollments">View Enrollments</a></li>

</ul>

<br>

<a href="<%= request.getContextPath() %>/logoutUser"><button>Logout</button></a>

</body>
</html>