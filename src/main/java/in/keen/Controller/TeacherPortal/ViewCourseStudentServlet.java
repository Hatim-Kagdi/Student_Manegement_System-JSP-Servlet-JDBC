package in.keen.Controller.TeacherPortal;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.EnrollmentDAO;
import in.keen.Model.Enrollment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewCourseStudents")
public class ViewCourseStudentServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		
		EnrollmentDAO edao = new EnrollmentDAO();
		
		List<Enrollment> list = edao.getStudentByCourseId(courseId);
		
		req.setAttribute("courseStudentList", list);
		
		req.getRequestDispatcher("/Teacher/viewCourseStudent.jsp").forward(req, resp);
	}
}
