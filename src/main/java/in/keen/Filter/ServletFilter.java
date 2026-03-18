package in.keen.Filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class ServletFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        String path = req.getServletPath();

        // 1. Allow access to login page, CSS, and LoginServlet without a session
        boolean isLoggedIn = (session != null && session.getAttribute("session_user") != null);
        boolean isLoginRequest = path.equals("/login.html") || path.equals("/loginUser") || path.endsWith(".css");

        if (isLoggedIn || isLoginRequest) {
            // 2. If user is logged in OR accessing login page, let them pass
            chain.doFilter(request, response);
        } else {
            // 3. Otherwise, redirect to login
            resp.sendRedirect(req.getContextPath() + "/login.html");
        }
	}
}
