package in.keen.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.keen.Connection.DBconnection;
import in.keen.Model.Student;

public class StudentDAO {
	//Add New Student
		public boolean addStudent(Student student, int userId) {
			boolean status = false;
			
			try {
				String query = "INSERT INTO students(students_name, students_email, students_department, students_year,students_admission_year,user_id) VALUES (?,?,?,?,?,?)";
				Connection con = DBconnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1, student.getStudentName());
				ps.setString(2, student.getStudentEmail());
				ps.setString(3, student.getStudentDepartment());
				ps.setInt(4, student.getStudentYear());
				ps.setDate(5, student.getStudentAddmissionYear());
				ps.setInt(6, userId);
				
				int addStudent = ps.executeUpdate();
				
				if(addStudent > 0) {
					status = true;
				}
				con.close();
				ps.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
			return status;
		}
		
		//View all students 
		public List<Student> getAllStudents(){
			List<Student> list = new ArrayList<>();
			try {
			Connection con = DBconnection.getConnection();
			
			String query = "SELECT * FROM students WHERE is_deleted = false;";
			
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				Student student = new Student(); 
				
				student.setStudentId(rs.getInt("students_id"));
				student.setStudentName(rs.getString("students_name"));
				student.setStudentEmail(rs.getString("students_email"));
				student.setStudentDepartment(rs.getString("students_department"));
				student.setStudentYear(rs.getInt("students_year"));
				student.setStudentAddmissionYear(rs.getDate("students_admission_year"));
				
				list.add(student);
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		//Get student by id
		public Student getStudentById(int id) {
			Student student = null;
			
			try {
				Connection con = DBconnection.getConnection();
				
				String query = "SELECT * FROM students WHERE students_id = ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1, id);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					student = new Student();
					
					student.setStudentId(rs.getInt("students_id"));
					student.setStudentName(rs.getString("students_name"));
					student.setStudentEmail(rs.getString("students_email"));
					student.setStudentDepartment(rs.getString("students_department"));
					student.setStudentYear(rs.getInt("students_year"));
					student.setStudentAddmissionYear(rs.getDate("students_admission_year"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return student;
		}
		
		//Update Student
		public boolean updateStudent(Student student) {
			boolean status = false;
			
			try {
				Connection con = DBconnection.getConnection();
				String query = "UPDATE students SET students_name=?, students_email=?, students_department=?, students_year=?, students_admission_year=? WHERE students_id = ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setString(1,student.getStudentName());
				ps.setString(2, student.getStudentEmail());
				ps.setString(3,student.getStudentDepartment());
				ps.setInt(4, student.getStudentYear());
				ps.setDate(5, student.getStudentAddmissionYear());
				ps.setInt(6, student.getStudentId());
				
				int update = ps.executeUpdate();
				
				if(update > 0) {
					status = true;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return status;
		}
		
		//Delete Student
		public boolean deleteStudent(int id) {
			boolean status = false;
			
			try {
				Connection con = DBconnection.getConnection();
				String query = "UPDATE students SET is_deleted = true WHERE students_id = ?";
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1,id);
				
				int delete = ps.executeUpdate();
				
				if(delete > 0) {
					status = true;
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return status;
		}
		
		//Get Student by user id
		public int getStudentByUserId(int userId) {
			
			int studentId = 0;
			
			try {
				Connection con = DBconnection.getConnection();
				String query = "SELECT students_id FROM students WHERE user_id = ?";
				PreparedStatement ps = con.prepareStatement(query);
				
				ps.setInt(1, userId);
				
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					studentId = rs.getInt("students_id");
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return studentId;
		}
		
		//Search Students
		public List<Student> searchStudent(String query){
			List<Student> list = new ArrayList<>();
			String sql = "SELECT * FROM students WHERE (students_name LIKE ? OR students_id LIKE ?) and is_deleted = false";
			try(Connection con = DBconnection.getConnection();
					PreparedStatement ps = con.prepareStatement(sql)){
				String wildCardQuery = "%" + query + "%";
				ps.setString(1, wildCardQuery);
				ps.setString(2, wildCardQuery);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					Student s = new Student();
					s.setStudentId(rs.getInt("students_id"));
					s.setStudentName(rs.getString("students_name"));
					s.setStudentEmail(rs.getString("students_email"));
					s.setStudentDepartment(rs.getString("students_department"));
					s.setStudentYear(rs.getInt("students_year"));
					s.setStudentAddmissionYear(rs.getDate("students_admission_year"));
					list.add(s);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}
		
		public Student getStudentProfileByUserId(int userId) {
			Student s = new Student();
			String query = "SELECT s.*, u.user_password FROM students s JOIN users u ON s.user_id = u.user_id WHERE s.user_id = ? AND s.is_deleted = false";
			
			try(Connection con = DBconnection.getConnection();
					PreparedStatement ps = con.prepareStatement(query)){
				ps.setInt(1,userId);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					s = new Student();
					s.setStudentId(rs.getInt("students_id"));
					s.setStudentName(rs.getString("students_name"));
					s.setStudentEmail(rs.getString("students_email"));
					s.setStudentDepartment(rs.getString("students_department"));
					s.setStudentYear(rs.getInt("students_year"));
					s.setStudentAddmissionYear(rs.getDate("students_admission_year"));
					s.setStudentPassword(rs.getString("user_password"));
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return s;
		}
		
		public boolean updateStudentField(int studentId, String fieldName, String newValue) {
			Connection con = null;
			boolean status = false;
			String columnName = "";
			String userColumn = "";
			
			if("name".equals(fieldName)) {
				columnName = "students_name";
				userColumn = "user_name";
			}else if("email".equals(fieldName)) {
				columnName = "students_email";
				userColumn = "user_email";
			}
			
			if(columnName.isEmpty()) return false;
			
			try{
				con = DBconnection.getConnection();
				con.setAutoCommit(false);
				
				String studentQuery = "UPDATE students SET " + columnName + " = ? WHERE students_id = ?";
				PreparedStatement ps = con.prepareStatement(studentQuery);
				ps.setString(1, newValue);
				ps.setInt(2 , studentId);
				int r1 = ps.executeUpdate();
				
				
				String userQuery = "UPDATE users SET " + userColumn + " = ? WHERE user_id = (SELECT user_id FROM students WHERE students_id = ?)";
				PreparedStatement ps2 = con.prepareStatement(userQuery);
				ps2.setString(1 , newValue);
				ps2.setInt(2 , studentId);
				int r2 = ps2.executeUpdate();
				
				if(r1 > 0 && r2 > 0) {
					con.commit();
					status = true;
				}else {
					con.rollback();
				}
			}
				catch (Exception e) {
			        try { if(con != null) con.rollback(); } catch(Exception ex) {}
			        e.printStackTrace();
			    } finally {
			        try { if(con != null) con.close(); } catch(Exception ex) {}
			    }
			    return status;
		}
}
