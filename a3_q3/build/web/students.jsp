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
            max-width: 1200px;
            margin: 0 auto;
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }
        h1 {
            color: #2c3e50;
            text-align: center;
            margin-bottom: 30px;
        }
        .btn {
            display: inline-block;
            padding: 8px 16px;
            margin: 5px;
            border-radius: 4px;
            text-decoration: none;
            color: white;
            font-weight: bold;
            text-align: center;
        }
        .btn-add {
            background-color: #2ecc71;
        }
        .btn-edit {
            background-color: #3498db;
        }
        .btn-delete {
            background-color: #e74c3c;
        }
        .btn-back {
            background-color: #7f8c8d;
        }
        .btn:hover {
            opacity: 0.9;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #34495e;
            color: white;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        .actions {
            text-align: center;
        }
        .header-actions {
            margin: 20px 0;
            text-align: right;
        }
        .no-students {
            text-align: center;
            padding: 20px;
            color: #7f8c8d;
            font-style: italic;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Student Management</h1>
        
        <div class="header-actions">
            <a href="students?action=new" class="btn btn-add">Add New Student</a>
            <a href="index.html" class="btn btn-back">Back to Home</a>
        </div>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Course</th>
                    <th>Age</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${listStudent != null && !empty listStudent}">
                        <c:forEach var="student" items="${listStudent}">
                            <tr>
                                <td><c:out value="${student.id}" /></td>
                                <td><c:out value="${student.name}" /></td>
                                <td><c:out value="${student.email}" /></td>
                                <td><c:out value="${student.course}" /></td>
                                <td><c:out value="${student.age}" /></td>
                                <td class="actions">
                                    <a href="students?action=edit&id=<c:out value='${student.id}' />" class="btn btn-edit">Edit</a>
                                    <a href="students?action=delete&id=<c:out value='${student.id}' />" 
                                       class="btn btn-delete"
                                       onclick="return confirm('Are you sure you want to delete this student?')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="6" class="no-students">No students found in the database.</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
    </div>
</body>
</html>