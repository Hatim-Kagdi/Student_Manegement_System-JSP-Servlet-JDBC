package in.keen.Controller.Attendance;

import java.io.IOException;
import java.sql.Date;

import in.keen.DAO.AttendanceDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/saveAttendance")
public class SaveAttendanceServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		
		String dateStr = req.getParameter("attendanceDate");
		Date attendanceDate = Date.valueOf(dateStr);
		
		String[] studentIds = req.getParameterValues("studentIds");
		
		if (studentIds != null) {
            AttendanceDAO adao = new AttendanceDAO();
            boolean allSaved = true;

            // 3. Loop through each student and get their specific status
            for (String sId : studentIds) {
                int studentId = Integer.parseInt(sId);
                
                // Fetch the radio button value using the unique name we created in JSP
                String status = req.getParameter("status_" + studentId);
                
                // 4. Save to database
                boolean isSaved = adao.saveAttendance(studentId, courseId, status, attendanceDate);
                
                if (!isSaved) {
                    allSaved = false;
                }
            }

            if (allSaved) {
                // Success! Redirect back to dashboard or a success page
                resp.sendRedirect(req.getContextPath() + "/DashBoard/teacherDashboard.jsp");
            } else {
                resp.getWriter().println("Error occurred while saving some attendance records.");
            }
        } else {
            resp.getWriter().println("No students found to mark attendance.");
        }
	}
	
}
