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
		
		StudentDAO sdao = new StudentDAO();
		
		List<Student> result;
		
		if(query == null || query.trim().isEmpty()) {
			result = sdao.getAllStudents();
		}else {
			result = sdao.searchStudent(query);
		}
		
		req.setAttribute("studentList", result);
		req.getRequestDispatcher("/Student/viewStudents.jsp").forward(req, resp);
	}
}
