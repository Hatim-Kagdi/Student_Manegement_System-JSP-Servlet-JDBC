package in.keen.Controller.TeacherPortal;

import java.io.IOException;

import in.keen.DAO.TeacherDAO;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateTeacherProfileAjax")
public class UpdateTeacherProfileAjaxServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String field = req.getParameter("field");
		String value = req.getParameter("value");
		int id = Integer.parseInt(req.getParameter("teacherId"));
		
		TeacherDAO tdao = new TeacherDAO();
		boolean success = false;
		
		if("name".equals(field)) {
			success  = tdao.updateTeacherProfileField(id, field, value);
		}else if("email".equals(field)){
			success = tdao.updateTeacherProfileField(id, field, value);
		}
		
		if(success) {
			HttpSession session = req.getSession();
			User user = (User) session.getAttribute("session_user");
			if("name".equals(field)) user.setUserName(value);
			else if("email".equals(field)) user.setUserEmail(value);
			session.setAttribute("session_user", user);
			resp.getWriter().write("success");
		}else {
			resp.getWriter().write("failed");
		}
	}
}
