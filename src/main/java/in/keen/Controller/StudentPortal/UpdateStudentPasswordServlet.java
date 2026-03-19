package in.keen.Controller.StudentPortal;

import java.io.IOException;

import in.keen.DAO.UserDAO;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateStudentPassword")
public class UpdateStudentPasswordServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("session_user");
		
		String oldPass = user.getUserPassword();
		String newPass = req.getParameter("newPassword");
		String confirmPass = req.getParameter("confirmPassword");
		
		//Validation Rules
		
		//Rule A
		if(!newPass.equals(confirmPass)) {
			resp.sendRedirect(req.getContextPath()+"/Student/editStudentPassword.jsp?error=New Passwords do not match!");
			return;
		}
		
		//Rule B
		if(oldPass.equals(newPass)) {
			resp.sendRedirect(req.getContextPath()+"/Student/editStudentPassword.jsp?error=Old and New password are same.");
			return;
		}
		
		UserDAO udao = new UserDAO();
		
		boolean success = udao.updateUserPassword(user.getUserId() , newPass);
		
		if(success) {
			user.setUserPassword(newPass);
			session.setAttribute("session_user", user);
			resp.sendRedirect(req.getContextPath()+"/studentProfile?msg=success");
		}else {
			resp.sendRedirect(req.getContextPath()+"/Teacher/editStudentPassword.jsp?msg=error");
		}
	}
}
