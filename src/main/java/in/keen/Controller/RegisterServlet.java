package in.keen.Controller;

import java.io.IOException;

import in.keen.DAO.UserDAO;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerUser")
public class RegisterServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String role = req.getParameter("role");
		
		User user = new User();
		user.setUserName(name);
		user.setUserEmail(email);
		user.setUserPassword(password);
		user.setUserRole(role);
		
		UserDAO userdao = new UserDAO();
		//boolean status = userdao.registerUser(user);
		
		if(true) {
			resp.sendRedirect("login.html");
		}else {
			resp.getWriter().println("Registration failed");
		}
	}
}
