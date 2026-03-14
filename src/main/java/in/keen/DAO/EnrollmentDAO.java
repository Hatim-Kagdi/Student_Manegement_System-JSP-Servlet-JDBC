package in.keen.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.keen.Connection.DBconnection;
import in.keen.Model.Enrollment;

public class EnrollmentDAO {
	//Add New Enrollment
	public boolean addEnrollment(Enrollment enrollment) {
		boolean status = false;
		try {
			Connection con = DBconnection.getConnection();
			String query = "INSERT INTO enrollments (student_id , courses_id) VALUES (? ,?) ";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, enrollment.getStudentId());
			ps.setInt(2 , enrollment.getCourseId());
			
			int add = ps.executeUpdate();
			
			if(add > 0) {
				status = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	//View All Enrollments
	public List<Enrollment> getAllEnrollments(){
		List<Enrollment> list = new ArrayList<>();
		
		try {
			
			Connection con = DBconnection.getConnection();
			String query = "SELECT e.enrollment_id, s.students_name, c.course_name FROM enrollments e JOIN students s ON e.student_id = s.students_id JOIN courses c ON e.courses_id = c.course_id WHERE e.is_deleted = false AND s.is_deleted = false AND c.is_deleted = false";
			PreparedStatement ps = con.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Enrollment e = new Enrollment();
				e.setEnrollmentId(rs.getInt("enrollment_id"));
				e.setStudentName(rs.getString("students_name"));
				e.setCourseName(rs.getString("course_name"));
				list.add(e);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Get Enrollment By id
	
	public Enrollment getEnrollmentById(int id) {
		Enrollment enrollment = null;
		
		try {
			Connection con = DBconnection.getConnection();
			String query = "SELECT * FROM enrollments WHERE enrollment_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				enrollment = new Enrollment();
				
				enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
				enrollment.setStudentId(rs.getInt("student_id"));
				enrollment.setCourseId(rs.getInt("courses_id"));
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return enrollment;
	}
	
	//Update Enrollment
	
	public boolean updateEnrollment(Enrollment enrollment) {
		boolean status = false;
		
		try {
			Connection con = DBconnection.getConnection();
			String query = "UPDATE enrollments SET student_id = ?, courses_id = ? WHERE enrollment_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, enrollment.getStudentId());
			ps.setInt(2, enrollment.getCourseId());
			ps.setInt(3, enrollment.getEnrollmentId());
			
			int update = ps.executeUpdate();
			
			if(update > 0) {
				status = true;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	
	//Delete Enrollment
	
	public boolean deleteEnrollment(int id) {
		boolean status = false;
		String query = "UPDATE enrollments SET is_deleted = true WHERE enrollment_id = ?";
		try(Connection con = DBconnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			ps.setInt(1, id);
			
			int delete = ps.executeUpdate();
			
			if(delete > 0) {
				status = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	//Get Student by Id for courses 
	public List<Enrollment> getCoursesByStudentId(int studentId){
		List<Enrollment> list = new ArrayList<>();
		
		try {
			Connection con = DBconnection.getConnection();
			String query = "SELECT c.course_name, t.teachers_name FROM enrollments e JOIN courses c ON e.courses_id = c.course_id JOIN teachers t ON c.teacher_id = t.teachers_id WHERE e.student_id = ? AND e.is_deleted = false";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, studentId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Enrollment e = new Enrollment();
				e.setCourseName(rs.getString("course_name"));
				e.setTeacherName(rs.getString("teachers_name"));
				list.add(e);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
