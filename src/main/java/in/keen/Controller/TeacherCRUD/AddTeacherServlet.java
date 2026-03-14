package in.keen.Controller.TeacherCRUD;

import java.io.IOException;

import in.keen.DAO.TeacherDAO;
import in.keen.DAO.UserDAO;
import in.keen.Model.Teacher;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addTeacher")
public class AddTeacherServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String department = req.getParameter("department");
		
		Teacher teacher = new Teacher();
		teacher.setTeacherName(name);
		teacher.setTeacherEmail(email);
		teacher.setTeacherDepartment(department);
		
		User user = new User();
		user.setUserName(name);
		user.setUserEmail(email);
		user.setUserPassword("teacher");
		user.setUserRole("teacher");
		
		TeacherDAO dao = new TeacherDAO();
		
		UserDAO udao = new UserDAO();
		udao.registerUser(user);
		
		boolean status = dao.addTeacher(teacher);
		
		if(status) {
			resp.sendRedirect("viewTeachers");
		}else {
			resp.getWriter().print("Add Teacher Failed!");
		}
	}
}
