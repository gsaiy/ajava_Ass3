package com.example.books;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {
    
    public static List<Book> parseXmlFile(String filePath) {
        List<Book> books = new ArrayList<>();
        
        try {
            File xmlFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            
            // Normalize the XML structure
            doc.getDocumentElement().normalize();
            
            // Get all book elements
            NodeList bookList = doc.getElementsByTagName("book");
            
            // Process each book element
            for (int i = 0; i < bookList.getLength(); i++) {
                Node bookNode = bookList.item(i);
                
                if (bookNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element bookElement = (Element) bookNode;
                    
                    // Extract book details
                    String title = getElementValue(bookElement, "title");
                    String author = getElementValue(bookElement, "author");
                    double price = Double.parseDouble(getElementValue(bookElement, "price"));
                    
                    // Create book object and add to list
                    Book book = new Book(title, author, price);
                    books.add(book);
                    
                    System.out.println("Parsed book: " + book);
                }
            }
            
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        
        return books;
    }
    
    // Helper method to get element value
    private static String getElementValue(Element parentElement, String tagName) {
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            NodeList childNodes = nodeList.item(0).getChildNodes();
            if (childNodes != null && childNodes.getLength() > 0) {
                return childNodes.item(0).getNodeValue();
            }
        }
        return "";
    }
}
