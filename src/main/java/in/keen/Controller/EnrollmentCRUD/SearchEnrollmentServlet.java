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
		int page = 1;
		int pageSize = 10;
		
		if(req.getParameter("page") != null) page = (int) Integer.parseInt(req.getParameter("page"));
		if(req.getParameter("pageSize") != null) pageSize = (int) Integer.parseInt(req.getParameter("pageSize"));
		
		String query = req.getParameter("searchQuery");
		
		EnrollmentDAO edao = new EnrollmentDAO();
		
		List<Enrollment> result;
		int totalPages;
		if(query == null || query.trim().isEmpty()) {
			result = edao.getAllEnrollmentByPage(page, pageSize);
			int totalRecords = edao.getAllEnrollmentCount();
			totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
			
		}else {
			result = edao.searchEnrollment(query, page, pageSize);
			int totalRecords = edao.getSearchEnrollmentCount(query);
			totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		}
		
		req.setAttribute("enrollmentList", result);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("searchQuery", query);
		req.setAttribute("currentPage", page);
		req.setAttribute("pageSize", pageSize);
		req.getRequestDispatcher("/Enrollment/viewEnrollment.jsp").forward(req, resp);
	}
}
