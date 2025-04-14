<!-- welcome.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            background-color: #f5f5f5;
        }
        .welcome-container {
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 500px;
            text-align: center;
            margin-top: 50px;
        }
        h1 {
            color: #4CAF50;
        }
        .logout-btn {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #f44336;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
        }
        .logout-btn:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
    <div class="welcome-container">
        <h1>Welcome, <%= session.getAttribute("username") %>!</h1>
        <p>You have successfully logged in.</p>
        
        <a href="logout.jsp" class="logout-btn">Logout</a>
    </div>
</body>
</html>