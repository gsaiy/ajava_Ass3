import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get the username from the form
        String username = request.getParameter("username");
        
        if (username != null && !username.trim().isEmpty()) {
            // Create a cookie with the username
            Cookie usernameCookie = new Cookie("username", username);
            
            // Set cookie to expire after 24 hours (24 * 60 * 60 seconds)
            usernameCookie.setMaxAge(86400);
            
            usernameCookie.setPath("/");
            // Add cookie to response
            response.addCookie(usernameCookie);
            
            // Redirect to welcome page
            response.sendRedirect("welcome.jsp");
        } else {
            // If username is empty, redirect back to login page
            request.setAttribute("errorMessage", "Username cannot be empty");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
