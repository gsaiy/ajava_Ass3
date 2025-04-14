<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Check if user already has a username cookie
    String username = null;
    Cookie[] cookies = request.getCookies();
    
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                username = cookie.getValue();
                break;
            }
        }
    }
    
    // Redirect based on cookie presence
    if (username != null) {
        response.sendRedirect("welcome.jsp");
    } else {
        response.sendRedirect("login.jsp");
    }
%>