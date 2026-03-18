package in.keen.Controller.EnrollmentCRUD;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.CourseDAO;
import in.keen.DAO.StudentDAO;
import in.keen.Model.Course;
import in.keen.Model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/enrollStudentPage")
public class EnrollStudentServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentDAO studentDAO = new StudentDAO();
		CourseDAO courseDAO = new CourseDAO();
		
		List<Student> studentList = studentDAO.getAllStudents();
		List<Course> courseList = courseDAO.getAllCourseWithTeacher();
		
		req.setAttribute("courseList", courseList);
		req.setAttribute("studentList", studentList);
		
		req.getRequestDispatcher("/Enrollment/enrollStudent.jsp").forward(req, resp);
	}
}
