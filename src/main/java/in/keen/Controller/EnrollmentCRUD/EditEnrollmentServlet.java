package in.keen.Controller.EnrollmentCRUD;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.CourseDAO;
import in.keen.DAO.EnrollmentDAO;
import in.keen.DAO.StudentDAO;
import in.keen.Model.Course;
import in.keen.Model.Enrollment;
import in.keen.Model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/editEnrollment")
public class EditEnrollmentServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("enrollmentId"));
		
		EnrollmentDAO dao = new EnrollmentDAO();
		StudentDAO sdao = new StudentDAO();
		CourseDAO cdao = new CourseDAO();
		
		Enrollment enrollment = dao.getEnrollmentById(id);
		List<Student> student = sdao.getAllStudents();
		List<Course> course = cdao.getAllCourses();
		
		req.setAttribute("enrollment", enrollment);
		req.setAttribute("course", course);
		req.setAttribute("student", student);
		
		req.getRequestDispatcher("/Enrollment/editEnrollment.jsp").forward(req, resp);
	}
}
