<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Visit Counter</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            line-height: 1.6;
        }
        .counter-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
            text-align: center;
        }
        .visit-count {
            font-size: 24px;
            font-weight: bold;
            color: #4285f4;
        }
    </style>
</head>
<body>
    <%
        // Get the cookie named "visitCount" if it exists
        Cookie[] cookies = request.getCookies();
        int visitCount = 0;
        Cookie visitCookie = null;
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("visitCount".equals(cookie.getName())) {
                    visitCookie = cookie;
                    visitCount = Integer.parseInt(cookie.getValue());
                    break;
                }
            }
        }
        
        // Increment the visit count
        visitCount++;
        
        // Create a new cookie or update the existing one
        if (visitCookie == null) {
            visitCookie = new Cookie("visitCount", String.valueOf(visitCount));
        } else {
            visitCookie.setValue(String.valueOf(visitCount));
        }
        
        // Set cookie expiration time (in seconds) - this example is set to expire in 30 days
        visitCookie.setMaxAge(30 * 24 * 60 * 60);
        
        // Add cookie to the response
        response.addCookie(visitCookie);
    %>
    
    <div class="counter-container">
        <h1>Welcome to Our Website</h1>
        
        <p>You have visited this page <span class="visit-count"><%= visitCount %></span> 
            <%= visitCount == 1 ? "time" : "times" %>.</p>
            
        <p>This counter is tracked using cookies and will increment each time you visit or refresh the page.</p>
        
        <p><small>Note: If you clear your browser cookies, the counter will reset.</small></p>
    </div>
</body>
</html>