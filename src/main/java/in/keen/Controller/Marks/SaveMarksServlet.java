package in.keen.Controller.Marks;

import java.io.IOException;

import in.keen.DAO.MarksDAO;
import in.keen.Model.Mark;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/saveMarks")
public class SaveMarksServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		
		String[] studentsId = req.getParameterValues("studentsId");
		
		if(studentsId != null) {
			MarksDAO mdao = new MarksDAO();
			boolean allSaved = false;
			
			for(String id : studentsId){
				int studentId = Integer.parseInt(id);
				
				int marks = Integer.parseInt(req.getParameter("marks_"+ studentId));
				
				Mark m = new Mark();
				
				m.setCourseId(courseId);
				m.setStudentId(studentId);
				m.setMarks(marks);
				
				boolean saved = mdao.saveMarks(m);
				
				if(saved) {
					allSaved = true;
				}
				
			}
			if(allSaved) {
				resp.sendRedirect(req.getContextPath()+"/DashBoard/teacherDashboard.jsp");
			}else {
				resp.getWriter().print("Marks not saved");
			}
		}else {
			resp.getWriter().print("No Students Found");
		}
	}
}
