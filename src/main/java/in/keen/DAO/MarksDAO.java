package in.keen.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.keen.Connection.DBconnection;
import in.keen.Model.Mark;

public class MarksDAO {
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
}
