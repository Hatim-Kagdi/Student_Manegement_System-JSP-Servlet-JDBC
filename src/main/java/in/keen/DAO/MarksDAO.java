package in.keen.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import in.keen.Connection.DBconnection;
import in.keen.Model.Mark;

public class MarksDAO {
	//Save Marks
	public boolean saveMarks(Mark mark) {
		boolean saved = false;
		String query = "INSERT INTO marks (student_id, course_id, marks) VALUES (?,?,?)";
		
		try(Connection con = DBconnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			
			int studentId = mark.getStudentId();
			int courseId = mark.getCourseId();
			int marks = mark.getMarks();
			
			ps.setInt(1, studentId);
			ps.setInt(2, courseId);
			ps.setInt(3, marks);
			
			if(ps.executeUpdate() > 0) {
				saved = true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return saved;
	}
	
	
	//View Marks
	public List<Map<String, Object>> getStudentMarks(int studentId){
		List<Map<String, Object>> list = new ArrayList<>();
		
		String query = "SELECT c.course_name, m.marks FROM marks m JOIN courses c ON m.course_id = c.course_id WHERE m.student_id = ?";
		
		try(Connection con = DBconnection.getConnection();
				PreparedStatement ps = con.prepareStatement(query)){
			
			ps.setInt(1, studentId);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Map<String ,Object> map = new HashMap<>();
				map.put("courseName", rs.getString("course_name"));
				map.put("marks", rs.getInt("marks"));
				list.add(map);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
