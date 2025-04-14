package com.example.books;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/importBooks")
public class ImportBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() throws ServletException {
        // Initialize database table when servlet starts
        DatabaseHelper.createTableIfNeeded();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Path to XML file - adjust as needed for your environment
        String xmlFilePath = getServletContext().getRealPath("/WEB-INF/books.xml");
        
        // Clear existing books
        DatabaseHelper.clearBooks();
        
        // Parse XML file
        List<Book> books = XmlParser.parseXmlFile(xmlFilePath);
        
        // Insert books into database
        for (Book book : books) {
            DatabaseHelper.insertBook(book);
        }
        
        // Set attribute and forward to JSP
        request.setAttribute("importSuccess", true);
        request.setAttribute("importCount", books.size());
        request.getRequestDispatcher("bookList.jsp").forward(request, response);
    }
}
