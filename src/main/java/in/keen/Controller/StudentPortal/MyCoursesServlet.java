package in.keen.Controller.StudentPortal;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.EnrollmentDAO;
import in.keen.DAO.StudentDAO;
import in.keen.Model.Enrollment;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/myCourses")
public class MyCoursesServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		
		User user = (User) session.getAttribute("session_user");
		
		int userId = user.getUserId();
		
		StudentDAO sdao = new StudentDAO();
		int studentId = sdao.getStudentByUserId(userId);
		
		EnrollmentDAO edao = new EnrollmentDAO();
		
		List<Enrollment> enrollment = edao.getCoursesByStudentId(studentId);
		
		req.setAttribute("courseList", enrollment);
		
		req.getRequestDispatcher("/Student/MyCourses.jsp").forward(req, resp);
	}
}
