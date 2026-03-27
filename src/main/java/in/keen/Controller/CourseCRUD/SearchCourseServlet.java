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
		int page = 1;
		int pageSize = 10;
		
		if(req.getParameter("page") != null) page = Integer.parseInt(req.getParameter("page"));
		if(req.getParameter("pageSize") != null) pageSize = Integer.parseInt(req.getParameter("pageSize"));
		
		CourseDAO dao = new CourseDAO();
		
		List<Course> result;
		int totalPages;
		if(query == null || query.trim().isEmpty()) {
			result = dao.getCourseByPage(page, pageSize);
			int totalRecords = dao.getAllCoursesCount();
			totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		}else {
			result = dao.searchCourse(query, page, pageSize);
			int totalRecords = dao.getSearchedCoursesCount(query);
			totalPages = (int) Math.ceilDiv(totalRecords, pageSize);
		}
		
		req.setAttribute("courseList", result);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("searchQuery", query);
		req.setAttribute("currentPage", page);
		req.setAttribute("totalPages", totalPages);
		req.getRequestDispatcher("/Course/viewCourses.jsp").forward(req, resp);
	}
}
