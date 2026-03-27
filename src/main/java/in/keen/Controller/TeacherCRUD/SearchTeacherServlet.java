package in.keen.Controller.TeacherCRUD;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.TeacherDAO;
import in.keen.Model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/searchTeacher")
public class SearchTeacherServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = req.getParameter("searchQuery");
		int page = 1;
		int pageSize = 10;

		if (req.getParameter("page") != null)
			page = Integer.parseInt(req.getParameter("page"));
		if (req.getParameter("pageSize") != null)
			pageSize = Integer.parseInt(req.getParameter("pageSize"));

		TeacherDAO dao = new TeacherDAO();

		List<Teacher> result;
		int totalPages;
		if (query == null || query.trim().isEmpty()) {
			result = dao.getTeacherByPage(page, pageSize);
			int totalRecords = dao.getAllTeachersCount();
			totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		} else {
			result = dao.searchTeacher(query, page, pageSize);
			int totalRecords = dao.getAllTeacherSearchCount(query);
			totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		}
		req.setAttribute("teacherList", result);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("currentPage", page);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("searchQuery", query);

		req.getRequestDispatcher("/Teacher/viewTeacher.jsp").forward(req, resp);
	}

}
