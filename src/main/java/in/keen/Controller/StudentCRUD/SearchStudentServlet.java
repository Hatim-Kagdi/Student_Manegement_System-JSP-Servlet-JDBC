package in.keen.Controller.StudentCRUD;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.StudentDAO;
import in.keen.Model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchStudents")
public class SearchStudentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = req.getParameter("searchQuery");
		int page = 1;
		int pageSize = 10;
		
		if(req.getAttribute("page") != null) page = Integer.parseInt(req.getParameter("page"));
		if(req.getParameter("pageSize") != null) pageSize = Integer.parseInt(req.getParameter("pageSize"));
		
		StudentDAO sdao = new StudentDAO();
		
		List<Student> result;
		int totalPages;
		
		if(query == null || query.trim().isEmpty()) {
			result = sdao.getStudentsByPage(page, pageSize);
			int totalRecords = sdao.getTotalStudentsCount();
			totalPages = Math.ceilDiv(totalRecords, pageSize);
		}else {
			result = sdao.searchStudent(query, page, pageSize);
			int totalRecords = sdao.getSearchedStudentCount(query);
			totalPages = Math.ceilDiv(totalRecords, pageSize);
		}
		
		req.setAttribute("studentList", result);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("currentPage", page);
		req.setAttribute("searchQuery", query);
		req.getRequestDispatcher("/Student/viewStudents.jsp").forward(req, resp);
	}
}
