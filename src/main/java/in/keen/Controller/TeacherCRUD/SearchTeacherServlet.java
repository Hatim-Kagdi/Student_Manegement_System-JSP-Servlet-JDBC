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
public class SearchTeacherServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = req.getParameter("searchQuery");
		
		TeacherDAO dao = new TeacherDAO();
		
		List<Teacher> result;
		
		if(query == null  || query.trim().isEmpty()) {
			result = dao.viewAllTeachers();
		}else {
			result = dao.searchTeacher(query);
		}
		req.setAttribute("teacherList", result);
		req.getRequestDispatcher("/Teacher/viewTeacher.jsp").forward(req, resp);
	}

}
