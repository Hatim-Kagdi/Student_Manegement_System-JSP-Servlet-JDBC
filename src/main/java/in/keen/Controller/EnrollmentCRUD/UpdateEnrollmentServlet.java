package in.keen.Controller.EnrollmentCRUD;

import java.io.IOException;

import in.keen.DAO.EnrollmentDAO;
import in.keen.Model.Enrollment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/updateEnrollment")
public class UpdateEnrollmentServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int enrollmentId = Integer.parseInt(req.getParameter("enrollmentId"));
		int studentId = Integer.parseInt(req.getParameter("studentId"));
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		
		Enrollment e = new Enrollment();
		
		e.setEnrollmentId(enrollmentId);
		e.setStudentId(studentId);
		e.setCourseId(courseId);
		
		EnrollmentDAO dao = new EnrollmentDAO();
		
		boolean status = dao.updateEnrollment(e);
		
		if(status) {
			resp.sendRedirect("viewEnrollments");
		}else {
			resp.getWriter().print("Update Failed");
		}
	}
}
