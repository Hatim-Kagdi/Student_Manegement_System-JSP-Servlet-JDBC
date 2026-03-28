package in.keen.Controller.EnrollmentCRUD;

import java.io.IOException;
import java.util.List;

import in.keen.DAO.EnrollmentDAO;
import in.keen.Model.Enrollment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/viewEnrollments")
public class ViewEnrollmentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int page = 1;
		int pageSize = 10;

		if (req.getParameter("page") != null)
			page = Integer.parseInt(req.getParameter("page"));
		if (req.getParameter("pageSize") != null)
			pageSize = Integer.parseInt(req.getParameter("pageSize"));

		EnrollmentDAO dao = new EnrollmentDAO();

		List<Enrollment> list = dao.getAllEnrollmentByPage(page, pageSize);
		int totalRecords = dao.getAllEnrollmentCount();
		int toatalPages = (int) Math.ceilDiv(totalRecords, pageSize);

		req.setAttribute("enrollmentList", list);
		req.setAttribute("totalPages", toatalPages);
		req.setAttribute("pageSize", pageSize);
		req.setAttribute("currentPage", page);

		req.getRequestDispatcher("Enrollment/viewEnrollment.jsp").forward(req, resp);
	}
}
