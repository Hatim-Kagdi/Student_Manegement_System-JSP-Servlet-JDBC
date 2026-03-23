package in.keen.Controller.StudentPortal;

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

@WebServlet("/updateStudentPassword")
public class UpdateStudentPasswordServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("session_user");
		
		String newPass = req.getParameter("newPassword");
		String confirmPass = req.getParameter("confirmPassword");
		
		//Validation Rules
		
		//Rule A
		if(!newPass.equals(confirmPass) || newPass == null || confirmPass == null || newPass.isEmpty() || confirmPass.isEmpty()) {
			resp.sendRedirect(req.getContextPath()+"/Student/editStudentPassword.jsp?error=New Passwords do not match and Field cannot be empty!");
			return;
		}
		
		//Rule B
		UserDAO udao = new UserDAO();
		
		String currentHashedPW = udao.getStoredHashedPassword(user.getUserId());
		
		if(BCrypt.checkpw(newPass, currentHashedPW)) {
			resp.sendRedirect(req.getContextPath() + "/Student/editStudentPassword.jsp?error=New password cannot be the same as the old password");
            return;
		}
		
		String newHashedPw = BCrypt.hashpw(newPass, BCrypt.gensalt());
		
		boolean success = udao.updateUserPassword(user.getUserId() , newHashedPw);
		
		if(success) {
			session.setAttribute("session_user", user);
			resp.sendRedirect(req.getContextPath()+"/studentProfile?msg=success");
		}else {
			resp.sendRedirect(req.getContextPath()+"/Teacher/editStudentPassword.jsp?msg=error");
		}
	}
}
