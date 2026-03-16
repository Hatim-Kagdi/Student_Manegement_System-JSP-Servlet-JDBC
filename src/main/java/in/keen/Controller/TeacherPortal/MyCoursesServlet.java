package in.keen.Controller.TeacherPortal;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.CourseDAO;
import in.keen.DAO.EnrollmentDAO;
import in.keen.DAO.TeacherDAO;
import in.keen.Model.Course;
import in.keen.Model.Enrollment;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/teachersCourses")
public class MyCoursesServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		User user = (User) session.getAttribute("session_user");
		
		int userId = user.getUserId();
		
		TeacherDAO tdao = new TeacherDAO();
		
		int teacherId = tdao.getTeacherByUserId(userId);
		
		CourseDAO edao = new CourseDAO();
		
		List<Course> list = edao.getCoursesByTeacherId(teacherId);
		
		req.setAttribute("teacherCourseList", list);
		
		req.getRequestDispatcher("/Teacher/myCourses.jsp").forward(req, resp);
		
	}
}
