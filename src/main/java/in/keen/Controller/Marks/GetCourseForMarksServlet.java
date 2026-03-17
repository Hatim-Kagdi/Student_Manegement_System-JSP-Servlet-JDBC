package in.keen.Controller.Marks;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.CourseDAO;
import in.keen.DAO.TeacherDAO;
import in.keen.Model.Course;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/markGrades")
public class GetCourseForMarksServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("session_user");
		
		int userId = user.getUserId();
		
		TeacherDAO tdao = new TeacherDAO();
		
		int teacherId = tdao.getTeacherByUserId(userId);
		
		CourseDAO cdoa = new CourseDAO();
		
		List<Course> list = cdoa.getCoursesByTeacherId(teacherId);
		
		if(list != null && !list.isEmpty()) {
		req.setAttribute("courseList", list);
		req.getRequestDispatcher("/Marks/selectCourseForMarks.jsp").forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath() + "/Teacher/teacherDashboard.jsp");
		}
	}
}
