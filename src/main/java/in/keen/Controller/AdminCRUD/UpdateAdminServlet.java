package in.keen.Controller.AdminCRUD;

import java.io.IOException;

import in.keen.DAO.UserDAO;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateAdminDetails")
public class UpdateAdminServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		int userId = Integer.parseInt(req.getParameter("userId"));
		String userName = req.getParameter("userName");
		String userEmail = req.getParameter("userEmail");
		String userPassword = req.getParameter("userPassword");
		
		User u = new User();
		
		u.setUserId(userId);
		u.setUserName(userName);
		u.setUserEmail(userEmail);
		u.setUserPassword(userPassword);
		
		UserDAO udao = new UserDAO();

		boolean status = udao.updateUser(u);
		
		if(status) {
			session.setAttribute("session_user", u);
			resp.sendRedirect(req.getContextPath()+"/Admin/viewAdminProfile.jsp?msg=success");
		}else {
			resp.sendRedirect(req.getContextPath()+"/Admin/editAdminProfile.jsp?msg=error");
		}
		
	}
	
}
