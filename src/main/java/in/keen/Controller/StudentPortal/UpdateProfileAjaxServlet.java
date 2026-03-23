package in.keen.Controller.StudentPortal;

import java.io.IOException;

import in.keen.DAO.StudentDAO;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateStudentProfileAjax")
public class UpdateProfileAjaxServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String field = req.getParameter("field");
		String value = req.getParameter("value");
		int id = Integer.parseInt(req.getParameter("studentId"));
		
		StudentDAO sdao = new StudentDAO();
		boolean success = false;
		
		//Determine which column
		if("name".equals(field)) {
			success = sdao.updateStudentField(id , field, value);
		}else if("email".equals(field)) {
			success = sdao.updateStudentField(id, field, value);
		}
		
		if(success) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("session_user");
			if("name".equals(field)) user.setUserName(value);
			if("email".equals(field)) user.setUserEmail(value);
			session.setAttribute("session_user", user);
			
			resp.getWriter().write("success");
		}else {
			resp.getWriter().write("failed");
		}
	}
}
