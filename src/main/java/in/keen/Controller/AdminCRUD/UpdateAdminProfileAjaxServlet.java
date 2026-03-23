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

@WebServlet("/updateAdminProfileAjax")
public class UpdateAdminProfileAjaxServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fieldName = req.getParameter("field");
		String newValue = req.getParameter("value");
		int userId = Integer.parseInt(req.getParameter("userId"));
		
		UserDAO udao = new UserDAO();
		
		boolean success = udao.updateUserFields(userId , fieldName, newValue);
		
		if(success) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("session_user");
			if("name".equals(fieldName)) user.setUserName(newValue);
			else if("email".equals(fieldName)) user.setUserEmail(newValue);
			session.setAttribute("session_user", user);
			resp.getWriter().write("success");
		}else {
			resp.getWriter().write("Failed to update details");
		}
		
	}
	
}
