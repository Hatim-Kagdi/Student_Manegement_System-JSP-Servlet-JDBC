package in.keen.Controller.TeacherPortal;

import java.io.IOException;

import in.keen.DAO.UserDAO;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateTeacherPassword")
public class UpdateTeacherPasswordServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		User user = (User) session.getAttribute("session_user");
		
		String oldPass = user.getUserPassword();
		String newPass = req.getParameter("newPassword");
		String confirmPass = req.getParameter("confirmPassword");
		
		//Validation
		
		if(!newPass.equals(confirmPass)) {
			resp.sendRedirect(req.getContextPath() +"/Teacher/editTeacherPassword.jsp?error=New Password doesnt match!");
			return;
		}
		
		if(newPass.equals(oldPass)) {
			resp.sendRedirect(req.getContextPath()+"/Teacher/editTeacherPassword.jsp?error=New and Old Password are same!");
			return;
		}
		
		UserDAO udao = new UserDAO();
		
		boolean status = udao.updateUserPassword(user.getUserId(), newPass);
		
		if(status) {
			user.setUserPassword(newPass);
			session.setAttribute("session_user", user);
			resp.sendRedirect(req.getContextPath()+"/viewTeacherProfile?msg=Success");
		}else {
			resp.sendRedirect(req.getContextPath()+"/Teacher/editTeacherPassword.jsp?msg=Error in Database!");
		}
	}
}
