// LoginServlet.java
package com.login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get the username and password from the form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Validate against XML file
        if (validateUser(username, password, getServletContext())) {
            // Successful login
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("welcome.jsp"); // Redirect to welcome page
        } else {
            // Failed login
            request.setAttribute("errorMessage", "Invalid username or password");
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.forward(request, response);
        }
    }
    
    private boolean validateUser(String username, String password, ServletContext context) {
        try {
            // Get the path to users.xml file
            String xmlFilePath = context.getRealPath("/WEB-INF/classes/users.xml");
            
            // Create DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            // Parse the XML file
            Document document = builder.parse(new File(xmlFilePath));
            document.getDocumentElement().normalize();
            
            // Get all user elements
            NodeList userList = document.getElementsByTagName("user");
            
            // Loop through each user
            for (int i = 0; i < userList.getLength(); i++) {
                Node userNode = userList.item(i);
                
                if (userNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element userElement = (Element) userNode;
                    
                    // Get username and password from XML
                    String xmlUsername = getUserValue(userElement, "username");
                    String xmlPassword = getUserValue(userElement, "password");
                    
                    // Check if credentials match
                    if (username.equals(xmlUsername) && password.equals(xmlPassword)) {
                        return true; // Authentication successful
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false; // Authentication failed
    }
    
    // Helper method to get value from a user element
    private String getUserValue(Element userElement, String tagName) {
        NodeList nodeList = userElement.getElementsByTagName(tagName);
        Element element = (Element) nodeList.item(0);
        Node node = element.getChildNodes().item(0);
        return node.getNodeValue();
    }
}
