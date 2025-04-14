<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #2c3e50;
            text-align: center;
            margin-bottom: 30px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #34495e;
        }
        input[type="text"], input[type="email"], input[type="number"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 5px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            text-align: center;
        }
        .btn-save {
            background-color: #2ecc71;
            color: white;
        }
        .btn-cancel {
            background-color: #7f8c8d;
            color: white;
        }
        .btn:hover {
            opacity: 0.9;
        }
        .form-actions {
            margin-top: 30px;
            text-align: center;
        }
        .error {
            color: #e74c3c;
            font-size: 14px;
            margin-top: 5px;
        }
    </style>
</head>
<body>
    <div class="container">
        <c:choose>
            <c:when test="${student != null}">
                <h1>Edit Student</h1>
                <form action="students?action=update" method="post">
                    <input type="hidden" name="id" value="<c:out value='${student.id}' />" />
            </c:when>
            <c:otherwise>
                <h1>Add New Student</h1>
                <form action="students?action=insert" method="post">
            </c:otherwise>
        </c:choose>
        
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" id="name" name="name" value="<c:out value='${student.name}' />" required>
            </div>
            
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="<c:out value='${student.email}' />" required>
            </div>
            
            <div class="form-group">
                <label for="course">Course</label>
                <input type="text" id="course" name="course" value="<c:out value='${student.course}' />" required>
            </div>
            
            <div class="form-group">
                <label for="age">Age</label>
                <input type="number" id="age" name="age" value="<c:out value='${student.age}' />" min="1" max="120" required>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="btn btn-save">Save</button>
                <a href="students?action=list" class="btn btn-cancel">Cancel</a>
            </div>
        </form>
    </div>
</body>
</html>
