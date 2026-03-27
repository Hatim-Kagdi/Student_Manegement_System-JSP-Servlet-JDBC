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

@WebServlet("/viewStudents")
public class ViewStudentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int pageSize = 10;

		String pStr = req.getParameter("page");
		if (pStr != null && !pStr.isEmpty()) {
			page = Integer.parseInt(req.getParameter("page"));
		}

		String psStr = req.getParameter("pageSize");
		if (psStr != null && !psStr.isEmpty()) {
			pageSize = Integer.parseInt(req.getParameter("pageSize"));
		}

		StudentDAO dao = new StudentDAO();
		// Previously it was used to get all the student and view them in the page.Now
		// we have updated to pagination.
		// List<Student> students = dao.getAllStudents();

		List<Student> students = dao.getStudentsByPage(page, pageSize);
		int totalRecords = dao.getTotalStudentsCount();
		int totalPages = (int) Math.ceilDiv(totalRecords, pageSize);


		req.setAttribute("studentList", students);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("currentPage", page);
		req.setAttribute("pageSize", pageSize);

		req.getRequestDispatcher("Student/viewStudents.jsp").forward(req, resp);
	}
}
