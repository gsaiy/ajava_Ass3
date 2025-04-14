package com.example.books;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    // JDBC connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/bookstore";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    
    static {
        // Load JDBC driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    // Get database connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    
    // Create Books table if it doesn't exist
    public static void createTableIfNeeded() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            String sql = "CREATE TABLE IF NOT EXISTS Books (" +
                         "id INT AUTO_INCREMENT PRIMARY KEY," +
                         "title VARCHAR(255) NOT NULL," +
                         "author VARCHAR(255) NOT NULL," +
                         "price DECIMAL(10,2) NOT NULL)";
            
            stmt.executeUpdate(sql);
            System.out.println("Books table created or already exists");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Insert a book into the database
    public static void insertBook(Book book) {
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO Books (title, author, price) VALUES (?, ?, ?)")) {
            
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, book.getAuthor());
            pstmt.setDouble(3, book.getPrice());
            
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Clear all books from the table
    public static void clearBooks() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            stmt.executeUpdate("DELETE FROM Books");
            System.out.println("All books cleared from database");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Get all books from the database
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT title, author, price FROM Books")) {
            
            while (rs.next()) {
                String title = rs.getString("title");
                String author = rs.getString("author");
                double price = rs.getDouble("price");
                
                books.add(new Book(title, author, price));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return books;
    }
}
