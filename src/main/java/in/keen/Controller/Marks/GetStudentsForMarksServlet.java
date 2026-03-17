package in.keen.Controller.Marks;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.EnrollmentDAO;
import in.keen.Model.Enrollment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/getStudentsForMarks")
public class GetStudentsForMarksServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		
		EnrollmentDAO edao = new EnrollmentDAO();
		
		List<Enrollment> list = edao.getStudentByCourseId(courseId);
		
		req.setAttribute("selectedCourseId", courseId);
		req.setAttribute("studentListForMarks", list);
		req.getRequestDispatcher("/Marks/gradeStudents.jsp").forward(req, resp);
		
	}
}
