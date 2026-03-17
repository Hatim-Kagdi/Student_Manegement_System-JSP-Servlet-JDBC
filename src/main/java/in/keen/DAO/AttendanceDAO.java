package in.keen.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.keen.Connection.DBconnection;

public class AttendanceDAO {
	
	//Save attendance in table
	public boolean saveAttendance(int studentId, int courseId, String status,Date attendanceDate) {
		
		boolean success = false;
		
		String query = "INSERT INTO attendance (att_student_id, att_course_id, status, date) VALUES (?, ?, ?, ?)";
        
        try (Connection con = DBconnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setInt(1, studentId);
            ps.setInt(2, courseId);
            ps.setString(3, status);
            ps.setDate(4, attendanceDate);
            
            if(ps.executeUpdate() > 0) success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
	
	
	//Get Attendance for Student Table
	public List<Map<String,String>> getStudentAttendanceRecords(int studentId){
		List<Map<String,String>> list = new ArrayList<>();
		
		String query = "SELECT c.course_name, a.date, a.status FROM attendance a JOIN courses c ON c.course_id = a.att_course_id WHERE a.att_student_id = ?";
		
		try(Connection con = DBconnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			
			ps.setInt(1, studentId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String, String> record = new HashMap<>();
				
				record.put("courseName", rs.getString("course_name"));
				record.put("date", rs.getString("date"));
				record.put("status", rs.getString("status"));
				
				list.add(record);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
