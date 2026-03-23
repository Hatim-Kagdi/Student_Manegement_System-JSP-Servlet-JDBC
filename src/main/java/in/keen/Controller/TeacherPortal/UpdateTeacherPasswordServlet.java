package in.keen.Controller.TeacherPortal;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

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
		
		String newPass = req.getParameter("newPassword");
		String confirmPass = req.getParameter("confirmPassword");
		
		//Validation
		
		if(!newPass.equals(confirmPass) || newPass == null || newPass.isEmpty() || confirmPass == null || confirmPass.isEmpty()) {
			resp.sendRedirect(req.getContextPath() +"/Teacher/editTeacherPassword.jsp?error=New Password doesnt match!");
			return;
		}
		
		UserDAO udao = new UserDAO(); 
		String currentHashedPW = udao.getStoredHashedPassword(user.getUserId());
		
		if(BCrypt.checkpw(newPass, currentHashedPW)) {
			resp.sendRedirect(req.getContextPath()+"/Teacher/editTeacherPassword.jsp?error=New and Old Password are same!");
			return;
		}
		
		String newHashedPW = BCrypt.hashpw(newPass, BCrypt.gensalt());
		
		boolean status = udao.updateUserPassword(user.getUserId(), newHashedPW);
		
		if(status) {
			session.setAttribute("session_user", user);
			resp.sendRedirect(req.getContextPath()+"/viewTeacherProfile?msg=success");
		}else {
			resp.sendRedirect(req.getContextPath()+"/Teacher/editTeacherPassword.jsp?msg=Error in Database!");
		}
	}
}
