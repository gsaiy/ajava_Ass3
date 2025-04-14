package com.example.books;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class BookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get all books from database
        List<Book> books = DatabaseHelper.getAllBooks();
        
        // Set attribute and forward to JSP
        request.setAttribute("books", books);
        request.getRequestDispatcher("bookList.jsp").forward(request, response);
    }
}
