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

	<%
	List<Student> sList = (List<Student>) request.getAttribute("studentList");
	List<Course> cList = (List<Course>) request.getAttribute("courseList");
	%>

	<h3>Student Course Enrollment Form</h3>

	<form action="<%=request.getContextPath()%>/addEnrollment"
		method="post">
		
		Department : <select id="deptFilter" onchange="applyDeptFilter()" required>
		<option value="" selected disabled>--Select Department--</option> 
		<option value="Computer Science">Computer Science</option>
		<option value="Civil">Civil</option>
		<option value="Mechanical">Mechanical</option>
		<option value="Artificial Intelligence">Artificial Intelligence</option>
		<option value="Automation & Robotics">Automation & Robotics</option>
		<option value="Electrical">Electrical</option> 
		</select><br><br>

		Student Name : <select name="studentId" id="studentSelect" disabled required>
			<option value=""><--Select Option--></option>
			<%
			for (Student s : sList) {
			%>
			<option value="<%=s.getStudentId()%>" data-dept="<%= s.getStudentDepartment()%>">
				<%=s.getStudentName()%> :
				<%=s.getStudentDepartment()%></option>
			<%
			}
			%>
		</select><br>
		<br> Course Name : <select name="courseId" id="courseSelect" disabled required>
			<option value=""><--Select Option--></option>
			<%
			for (Course c : cList) {
			%>
			<option value="<%=c.getCourseId()%>" data-dept="<%= c.getTeacherDepartment()%>">
				<%=c.getCourseName()%> (Prof. :
				<%=c.getTeacherName()%>)
			</option>
			<%
			}
			%>
		</select>

		<button type="submit">Add Enrollment</button>
	</form>

	<a href="<%=request.getContextPath()%>/DashBoard/adminDashboard.jsp"><button>Back</button></a>
</body>
<script>
	function applyDeptFilter(){
		const deptSelect = document.getElementById('deptFilter').value;
		const studentSelect = document.getElementById('studentSelect');
		const courseSelect = document.getElementById('courseSelect');
		
		studentSelect.disabled = false;
		courseSelect.disabled = false;
		
		filterDropDown(studentSelect, deptSelect);
		filterDropDown(courseSelect, deptSelect);
	}
	
	function filterDropDown(selectElement ,dept){
		const options = selectElement.options;
		selectElement.value = "";
		
		for(let i=1; i<options.length; i++){
			const optionDept = options[i].getAttribute('data-dept');
			if(optionDept == dept){
				options[i].style.display = "block";
			}else{
				options[i].style.display = "none";
			}
		}
	}
</script>
</html>