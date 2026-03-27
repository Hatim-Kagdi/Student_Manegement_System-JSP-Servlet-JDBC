package in.keen.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.keen.Connection.DBconnection;
import in.keen.Model.Teacher;

public class TeacherDAO {
	// Add New Student
	public boolean addTeacher(Teacher teacher, int userId) {
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

			if (add > 0) {
				status = true;
			}
			con.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	// View all Student
	public List<Teacher> viewAllTeachers() {
		List<Teacher> list = new ArrayList<>();

		try {
			Connection con = DBconnection.getConnection();
			String query = "SELECT * FROM teachers WHERE is_deleted = false";
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setTeacherId(rs.getInt("teachers_id"));
				teacher.setTeacherName(rs.getString("teachers_name"));
				teacher.setTeacherEmail(rs.getString("teachers_email"));
				teacher.setTeacherDepartment(rs.getString("teachers_department"));

				list.add(teacher);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// Get Teacher by Id
	public Teacher getTeacherById(int id) {
		Teacher teacher = null;

		try {

			Connection con = DBconnection.getConnection();
			String query = "SELECT * FROM teachers where teachers_id = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				teacher = new Teacher();

				teacher.setTeacherId(rs.getInt("teachers_id"));
				teacher.setTeacherName(rs.getString("teachers_name"));
				teacher.setTeacherEmail(rs.getString("teachers_email"));
				teacher.setTeacherDepartment(rs.getString("teachers_department"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return teacher;
	}

	// Update Teacher
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

			if (update > 0) {
				status = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// DELETE BY ID
	public boolean deleteById(int id) {
		boolean status = false;

		try {
			Connection con = DBconnection.getConnection();
			String query = "UPDATE teachers SET is_deleted = true WHERE teachers_id = ?";
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

	// Get Teacher By User id
	public int getTeacherByUserId(int userId) {
		int teacherId = 0;

		try {
			Connection con = DBconnection.getConnection();
			String query = "SELECT * FROM teachers WHERE user_id = ? AND is_deleted = false";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				teacherId = rs.getInt("teachers_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return teacherId;
	}

	// Search Teacher
	public List<Teacher> searchTeacher(String query, int page, int pageSize) {
		List<Teacher> list = new ArrayList<>();
		int offset = (page - 1) * pageSize;

		String sql = "SELECT * FROM teachers WHERE (teachers_id LIKE ? OR teachers_name LIKE ?) and is_deleted = false LIMIT ? OFFSET ?";

		try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			String wildCardQuery = "%" + query + "%";

			ps.setString(1, wildCardQuery);
			ps.setString(2, wildCardQuery);
			ps.setInt(3, pageSize);
			ps.setInt(4, offset);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Teacher t = new Teacher();
				t.setTeacherId(rs.getInt("teachers_id"));
				t.setTeacherName(rs.getString("teachers_name"));
				t.setTeacherDepartment(rs.getString("teachers_department"));
				t.setTeacherEmail(rs.getString("teachers_email"));
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getAllTeacherSearchCount(String query) {
		String sql = "SELECT COUNT(*) FROM teachers WHERE (teachers_id LIKE ? OR teachers_name LIKE ? is_deleted = false";
		try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, "%" + query + "%");
			ps.setString(2, "%" + query + "%");

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Get teacher for profile
	public Teacher getTeacherProfileByUserId(int userId) {
		Teacher t = null;
		String query = "SELECT t.*, user_password FROM teachers t JOIN users u ON t.user_id = u.user_id WHERE t.user_id = ? AND t.is_deleted = false";

		try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				t = new Teacher();
				t.setTeacherId(rs.getInt("teachers_id"));
				t.setTeacherName(rs.getString("teachers_name"));
				t.setTeacherEmail(rs.getString("teachers_email"));
				t.setTeacherDepartment(rs.getString("teachers_department"));
				t.setTeacherPassword(rs.getString("user_password"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return t;
	}

	// Update Teacher Profile Fields in teachers and users table
	public boolean updateTeacherProfileField(int teacherId, String fieldName, String value) {
		boolean status = false;
		Connection con = null;
		String columnName = "";
		String userColumn = "";

		if ("name".equals(fieldName)) {
			columnName = "teachers_name";
			userColumn = "user_name";
		} else if ("email".equals(fieldName)) {
			columnName = "teachers_email";
			userColumn = "user_email";
		}

		if (columnName.isEmpty())
			return false;

		try {
			con = DBconnection.getConnection();
			con.setAutoCommit(false);

			String teacherQuery = "UPDATE teachers SET " + columnName + " = ? WHERE teachers_id = ?";
			PreparedStatement ps1 = con.prepareStatement(teacherQuery);
			ps1.setString(1, value);
			ps1.setInt(2, teacherId);
			int r1 = ps1.executeUpdate();

			String userQuery = "UPDATE users SET " + userColumn
					+ " = ? WHERE user_id = (SELECT user_id FROM teachers WHERE teachers_id = ?)";
			PreparedStatement ps2 = con.prepareStatement(userQuery);
			ps2.setString(1, value);
			ps2.setInt(2, teacherId);
			int r2 = ps2.executeUpdate();

			if (r1 > 0 && r2 > 0) {
				con.commit();
				status = true;
			} else {
				con.rollback();
			}

		} catch (Exception e) {
			try {
				if (con != null)
					con.rollback();
			} catch (Exception ex) {
			}
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception ex) {
			}
		}

		return status;
	}

	// Pagination
	public List<Teacher> getTeacherByPage(int currentPage, int pageSize) {
		List<Teacher> list = new ArrayList<>();
		int offset = (currentPage - 1) * pageSize;
		String query = "SELECT * FROM teachers WHERE is_deleted = false LIMIT ? OFFSET ?";
		try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, pageSize);
			ps.setInt(2, offset);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Teacher t = new Teacher();
				t.setTeacherId(rs.getInt("teachers_id"));
				t.setTeacherName(rs.getString("teachers_name"));
				t.setTeacherEmail(rs.getString("teachers_email"));
				t.setTeacherDepartment(rs.getString("teachers_department"));
				list.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getAllTeachersCount() {
		String query = "SELECT COUNT(*) FROM teachers WHERE is_deleted = false";
		try (Connection con = DBconnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
