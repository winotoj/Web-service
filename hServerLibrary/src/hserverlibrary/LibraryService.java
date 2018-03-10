/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hserverlibrary;

import hintlibrary.LibraryInterface;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author minx1
 */
//6YdHuCv0iGIGQcev
public class LibraryService implements LibraryInterface{
    private static final String DB_PASS = "6YdHuCv0iGIGQcev";
    private static final String DB_USER = "hlibrary";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hlibrary";
    Connection conn;
    
    public LibraryService() throws IOException {
        try {
           System.out.println("LibraryService constructor executed");
            Class.forName("com.mysql.jdbc.Driver");
           System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exceptions in LibraryService contructor: " + ex.getMessage());
            // exception chaining
            throw new IOException("LibraryService creation failed", ex);
        }
    }
    @Override
    public int addBook(String title, String author, String yearPub) throws IOException {
        try{
            String insertSql = "INSERT INTO books VALUES (null, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(insertSql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, yearPub);
            preparedStatement.executeUpdate();
                return 0;
            } catch (SQLException ex) {
                throw new IOException("Database error in addShout()", ex);
            }
    }

    @Override
    public String[] getAllBooks() throws IOException {
        try {
            ArrayList<String> result = new ArrayList<>();
            String sql = "SELECT * FROM books";
            Statement stmt = conn.createStatement();
            // try with resources - do NOT use close();
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    String yearpub = rs.getString("yearpub");
                    result.add(title +"; " + author + "; " + yearpub);
                }
            }
            return result.toArray(new String[0]);
        } catch (SQLException ex) {
            throw new IOException("Database error in getAllShouts()", ex);
        }
    }

    @Override
    public String[] getFilteredBooks(String keyword) throws IOException {
        System.out.println("Test before prepared");
        try {
           
            ArrayList<String> result = new ArrayList<>();
            String sql = "SELECT * FROM books WHERE title like ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, '%' + keyword + '%');
            // try with resources - do NOT use close();
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    String author = rs.getString("author");
                    String yearpub = rs.getString("yearpub");
                    result.add(title +"; " + author + "; " + yearpub);
                }
            }
            return result.toArray(new String[0]);
        } catch (SQLException ex) {
            throw new IOException("Database error in getfilteredBook()", ex);
        }
    }
    
}
