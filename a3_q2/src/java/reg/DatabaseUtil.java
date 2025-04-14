package reg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";
    
    // Get database connection
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
    
    // Register a new user
    public static int registerUser(User user) throws ClassNotFoundException, SQLException {
        int userId = -1;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            String sql = "INSERT INTO reg (name, last_name, city, mobile, email) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getCity());
            stmt.setString(4, user.getMobile());
            stmt.setString(5, user.getEmail());
            
            int rowsAffected = stmt.executeUpdate();
            
            if (rowsAffected > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    userId = rs.getInt(1);
                }
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        
        return userId;
    }
    
    // Get all registered users
    public static List<User> getAllUsers() throws ClassNotFoundException, SQLException {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM reg";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setCity(rs.getString("city"));
                user.setMobile(rs.getString("mobile"));
                user.setEmail(rs.getString("email"));
                
                users.add(user);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        
        return users;
    }
}
