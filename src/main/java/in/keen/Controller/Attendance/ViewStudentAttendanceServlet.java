package in.keen.Controller.Attendance;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import in.keen.DAO.AttendanceDAO;
import in.keen.DAO.StudentDAO;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewStudentAttendance")
public class ViewStudentAttendanceServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("session_user");
		
		int userId = user.getUserId();
		
		StudentDAO sdao = new StudentDAO();
		
		int studentId = sdao.getStudentByUserId(userId);
		
		AttendanceDAO adao = new AttendanceDAO();
		
		List<Map<String, String>> attendanceList = adao.getStudentAttendanceRecords(studentId);
		
		req.setAttribute("attendanceList", attendanceList);
		
		req.getRequestDispatcher("/Attendance/viewAttendance.jsp").forward(req, resp);
		
		
	}
}
