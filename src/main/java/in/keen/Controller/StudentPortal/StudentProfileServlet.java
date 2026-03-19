package in.keen.Controller.StudentPortal;

import java.io.IOException;

import in.keen.DAO.StudentDAO;
import in.keen.Model.Student;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/studentProfile")
public class StudentProfileServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("session_user");
		
		int userId = user.getUserId();
		
		StudentDAO sdao = new StudentDAO();
		
		Student student = sdao.getStudentProfileByUserId(userId);
		
		req.setAttribute("studentProfileDetails", student);
		
		req.getRequestDispatcher("/Student/viewStudentProfile.jsp").forward(req, resp);
	}

}
