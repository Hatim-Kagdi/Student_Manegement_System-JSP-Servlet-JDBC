package in.keen.Controller.Marks;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import in.keen.DAO.MarksDAO;
import in.keen.DAO.StudentDAO;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewStudentMarks")
public class ViewMarksServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		
		User user = (User) session.getAttribute("session_user");
		
		int userId = user.getUserId();
		
		StudentDAO sdao = new StudentDAO();
		
		int studentId = sdao.getStudentByUserId(userId);
		
		MarksDAO mdao = new MarksDAO();
		
		List<Map<String, Object>> list = mdao.getStudentMarks(studentId);
		
		req.setAttribute("marksList", list);
		
		req.getRequestDispatcher("/Marks/viewStudentMarks.jsp").forward(req, resp);
	}
}
