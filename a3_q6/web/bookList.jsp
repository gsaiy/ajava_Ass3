<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 900px;
            margin: 0 auto;
            background: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }
        h1 {
            color: #333;
            text-align: center;
        }
        .message {
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
            text-align: center;
        }
        .success {
            background-color: #dff0d8;
            color: #3c763d;
            border: 1px solid #d6e9c6;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        tr:hover {
            background-color: #f5f5f5;
        }
        .actions {
            text-align: center;
            margin-top: 20px;
        }
        .btn {
            display: inline-block;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            border: none;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Book List</h1>
        
        <c:if test="${importSuccess == true}">
            <div class="message success">
                Successfully imported ${importCount} books from XML file!
            </div>
        </c:if>
        
        <table>
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Author</th>
                    <th>Price (₹)</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty books}">
                        <tr>
                            <td colspan="3" style="text-align:center">No books available.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="book" items="${books}">
                            <tr>
                                <td>${book.title}</td>
                                <td>${book.author}</td>
                                <td><fmt:formatNumber value="${book.price}" type="currency" currencySymbol="₹" maxFractionDigits="2"/></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        
        <div class="actions">
            <a href="importBooks" class="btn">Import Books from XML</a>
        </div>
    </div>
</body>
</html>