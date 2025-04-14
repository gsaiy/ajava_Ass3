package reg;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Extract user data from form
            String name = request.getParameter("name");
            String lastName = request.getParameter("lastName");
            String city = request.getParameter("city");
            String mobile = request.getParameter("mobile");
            String email = request.getParameter("email");
            
            // Validate input (basic validation)
            if (name == null || name.trim().isEmpty() ||
                lastName == null || lastName.trim().isEmpty() ||
                city == null || city.trim().isEmpty() ||
                mobile == null || mobile.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {
                
                response.sendRedirect("register.html?error=missing_fields");
                return;
            }
            
            // Create user object
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setCity(city);
            user.setMobile(mobile);
            user.setEmail(email);
            
            // Save user to database
            int userId = DatabaseUtil.registerUser(user);
            
            if (userId > 0) {
                // Registration successful
                response.sendRedirect("viewUsers");
            } else {
                // Registration failed
                response.sendRedirect("register.html?error=registration_failed");
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.html?error=database_error");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect to registration form
        response.sendRedirect("register.html");
    }
}

