package in.keen.Model;

import java.sql.Date;

public class Student {
	private int studentId;
	private String studentName;
	private String studentEmail;
	private String studentDepartment;
	private int studentYear;
	private Date studentAddmissionYear;
	private String studentPassword;

	//Student Password
	public String getStudentPassword() {
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}
	//Getter and Setters
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getStudentEmail() {
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	public String getStudentDepartment() {
		return studentDepartment;
	}
	public void setStudentDepartment(String studentDepartment) {
		this.studentDepartment = studentDepartment;
	}
	public int getStudentYear() {
		return studentYear;
	}
	public void setStudentYear(int studentYear) {
		this.studentYear = studentYear;
	}
	public Date getStudentAddmissionYear() {
		return studentAddmissionYear;
	}
	public void setStudentAddmissionYear(Date studentAddmissionYear) {
		this.studentAddmissionYear = studentAddmissionYear;
	}
}
