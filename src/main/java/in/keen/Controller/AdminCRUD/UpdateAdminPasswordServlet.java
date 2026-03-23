package in.keen.Controller.AdminCRUD;

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

@WebServlet("/updateAdminPassword")
public class UpdateAdminPasswordServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("session_user");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");
		
		//Validation rules 
		//A. Password should not be null or empty or diffrent
		if(newPassword == null || newPassword.isEmpty() || !newPassword.equals(confirmPassword)) {
			resp.sendRedirect(req.getContextPath() + "/Admin/editAdminPassword.jsp?error=Passwords must match and not be empty");
			return;
		}
		
		//B. New and old should not be same
		UserDAO udao = new UserDAO();
		
		String currentHash = udao.getStoredHashedPassword(user.getUserId());
		
		if(BCrypt.checkpw(newPassword, currentHash)) {
			resp.sendRedirect("Admin/editAdminPassword.jsp?error=New password cannot be the same as the old password");
            return;
		}
		
		String hashedPW = BCrypt.hashpw(newPassword, BCrypt.gensalt());
		
		boolean success = udao.updateUserPassword(user.getUserId() , hashedPW);
		
		if (success) {
			session.setAttribute("session_user", user);
            resp.sendRedirect("Admin/viewAdminProfile.jsp?msg=success");
        } else {
            resp.sendRedirect("Admin/editAdminPassword.jsp?error=Database error");
        }
	}
}
