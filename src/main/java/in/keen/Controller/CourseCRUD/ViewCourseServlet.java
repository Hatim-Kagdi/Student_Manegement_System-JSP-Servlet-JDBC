package in.keen.Controller.CourseCRUD;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.CourseDAO;
import in.keen.DAO.TeacherDAO;
import in.keen.Model.Course;
import in.keen.Model.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewCourses")
public class ViewCourseServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int page = 1;
		int pageSize = 10;
		
		if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));
		if(req.getParameter("pageSize") != null) pageSize = Integer.parseInt(req.getParameter("pageSize"));
		
		//TO get data of Course
		CourseDAO dao = new CourseDAO();
		List<Course> course = dao.getCourseByPage(page, pageSize);
		int totalRecords = dao.getAllCoursesCount();
		int totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		
		req.setAttribute("courseList", course);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("currentPage", page);
		req.setAttribute("pageSize", pageSize);
		
		
		req.getRequestDispatcher("Course/viewCourses.jsp").forward(req, resp);
	}
}
