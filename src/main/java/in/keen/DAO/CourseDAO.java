package in.keen.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.keen.Connection.DBconnection;
import in.keen.Model.Course;

public class CourseDAO {
	// Add new course
	public boolean addCourse(Course course) {
		boolean status = false;

		try {
			Connection con = DBconnection.getConnection();
			String query = "INSERT INTO courses(course_name ,teacher_id) VALUES (?,?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getTeacherId());

			int add = ps.executeUpdate();

			if (add > 0) {
				status = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	// view all courses
	public List<Course> getAllCourses() {
		List<Course> list = new ArrayList<>();

		try {
			Connection con = DBconnection.getConnection();
			String query = "SELECT * FROM courses WHERE is_deleted = false";
			PreparedStatement ps = con.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setTeacherId(rs.getInt("teacher_id"));
				list.add(course);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// Get Course By id
	public Course getCourseById(int id) {
		Course course = null;

		try {
			Connection con = DBconnection.getConnection();
			String query = "SELECT * FROM courses WHERE course_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				course.setTeacherId(rs.getInt("teacher_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	// Update Course
	public boolean updateCourse(Course course) {
		boolean status = false;

		try {
			Connection con = DBconnection.getConnection();
			String query = "UPDATE courses SET course_name = ?, teacher_id = ? WHERE course_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getTeacherId());
			ps.setInt(3, course.getCourseId());

			int update = ps.executeUpdate();

			if (update > 0) {
				status = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public boolean deleteCourse(int id) {
		boolean status = false;

		try {
			Connection con = DBconnection.getConnection();
			String query = "UPDATE courses SET is_deleted = true WHERE course_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			int delete = ps.executeUpdate();

			if (delete > 0) {
				status = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	// Get Course by teacher id
	public List<Course> getCoursesByTeacherId(int teacherId) {
		List<Course> list = new ArrayList<>();

		try {
			Connection con = DBconnection.getConnection();
			String query = "SELECT * FROM courses WHERE teacher_id = ? AND is_deleted = false";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, teacherId);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Course c = new Course();
				c.setCourseId(rs.getInt("course_id"));
				c.setCourseName(rs.getString("course_name"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// Get all courses with Teachers
	public List<Course> getAllCourseWithTeacher() {
		List<Course> list = new ArrayList<>();

		String query = "SELECT c.*,u.user_name AS teacher_name FROM courses c JOIN teachers t ON c.teacher_id = t.teachers_id JOIN users u ON t.user_id = u.user_id WHERE c.is_deleted = false";

		try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Course c = new Course();
				c.setCourseId(rs.getInt("course_id"));
				c.setCourseName(rs.getString("course_name"));
				c.setTeacherName(rs.getString("teacher_name"));
				list.add(c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// Search Courses
	public List<Course> searchCourse(String query, int currentPage, int pageSize) {
		List<Course> list = new ArrayList<>();
		int offset = (currentPage - 1) * pageSize;

		String sql = "SELECT c.*, t.teachers_name FROM courses c LEFT JOIN teachers t ON c.teacher_id = t.teachers_id WHERE (c.course_id LIKE ? OR c.course_name LIKE ?) AND c.is_deleted = false LIMIT ? OFFSET ?";

		try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			String wildquery = "%" + query + "%";
			ps.setString(1, wildquery);
			ps.setString(2, wildquery);
			ps.setInt(3, pageSize);
			ps.setInt(4, offset);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Course c = new Course();
				c.setCourseId(rs.getInt("course_id"));
				c.setCourseName(rs.getString("course_name"));
				c.setTeacherName(rs.getString("teachers_name"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getSearchedCoursesCount(String query) {
		String q = "SELECT COUNT(*) FROM courses WHERE (course_name LIKE ? OR course_id LIKE ?) AND is_deleted = false";
		try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(q)) {
			ps.setString(1, "%" + query + "%");
			ps.setString(2, "%" + query + "%");

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Pagination
	public List<Course> getCourseByPage(int currentPage, int pageSize) {
		List<Course> list = new ArrayList<>();
		int offset = (currentPage - 1) * pageSize;
		String query = "SELECT c.*, t.teachers_name FROM courses c JOIN teachers t ON c.teacher_id = t.teachers_id WHERE (c.is_deleted = false AND t.is_deleted = false) LIMIT ? OFFSET ?";
		try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, pageSize);
			ps.setInt(2, offset);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course c = new Course();
				c.setCourseId(rs.getInt("course_id"));
				c.setCourseName(rs.getString("course_name"));
				c.setTeacherName(rs.getString("teachers_name"));
				list.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getAllCoursesCount() {
		String query = "SELECT COUNT(*) FROM courses WHERE is_deleted = false";
		try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
}
