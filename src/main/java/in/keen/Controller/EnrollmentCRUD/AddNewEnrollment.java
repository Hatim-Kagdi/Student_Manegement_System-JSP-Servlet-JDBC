package in.keen.Controller.EnrollmentCRUD;

import java.io.IOException;

import in.keen.DAO.EnrollmentDAO;
import in.keen.Model.Enrollment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addEnrollment")
public class AddNewEnrollment extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int studentId = Integer.parseInt(req.getParameter("studentId"));
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		
		Enrollment enrollment = new Enrollment();
		enrollment.setCourseId(courseId);
		enrollment.setStudentId(studentId);
		
		EnrollmentDAO dao = new EnrollmentDAO();
		boolean status = dao.addEnrollment(enrollment);
		
		if(status) {
			resp.sendRedirect("viewEnrollments");
		}else {
			resp.getWriter().print("Enrollment failed!");
		}
	}
}
