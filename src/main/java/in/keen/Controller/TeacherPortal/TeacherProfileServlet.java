package in.keen.Controller.TeacherPortal;

import java.io.IOException;

import in.keen.DAO.TeacherDAO;
import in.keen.Model.Teacher;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/viewTeacherProfile")
public class TeacherProfileServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("session_user");
		
		int userId = user.getUserId();
		
		TeacherDAO tdao = new TeacherDAO();
		
		Teacher teacher = tdao.getTeacherProfileByUserId(userId);
		
		req.setAttribute("teacherProfileDetails", teacher);
		
		req.getRequestDispatcher("/Teacher/viewTeacherProfile.jsp").forward(req, resp);
	}
}
