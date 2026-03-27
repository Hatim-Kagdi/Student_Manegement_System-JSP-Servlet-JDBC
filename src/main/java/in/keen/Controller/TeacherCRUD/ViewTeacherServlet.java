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

@WebServlet("/viewTeachers")
public class ViewTeacherServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int pageSize = 10;

		String pStr = req.getParameter("page");
		if (pStr != null && !pStr.isEmpty()) {
			page = Integer.parseInt(pStr);
		}

		String ppStr = req.getParameter("pageSize");
		if (ppStr != null && !ppStr.isEmpty()) {
			pageSize = Integer.parseInt(ppStr);
		}
		TeacherDAO dao = new TeacherDAO();
		// Now we use pagination
		// List<Teacher> teacher = dao.viewAllTeachers();

		List<Teacher> teacher = dao.getTeacherByPage(page, pageSize);
		int totalRecords = dao.getAllTeachersCount();
		int totalPages = (int) Math.ceilDiv(totalRecords, pageSize);

		req.setAttribute("teacherList", teacher);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("currentPage", page);
		req.setAttribute("pageSize", pageSize);

		req.getRequestDispatcher("Teacher/viewTeacher.jsp").forward(req, resp);
	}
}
