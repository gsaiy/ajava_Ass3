//// LogoutServlet.java
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/logout")
//public class LogoutServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//            throws ServletException, IOException {
//        
//        // Get all cookies
//        Cookie[] cookies = request.getCookies();
//        
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("username".equals(cookie.getName())) {
//                    // Set cookie max age to 0 to expire it immediately
//                    cookie.setMaxAge(0);
//                    response.addCookie(cookie);
//                    break;
//                }
//            }
//        }
//        
//        // Redirect to login page
//        response.sendRedirect("login.jsp");
//    }
//}

// LogoutServlet.java
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get all cookies
        Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    // Set cookie max age to 0 to expire it immediately
                    cookie.setMaxAge(0);
                    
                    // IMPORTANT: Set the same path as when the cookie was created
                    cookie.setPath("/"); // Match this with how you created the cookie
                    
                    // Add the "deleted" cookie to the response
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        
        // Redirect to login page
        response.sendRedirect("login.jsp");
    }
}