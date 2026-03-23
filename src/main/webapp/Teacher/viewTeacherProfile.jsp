<%@page import="in.keen.Model.Teacher"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Teacher Profile</title>
    <style>
        .profile-container { width: 50%; margin: 50px auto; border: 1px solid #ddd; padding: 20px; border-radius: 8px; font-family: sans-serif; }
        .info-group { margin-bottom: 15px; border-bottom: 1px solid #eee; padding-bottom: 5px; }
        .label { font-weight: bold; color: #555; display: inline-block; width: 150px; }
        .value { color: #222; }
        .locked-msg { font-size: 12px; color: #cc0000; font-style: italic; }
        .editable-value { cursor: pointer; border-bottom: 1px dashed #007bff; /* Visual cue that it's clickable */}
		.editable-value:hover { background-color: #f0f7ff; }
    </style>
</head>
<body>
    <% if("success".equals(request.getParameter("msg"))) { %>
    <div style="color: green; padding: 10px; border: 1px solid green;">
        Profile updated successfully!
    </div>
<% } %>
    <div class="profile-container">

        <h2>My Profile</h2>
        <p class="locked-msg">* Academic details can only be changed by the Administration.</p>
        <hr>

        <% Teacher s = (Teacher) request.getAttribute("teacherProfileDetails"); %>

        <div class="info-group">
            <span class="label">Roll No / ID:</span>
            <span class="value"><%= s.getTeacherId() %></span>
        </div>

        <div class="info-group">
            <span class="label">Full Name:</span>
            <span id="nameText" onclick="makeEditable('name')" class="editable-value"><%= s.getTeacherName() %></span>
            <input type="text" id="nameInput" style="display:none;" value="<%= s.getTeacherName() %>" onBlur="saveData('name')">
        </div>

        <div class="info-group">
            <span class="label">Email:</span>
            <span id="emailText" onclick="makeEditable('email')" class="editable-value"><%= s.getTeacherEmail() %></span>
            <input type="email" id="emailInput" style="display:none;" value="<%= s.getTeacherEmail() %>" onBlur="saveData('email')">
        </div>
 		<div class="info-group">
            <span class="label">Password:</span>
            <span class="value">******** (Encrypted)</span>
        </div>
        <div class="info-group">
            <span class="label">Department:</span>
            <span class="value"><%= s.getTeacherDepartment() %></span>
        </div>

        <br>
        <a href="<%= request.getContextPath() %>/Teacher/editTeacherPassword.jsp"><button>Change Password</button></a>
        <a href="<%= request.getContextPath() %>/DashBoard/teacherDashboard.jsp"><button style="background: #eee;">Back</button></a>
    </div>
</body>
<script>
function makeEditable(field){
	document.getElementById(field + "Text").style.display = "none";
	document.getElementById(field + "Input").style.display = "inline";
	document.getElementById(field + "Input").focus();
}

function saveData(field){
	let newValue = document.getElementById(field + "Input").value;
	let teacherId = "<%= s.getTeacherId() %>";
	
	fetch("<%= request.getContextPath()%>/updateTeacherProfileAjax", {
		method:"POST",
		headers:{"Content-Type" : "application/x-www-form-urlencoded"},
		body : "field=" + field + "&value=" + encodeURIComponent(newValue) + "&teacherId="+ teacherId 
	})
	.then(response => response.text())
	.then(data => {
	if(data === "success"){
		document.getElementById(field + "Text").innerText = newValue;
		document.getElementById(field + "Text").style.display = "inline";
		document.getElementById(field + "Input").style.display = "none";
	}
	});
}
</script>
</html>