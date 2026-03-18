package in.keen.Controller.CourseCRUD;

import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet("/searchCourse")
public class SearchCourseServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String query = req.getParameter("searchQuery");
		
		CourseDAO dao = new CourseDAO();
		TeacherDAO tdao = new TeacherDAO();
		
		List<Course> result;
		
		
		if(query == null || query.trim().isEmpty()) {
			result = dao.getAllCourses();
		}else {
			result = dao.searchCourse(query);
		}
		List<Teacher> tList = tdao.viewAllTeachers();
		
		req.setAttribute("courseList", result);
		req.setAttribute("teacherList", tList);
		req.getRequestDispatcher("/Course/viewCourses.jsp").forward(req, resp);
	}
}
