package in.keen.Controller.StudentCRUD;


	import java.io.IOException;
	import java.sql.Date;
	import in.keen.DAO.StudentDAO;
import in.keen.DAO.UserDAO;
import in.keen.Model.Student;
import in.keen.Model.User;
import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.HttpServlet;
	import jakarta.servlet.http.HttpServletRequest;
	import jakarta.servlet.http.HttpServletResponse;

	@WebServlet("/addStudent")
	public class AddStudentServlet extends HttpServlet{
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String name = req.getParameter("name");
			String email = req.getParameter("email");
			String department = req.getParameter("department");
			int year = Integer.parseInt(req.getParameter("year"));
			Date admissionDate = Date.valueOf(req.getParameter("admissionDate"));
			
			Student student = new Student();
			student.setStudentName(name);
			student.setStudentEmail(email);
			student.setStudentDepartment(department);
			student.setStudentYear(year);
			student.setStudentAddmissionYear(admissionDate);
			
			User user = new User();
			user.setUserName(student.getStudentName());
			user.setUserEmail(email);
			user.setUserPassword("student");
			user.setUserRole("student");
			
			UserDAO udao = new UserDAO();
			udao.registerUser(user);
		
			StudentDAO dao = new StudentDAO();
			boolean status = dao.addStudent(student);
			
			if(status) {
				resp.sendRedirect("DashBoard/adminDashboard.jsp");
			}else {
				resp.getWriter().print("Add new Student Failed!");
			}
		}
	}
