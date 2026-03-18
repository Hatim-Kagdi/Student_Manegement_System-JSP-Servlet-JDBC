package in.keen.Controller.EnrollmentCRUD;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import in.keen.DAO.CourseDAO;
import in.keen.DAO.EnrollmentDAO;
import in.keen.DAO.StudentDAO;
import in.keen.Model.Enrollment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchEnrollment")
public class SearchEnrollmentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = req.getParameter("searchQuery");
		
		EnrollmentDAO edao = new EnrollmentDAO();
		
		List<Enrollment> result;
		
		if(query == null || query.trim().isEmpty()) {
			result = edao.getAllEnrollments();
		}else {
			result = edao.searchEnrollment(query);
		}
		
		req.setAttribute("enrollmentList", result);
		req.getRequestDispatcher("/Enrollment/viewEnrollment.jsp").forward(req, resp);
	}
}
