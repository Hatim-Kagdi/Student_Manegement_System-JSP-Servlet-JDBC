package in.keen.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.keen.Connection.DBconnection;
import in.keen.Model.Teacher;

public class TeacherDAO {
	//Add New Student
	public boolean addTeacher(Teacher teacher,int userId) {
		boolean status = false;
		
		try {
			Connection con = DBconnection.getConnection();
			String query = "INSERT INTO teachers(teachers_name, teachers_email, teachers_department,user_id) VALUES(?, ?, ?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, teacher.getTeacherName());
			ps.setString(2, teacher.getTeacherEmail());
			ps.setString(3, teacher.getTeacherDepartment());
			ps.setInt(4, userId);
			
			int add = ps.executeUpdate();
			
			if(add > 0) {
				status = true;
			}
			con.close();
			ps.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	//View all Student
	public List<Teacher> viewAllTeachers(){
		List<Teacher> list = new ArrayList<>();
		
		try {
			Connection con = DBconnection.getConnection();
			String query = "SELECT * FROM teachers WHERE is_deleted = false";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setTeacherId(rs.getInt("teachers_id"));
				teacher.setTeacherName(rs.getString("teachers_name"));
				teacher.setTeacherEmail(rs.getString("teachers_email"));
				teacher.setTeacherDepartment(rs.getString("teachers_department"));
				
				list.add(teacher);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//Get Teacher by Id
	public Teacher getTeacherById(int id) {
		Teacher teacher = null;
		
		try {
			
			Connection con = DBconnection.getConnection();
			String query = "SELECT * FROM teachers where teachers_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				teacher = new Teacher();
				
				teacher.setTeacherId(rs.getInt("teachers_id"));
				teacher.setTeacherName(rs.getString("teachers_name"));
				teacher.setTeacherEmail(rs.getString("teachers_email"));
				teacher.setTeacherDepartment(rs.getString("teachers_department"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return teacher;
	}
	
	//Update Teacher
	public boolean updateTeacher(Teacher teacher) {
		boolean status = false;
		
		try {
			
			Connection con = DBconnection.getConnection();
			String query = "UPDATE teachers SET teachers_name = ?, teachers_email = ?, teachers_department = ? where teachers_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, teacher.getTeacherName());
			ps.setString(2, teacher.getTeacherEmail());
			ps.setString(3, teacher.getTeacherDepartment());
			ps.setInt(4, teacher.getTeacherId());
			
			int update = ps.executeUpdate();
			
			if(update > 0) {
				status = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	//DELETE BY ID
	public boolean deleteById(int id) {
		boolean status = false;
		
		try {
			Connection con = DBconnection.getConnection();
			String query = "UPDATE teachers SET is_deleted = true WHERE teachers_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
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
	
	//Get Teacher By User id
	public int getTeacherByUserId(int userId) {
		int teacherId = 0;
		
		try {
			Connection con = DBconnection.getConnection();
			String query = "SELECT * FROM teachers WHERE user_id = ? AND is_deleted = false";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				teacherId = rs.getInt("teachers_id");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return teacherId;
	}
	
	//Search Teacher
	public List<Teacher> searchTeacher(String query){
		List<Teacher> list = new ArrayList<>();
		
		String sql = "SELECT * FROM teachers WHERE (teachers_id LIKE ? OR teachers_name LIKE ?) and is_deleted = false";
		
		try(Connection con = DBconnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)){
			String wildCardQuery = "%"+query+"%";
			
			ps.setString(1, wildCardQuery);
			ps.setString(2, wildCardQuery);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Teacher t = new Teacher();
				t.setTeacherId(rs.getInt("teachers_id"));
				t.setTeacherName(rs.getString("teachers_name"));
				t.setTeacherDepartment(rs.getString("teachers_department"));
				t.setTeacherEmail(rs.getString("teachers_email"));
				list.add(t);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Get teacher for profile
	public Teacher getTeacherProfileByUserId(int userId) {
		Teacher t = null;
		String query = "SELECT t.*, user_password FROM teachers t JOIN users u ON t.user_id = u.user_id WHERE t.user_id = ? AND t.is_deleted = false";
		
		try(Connection con = DBconnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				t = new Teacher();
				t.setTeacherId(rs.getInt("teachers_id"));
				t.setTeacherName(rs.getString("teachers_name"));
				t.setTeacherEmail(rs.getString("teachers_email"));
				t.setTeacherDepartment(rs.getString("teachers_department"));
				t.setTeacherPassword(rs.getString("user_password"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return t;
	}
}
