package in.keen.Controller;

import java.io.IOException;

import in.keen.DAO.UserDAO;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginUser")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		UserDAO dao = new UserDAO();
		
		User user = dao.loginUser(email, password);
		
		if(user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("session_user", user);
			
			String role = user.getUserRole();
			
			if("admin".equals(role)) {
				resp.sendRedirect("DashBoard/adminDashboard.jsp");
			}else if("teacher".equals(role)) {
				resp.sendRedirect("DashBoard/teacherDashboard.jsp");
			}else if("student".equals(role)) {
				resp.sendRedirect("DashBoard/studentDashboard.jsp");
			}
			
			//resp.sendRedirect("dashboard.jsp");
		}else {
			resp.sendRedirect("login.jsp?error=Invalid email or password");
		}
	}
}
