package in.keen.Controller.EnrollmentCRUD;

import java.io.IOException;

import in.keen.DAO.EnrollmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteEnrollment")
public class DeleteEnrollmentServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("enrollmentId"));
		
		EnrollmentDAO dao = new EnrollmentDAO();
		
		boolean status = dao.deleteEnrollment(id);
		
		if(status) {
			resp.sendRedirect("viewEnrollments");
		}else {
			resp.getWriter().print("Delete Enrollment Failed");
		}
	}
}
